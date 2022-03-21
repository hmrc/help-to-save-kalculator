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

import uk.gov.hmrc.helptosavecalculator.models.FinalBonusInput
import uk.gov.hmrc.helptosavecalculator.models.FinalBonusStatus
import uk.gov.hmrc.helptosavecalculator.utils.monthsSince

internal class FinalBonusTermCalculation {

    fun calculateAdditionalSavingsThisMonth(input: FinalBonusInput) =
        if (input.regularPayment > input.paidInThisMonth) {
            input.regularPayment - input.paidInThisMonth
        } else 0.0

    fun calculateTotalProjectedSavingsIncludeBonuses(
        totalProjectedSavings: Double,
        totalProjectedBonuses: Double
    ) = totalProjectedSavings + totalProjectedBonuses

    fun calculateTotalProjectedSavings(
        input: FinalBonusInput,
        additionalSavingsThisMonth: Double,
        monthsLeftInScheme: Int
    ) = input.currentBalance + additionalSavingsThisMonth + (input.regularPayment * monthsLeftInScheme)

    fun calculateTotalProjectedBonuses(
        highestBalanceFinalBonusPeriod: Double,
        input: FinalBonusInput
    ) = if (highestBalanceFinalBonusPeriod > input.balanceMustBeMoreThanForBonus) {
            (highestBalanceFinalBonusPeriod - input.balanceMustBeMoreThanForBonus) / 2
        } else 0.0

    fun calculateMaybeHighestBalanceSoFar(input: FinalBonusInput) =
            input.balanceMustBeMoreThanForBonus + (input.secondTermBonusEstimate * 2)

    fun calculateHighestBalanceFinalBonusPeriod(
        highestBalanceSoFar: Double,
        totalProjectedSavings: Double
    ) = (highestBalanceSoFar).takeIf {
        it > totalProjectedSavings
    } ?: totalProjectedSavings

    fun finalBonusStatus(
        input: FinalBonusInput,
        monthsLeftInScheme: Int
    ): FinalBonusStatus {
        val highestPossibleBalance = input.currentBalance + input.canPayInThisMonth + (monthsLeftInScheme * 50)
        return if (input.secondTermBonusEstimate > 0.0) FinalBonusStatus.EARNED else {
            if (highestPossibleBalance > input.balanceMustBeMoreThanForBonus)
                FinalBonusStatus.POSSIBLE_TO_EARN else FinalBonusStatus.CANNOT_EARN
        }
    }

    fun calculateMonthsLeftInScheme(input: FinalBonusInput): Int {
        val thisMonthEndDate = input.thisMonthEndDate.convertToDateTime()
        val secondTermEndDate = input.secondTermEndDate.convertToDateTime()
        return thisMonthEndDate.monthsSince(secondTermEndDate)
    }
}
