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

import uk.gov.hmrc.helptosavecalculator.models.FirstBonusInput
import uk.gov.hmrc.helptosavecalculator.utils.monthsSince

internal class FirstBonusTermCalculation {

    fun calculateTotalProjectedSavingsIncludeBonuses(
        totalProjectedSavings: Double,
        totalProjectedBonuses: Double
    ): Double {
        return totalProjectedSavings + totalProjectedBonuses
    }

    fun calculateAdditionalSavingsThisMonth(input: FirstBonusInput): Double {
        return if (input.regularPayment > input.paidInThisMonth) {
            input.regularPayment - input.paidInThisMonth
        } else {
            0.0
        }
    }

    fun calculateTotalProjectedSavings(
        input: FirstBonusInput,
        additionalSavingsThisMonth: Double,
        monthsLeftInScheme: Int
    ): Double {
        return input.currentBalance + additionalSavingsThisMonth + (input.regularPayment * monthsLeftInScheme)
    }

    fun calculateTotalProjectedBonuses(
        projectedFirstBonus: Double,
        projectedFinalBonus: Double
    ): Double {
        return projectedFirstBonus + projectedFinalBonus
    }

    fun calculateProjectedSavingsFirstBonusPeriod(
        input: FirstBonusInput,
        additionalSavingsThisMonth: Double,
        monthsLeftInFirstTerm: Int
    ): Double {
        return input.currentBalance + additionalSavingsThisMonth + (input.regularPayment * monthsLeftInFirstTerm)
    }

    fun calculateHighestBalanceFirstBonusPeriod(
        input: FirstBonusInput,
        projectedSavingsFirstBonusPeriod: Double
    ): Double {
        return input.balanceMustBeMoreThanForBonus.takeIf {
            it > projectedSavingsFirstBonusPeriod
        } ?: projectedSavingsFirstBonusPeriod
    }

    fun calculateProjectedFirstBonus(highestBalanceFirstBonusPeriod: Double): Double {
        return highestBalanceFirstBonusPeriod / 2
    }

    fun calculateProjectedAdditionalSavingsFinalBonusPeriod(input: FirstBonusInput): Double {
        return input.regularPayment * 24
    }

    fun calculateProjectedFinalBonus(
        highestBalanceFinalBonusPeriod: Double,
        highestBalanceFirstBonusPeriod: Double
    ): Double {
        return if (highestBalanceFinalBonusPeriod > highestBalanceFirstBonusPeriod) {
            (highestBalanceFinalBonusPeriod - highestBalanceFirstBonusPeriod) / 2
        } else {
            0.0
        }
    }

    fun calculateMonthsLeftInScheme(input: FirstBonusInput): Pair<Int, Int> {
        val startDate = input.accountStartDate.convertToDateTime()
        val secondTermEndDate = input.secondTermEndDate.convertToDateTime()
        val firstTermEndDate = input.firstTermEndDate.convertToDateTime()
        val monthsLeftInScheme = startDate.monthsSince(secondTermEndDate)
        val monthsLeftInFirstTerm = startDate.monthsSince(firstTermEndDate)
        return Pair(monthsLeftInScheme, monthsLeftInFirstTerm)
    }
}
