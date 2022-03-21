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

import uk.gov.hmrc.helptosavecalculator.models.FirstBonusInput
import uk.gov.hmrc.helptosavecalculator.utils.monthsSince

internal class FirstBonusTermCalculation {

    fun calculateTotalProjectedSavingsIncludeBonuses(
        totalProjectedSavings: Double,
        totalProjectedBonuses: Double
    ) = totalProjectedSavings + totalProjectedBonuses

    fun calculateAdditionalSavingsThisMonth(input: FirstBonusInput) =
            if (input.regularPayment > input.paidInThisMonth) {
            input.regularPayment - input.paidInThisMonth
        } else 0.0

    fun calculateTotalProjectedSavings(
        input: FirstBonusInput,
        additionalSavingsThisMonth: Double,
        monthsLeftInScheme: Int
    ) = input.currentBalance + additionalSavingsThisMonth + (input.regularPayment * monthsLeftInScheme)

    fun calculateTotalProjectedBonuses(
        projectedFirstBonus: Double,
        projectedFinalBonus: Double
    ) = projectedFirstBonus + projectedFinalBonus

    fun calculateProjectedSavingsFirstBonusPeriod(
        input: FirstBonusInput,
        additionalSavingsThisMonth: Double,
        monthsLeftInFirstTerm: Int
    ) = input.currentBalance + additionalSavingsThisMonth + (input.regularPayment * monthsLeftInFirstTerm)

    fun calculateHighestBalanceFirstBonusPeriod(
        input: FirstBonusInput,
        projectedSavingsFirstBonusPeriod: Double
    ) = input.balanceMustBeMoreThanForBonus.takeIf {
        it > projectedSavingsFirstBonusPeriod
    } ?: projectedSavingsFirstBonusPeriod

    fun calculateProjectedFirstBonus(highestBalanceFirstBonusPeriod: Double) =
            highestBalanceFirstBonusPeriod / 2

    fun calculateProjectedAdditionalSavingsFinalBonusPeriod(input: FirstBonusInput) = input.regularPayment * TWENTY_FOUR

    fun calculateProjectedFinalBonus(
        highestBalanceFinalBonusPeriod: Double,
        highestBalanceFirstBonusPeriod: Double
    ) = if (highestBalanceFinalBonusPeriod > highestBalanceFirstBonusPeriod) {
        (highestBalanceFinalBonusPeriod - highestBalanceFirstBonusPeriod) / 2
    } else 0.0

    fun calculateMonthsLeftInScheme(input: FirstBonusInput): Pair<Int, Int> {
        val thisMonthEndDate = input.thisMonthEndDate.convertToDateTime()
        val secondTermEndDate = input.secondTermEndDate.convertToDateTime()
        val firstTermEndDate = input.firstTermEndDate.convertToDateTime()
        val monthsLeftInScheme = thisMonthEndDate.monthsSince(secondTermEndDate)
        val monthsLeftInFirstTerm = thisMonthEndDate.monthsSince(firstTermEndDate)
        return Pair(monthsLeftInScheme, monthsLeftInFirstTerm)
    }

    companion object {
        private const val TWENTY_FOUR = 24
    }
}
