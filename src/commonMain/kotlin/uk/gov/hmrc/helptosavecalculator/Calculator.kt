/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.helptosavecalculator

import com.soywiz.klock.DateTime
import uk.gov.hmrc.helptosavecalculator.config.HtSSchemeConfig
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidAccountStartDate
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidCurrentBalance
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidCurrentBonusAmount
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidRegularPaymentException
import uk.gov.hmrc.helptosavecalculator.models.CalculatorResponse
import uk.gov.hmrc.helptosavecalculator.models.MonthlyBreakdown
import uk.gov.hmrc.helptosavecalculator.utils.monthsSince
import uk.gov.hmrc.helptosavecalculator.validation.RegularPaymentValidators
import kotlin.jvm.JvmOverloads

class Calculator @JvmOverloads constructor(
    private val regularPayment: Int,
    private val currentBalance: Int? = null,
    private val currentFirstPeriodBonus: Double? = null,
    private val currentSecondPeriodBonus: Double? = null,
    private val accountStartDate: DateTime? = null) : HtSSchemeConfig() {

    fun run(): CalculatorResponse {
        if (currentBalance == null && accountStartDate != null) {
            throw InvalidCurrentBalance()
        }
        if (currentBalance != null && accountStartDate == null) {
            throw InvalidAccountStartDate()
        }
        if (currentBalance != null && (currentFirstPeriodBonus == null || currentSecondPeriodBonus == null)) {
            throw InvalidCurrentBonusAmount()
        }
        if (!RegularPaymentValidators.isValidRegularPayments(regularPayment)) {
            throw InvalidRegularPaymentException(regularPayment)
        }

        val listOfMonths: MutableList<MonthlyBreakdown> = mutableListOf<MonthlyBreakdown>()
        var currentMonth: Int = accountStartDate?.monthsSince()?.plus(1) ?: 1
        var balance: Int = currentBalance ?: 0
        var endOfFirstPeriodBonus: Double = currentFirstPeriodBonus ?: 0.0
        var endOfSecondPeriodBonus: Double = currentSecondPeriodBonus ?: 0.0

        while (currentMonth <= endOfSecondBonusPeriod) {
            balance += regularPayment
            when (currentMonth) {
                in startOfFirstBonusPeriod..endOfFirstBonusPeriod -> {
                    endOfFirstPeriodBonus += (regularPayment / 2)
                }
                in startOfSecondBonusPeriod..endOfSecondBonusPeriod -> {
                    endOfSecondPeriodBonus += (regularPayment / 2)
                }
                else -> throw IllegalStateException("The scheme has exceeded 1 to 48 months")
            }
            listOfMonths.add(
                MonthlyBreakdown(
                    monthNumber = currentMonth,
                    balance = balance,
                    secondYearBonus = endOfFirstPeriodBonus,
                    fourthYearBonus = endOfSecondPeriodBonus))
            currentMonth++
        }
        return CalculatorResponse(
            monthlyPayments = regularPayment,
            finalBalance = balance,
            finalSecondYearBonus = endOfFirstPeriodBonus,
            finalFourthYearBonus = endOfSecondPeriodBonus,
            monthlyBreakdown = listOfMonths)
    }
}
