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

import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidRegularPaymentException
import uk.gov.hmrc.helptosavecalculator.models.FirstBonusInput
import uk.gov.hmrc.helptosavecalculator.utils.convertYearMonthDayToDateTime
import uk.gov.hmrc.helptosavecalculator.utils.convertYearMonthToDateTime
import uk.gov.hmrc.helptosavecalculator.utils.monthsSince
import uk.gov.hmrc.helptosavecalculator.validation.RegularPaymentValidators

open class FirstBonusTermCalculation {

    protected fun calculateTotalProjectedSavingsIncludeBonuses(totalProjectedSavings: Double, totalProjectedBonuses: Double): Double {
        return totalProjectedSavings + totalProjectedBonuses
    }

    protected fun calculateAdditionalSavingsThisMonth(input: FirstBonusInput): Double {
        return if (input.regularPayment > input.paidInThisMonth) {
            input.regularPayment - input.paidInThisMonth
        } else {
            0.0
        }
    }

    protected fun calculateTotalProjectedSavings(input: FirstBonusInput, additionalSavingsThisMonth: Double, monthsLeftInScheme: Int): Double {
        return input.currentBalance + additionalSavingsThisMonth + (input.regularPayment * monthsLeftInScheme)
    }

    protected fun calculateTotalProjectedBonuses(projectedFirstBonus: Double, projectedFinalBonus: Double): Double {
        return projectedFirstBonus + projectedFinalBonus
    }

    protected fun calculateProjectedSavingsFirstBonusPeriod(input: FirstBonusInput, additionalSavingsThisMonth: Double, monthsLeftInFirstTerm: Int): Double {
        return input.currentBalance + additionalSavingsThisMonth + (input.regularPayment * monthsLeftInFirstTerm)
    }

    protected fun calculateHighestBalanceFirstBonusPeriod(input: FirstBonusInput, projectedSavingsFirstBonusPeriod: Double): Double {
        return input.balanceMustBeMoreThanForBonus.takeIf {
            it > projectedSavingsFirstBonusPeriod
        } ?: projectedSavingsFirstBonusPeriod
    }

    protected fun calculateProjectedFirstBonus(highestBalanceFirstBonusPeriod: Double): Double {
        return highestBalanceFirstBonusPeriod / 2
    }

    protected fun calculateProjectedAdditionalSavingsFinalBonusPeriod(input: FirstBonusInput): Double {
        return input.regularPayment * 24
    }

    protected fun calculateProjectedFinalBonus(highestBalanceFinalBonusPeriod: Double, highestBalanceFirstBonusPeriod: Double): Double {
        return if (highestBalanceFinalBonusPeriod > highestBalanceFirstBonusPeriod) {
            (highestBalanceFinalBonusPeriod - highestBalanceFirstBonusPeriod) / 2
        } else {
            0.0
        }
    }

    protected fun calculateMonthsLeftInScheme(input: FirstBonusInput): Int {
        val accountStartDateInDateTime = input.accountStartDate.convertYearMonthToDateTime()
        val accountSecondTermEndDateInDateTime = input.secondTermEndDate.convertYearMonthDayToDateTime()
        return accountStartDateInDateTime.monthsSince(accountSecondTermEndDateInDateTime)
    }

    protected fun calculateMonthsLeftInFirstTerm(input: FirstBonusInput): Int {
        val accountStartDateInDateTime = input.accountStartDate.convertYearMonthToDateTime()
        val accountFirstTermEndDateInDateTime = input.firstTermEndDate.convertYearMonthDayToDateTime()
        return accountStartDateInDateTime.monthsSince(accountFirstTermEndDateInDateTime)
    }

    protected fun validateUserInput(regularPayment: Double) {
        if (!RegularPaymentValidators.isValidRegularPayments(regularPayment)) {
            throw InvalidRegularPaymentException(regularPayment)
        }
    }
}
