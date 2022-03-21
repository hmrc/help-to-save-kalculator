/*
 * Copyright 2022 HM Revenue & Customs
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
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidRegularPaymentException
import uk.gov.hmrc.helptosavecalculator.models.CalculatorResponse
import uk.gov.hmrc.helptosavecalculator.models.MonthlyBreakdown
import uk.gov.hmrc.helptosavecalculator.utils.monthsSince
import uk.gov.hmrc.helptosavecalculator.validation.RegularPaymentValidators

object Calculator : HtSSchemeConfig() {

    fun run(regularPayment: Double): CalculatorResponse {
        return calculate(regularPayment)
    }

    fun run(
        regularPayment: Double,
        currentBalance: Double,
        currentFirstPeriodBonus: Double,
        currentSecondPeriodBonus: Double,
        accountStartDate: DateTime
    ): CalculatorResponse {
        return calculate(
            regularPayment, currentBalance, currentFirstPeriodBonus, currentSecondPeriodBonus, accountStartDate)
    }

    private fun calculate(
        regularPayment: Double,
        currentBalance: Double? = null,
        currentPeriod1Bonus: Double? = null,
        currentPeriod2Bonus: Double? = null,
        accountStartDate: DateTime? = null
    ): CalculatorResponse {
        val listOfMonths: MutableList<MonthlyBreakdown> = mutableListOf()
        var currentMonth: Int = accountStartDate?.monthsSince()?.plus(1) ?: 1
        var endOfSchemeSavings: Double = currentBalance ?: 0.0
        var endOfPeriod1Savings: Double = currentBalance ?: 0.0
        var endOfPeriod2Savings: Double = currentBalance ?: 0.0
        var endOfPeriod1Bonus: Double = currentPeriod1Bonus ?: 0.0
        var endOfPeriod2Bonus: Double = currentPeriod2Bonus ?: 0.0

        validateUserInput(regularPayment)

        while (currentMonth <= endOfSecondBonusPeriod) {
            endOfSchemeSavings += regularPayment
            when (currentMonth) {
                in startOfFirstBonusPeriod..endOfFirstBonusPeriod -> {
                    endOfPeriod1Bonus += (regularPayment / 2)
                    if (currentMonth == endOfFirstBonusPeriod) {
                        endOfPeriod1Savings = endOfSchemeSavings
                    }
                }
                in startOfSecondBonusPeriod..endOfSecondBonusPeriod -> {
                    endOfPeriod2Bonus += (regularPayment / 2)
                    if (currentMonth == endOfSecondBonusPeriod) {
                        endOfPeriod2Savings = endOfSchemeSavings - endOfPeriod1Savings
                    }
                }
                else -> throw IllegalStateException("The scheme has exceeded 1 to 48 months")
            }
            listOfMonths.add(
                MonthlyBreakdown(
                    monthNumber = currentMonth,
                    savingsToDate = endOfSchemeSavings,
                    period1Bonus = endOfPeriod1Bonus,
                    period2Bonus = endOfPeriod2Bonus))
            currentMonth++
        }
        return CalculatorResponse(
            monthlyPayments = regularPayment,
            monthlyBreakdown = listOfMonths,
            endOfSchemeSavings = endOfSchemeSavings,
            endOfPeriod1Bonus = endOfPeriod1Bonus,
            endOfPeriod1Savings = endOfPeriod1Savings,
            endOfPeriod2Bonus = endOfPeriod2Bonus,
            endOfPeriod2Savings = endOfPeriod2Savings)
    }

    private fun validateUserInput(regularPayment: Double) {
        if (!RegularPaymentValidators.isValidRegularPayments(regularPayment)) {
            throw InvalidRegularPaymentException(regularPayment)
        }
    }
}
