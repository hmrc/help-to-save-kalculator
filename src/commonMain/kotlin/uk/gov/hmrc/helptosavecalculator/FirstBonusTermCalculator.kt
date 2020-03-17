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

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.parse
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidRegularPaymentException
import uk.gov.hmrc.helptosavecalculator.models.FirstBonusCalculatorResponse
import uk.gov.hmrc.helptosavecalculator.utils.monthsSince
import uk.gov.hmrc.helptosavecalculator.validation.RegularPaymentValidators

object FirstBonusTermCalculator {

    fun runFirstBonusCalculator(
            regularPayment: Double,
            currentBalance: Double,
            paidInThisMonth: Double,
            accountStartDate: String,
            firstTermEndDate: String,
            secondTermEndDate: String,
            BalanceMustBeMoreThanForBonus: Double
    ): FirstBonusCalculatorResponse {
        return calculateFirstBonus(regularPayment, currentBalance, paidInThisMonth, accountStartDate, firstTermEndDate, secondTermEndDate, BalanceMustBeMoreThanForBonus)
    }

    private fun calculateFirstBonus(
            regularPayment: Double,
            currentBalance: Double,
            paidInThisMonth: Double,
            accountStartDate: String,
            firstTermEndDate: String,
            secondTermEndDate: String,
            BalanceMustBeMoreThanForBonus: Double
    ): FirstBonusCalculatorResponse {
        validateUserInput(regularPayment)

        val accountStartDateInDateTime = convertYearMonthToDateTime(accountStartDate)
        val accountFirstTermEndDateInDateTime = convertYearMonthDayToDateTime(firstTermEndDate)
        val accountSecondTermEndDateInDateTime = convertYearMonthDayToDateTime(secondTermEndDate)
        val monthLeftInScheme = calculateMonthsLeftInScheme(accountStartDateInDateTime, accountSecondTermEndDateInDateTime)
        val monthLetInFirstTerm = calculateMonthsLeftInFirstTerm(accountStartDateInDateTime, accountFirstTermEndDateInDateTime)
        val additionalSavingsThisMonth = calculateAdditionalSavingsThisMonth(regularPayment, paidInThisMonth)
        val totalProjectedSavings = calculateTotalProjectedSavings(currentBalance, additionalSavingsThisMonth, regularPayment, monthLeftInScheme)
        val projectedSavingsFirstBonusPeriod = calculateProjectedSavingsFirstBonusPeriod(currentBalance, additionalSavingsThisMonth, regularPayment, monthLetInFirstTerm)
        val highestBalanceFirstBonusPeriod = calculateHighestBalanceFirstBonusPeriod(BalanceMustBeMoreThanForBonus, projectedSavingsFirstBonusPeriod)
        val projectedFirstBonus = calculateProjectedFirstBonus(highestBalanceFirstBonusPeriod)
        val projectedAdditionalSavingsFinalBonusPeriod = calculateProjectedAdditionalSavingsFinalBonusPeriod(regularPayment)
        val projectedFinalBonus = calculateProjectedFinalBonus(totalProjectedSavings, highestBalanceFirstBonusPeriod)
        val totalProjectedBonuses = calculateTotalProjectedBonuses(projectedFirstBonus, projectedFinalBonus)
        val totalProjectedSavingsIncludingBonuses = calculateTotalProjectedSavingsIncludeBonuses(totalProjectedSavings, totalProjectedBonuses)

        return FirstBonusCalculatorResponse(
                totalProjectedSavingsIncludingBonuses = totalProjectedSavingsIncludingBonuses,
                totalProjectedSavings = totalProjectedSavings,
                totalProjectedBonuses = totalProjectedBonuses,
                projectedSavingsFirstBonusPeriod = projectedSavingsFirstBonusPeriod,
                projectedFirstBonus = projectedFirstBonus,
                projectedAdditionalSavingsFinalBonusPeriod = projectedAdditionalSavingsFinalBonusPeriod,
                projectedFinalBonus = projectedFinalBonus
        )
    }

    private fun calculateTotalProjectedSavingsIncludeBonuses(totalProjectedSavings: Double, totalProjectedBonuses: Double): Double {
        return totalProjectedSavings + totalProjectedBonuses
    }

    private fun calculateAdditionalSavingsThisMonth(regularPayment: Double, paidInThisMonth: Double): Double {
        return if (regularPayment > paidInThisMonth) {
            regularPayment - paidInThisMonth
        } else {
            0.0
        }
    }

    private fun calculateTotalProjectedSavings(currentBalance: Double, additionalSavingsThisMonth: Double, regularPayment: Double, monthsLeftInScheme: Int): Double {
        return currentBalance + additionalSavingsThisMonth + (regularPayment * monthsLeftInScheme)
    }

    private fun calculateTotalProjectedBonuses(projectedFirstBonus: Double, projectedFinalBonus: Double): Double {
        return projectedFirstBonus + projectedFinalBonus
    }

    private fun calculateProjectedSavingsFirstBonusPeriod(currentBalance: Double, additionalSavingsThisMonth: Double, regularPayment: Double, monthsLeftInFirstTerm: Int): Double {
        return currentBalance + additionalSavingsThisMonth + (regularPayment * monthsLeftInFirstTerm)
    }

    private fun calculateHighestBalanceFirstBonusPeriod(balanceMustBeMoreThanValue: Double, projectedSavingsFirstBonusPeriod: Double): Double {
        return balanceMustBeMoreThanValue.takeIf {
            it > projectedSavingsFirstBonusPeriod
        } ?: projectedSavingsFirstBonusPeriod
    }

    private fun calculateProjectedFirstBonus(highestBalanceFirstBonusPeriod: Double): Double {
        return highestBalanceFirstBonusPeriod / 2
    }

    private fun calculateProjectedAdditionalSavingsFinalBonusPeriod(regularPayment: Double): Double {
        return regularPayment * 24
    }

    private fun calculateProjectedFinalBonus(highestBalanceFinalBonusPeriod: Double, highestBalanceFirstBonusPeriod: Double): Double {
        return if (highestBalanceFinalBonusPeriod > highestBalanceFirstBonusPeriod) {
            (highestBalanceFinalBonusPeriod - highestBalanceFirstBonusPeriod) / 2
        } else {
            0.0
        }
    }

    private fun calculateMonthsLeftInScheme(accountStartDate: DateTime, secondTermEndDate: DateTime): Int {
        return accountStartDate.monthsSince(secondTermEndDate)
    }

    private fun calculateMonthsLeftInFirstTerm(accountStartDate: DateTime, firstTermEndDate: DateTime): Int {
        return accountStartDate.monthsSince(firstTermEndDate)
    }

    private fun convertYearMonthToDateTime(yearMonthFormat: String): DateTime {
        val dateFormat = DateFormat("yyyy-MM-dd")
        return dateFormat.parse("$yearMonthFormat-01").local
    }

    private fun convertYearMonthDayToDateTime(yearMonthDayFormat: String): DateTime {
        val dateFormat = DateFormat("yyyy-MM-dd")
        return dateFormat.parse(yearMonthDayFormat).local
    }

    private fun validateUserInput(regularPayment: Double) {
        if (!RegularPaymentValidators.isValidRegularPayments(regularPayment)) {
            throw InvalidRegularPaymentException(regularPayment)
        }
    }
}