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

import uk.gov.hmrc.helptosavecalculator.models.FirstBonusCalculatorResponse
import uk.gov.hmrc.helptosavecalculator.models.FirstBonusInput

object FirstBonusTermCalculator : FirstBonusTermCalculation() {

    fun runFirstBonusCalculator(input: FirstBonusInput): FirstBonusCalculatorResponse {
        return calculateFirstBonus(input)
    }

    private fun calculateFirstBonus(input: FirstBonusInput): FirstBonusCalculatorResponse {
        validateUserInput(input.regularPayment)

        val monthLeftInScheme = calculateMonthsLeftInScheme(input)
        val monthLetInFirstTerm = calculateMonthsLeftInFirstTerm(input)
        val additionalSavingsThisMonth = calculateAdditionalSavingsThisMonth(input)
        val totalProjectedSavings = calculateTotalProjectedSavings(input, additionalSavingsThisMonth, monthLeftInScheme)
        val projectedSavingsFirstBonusPeriod = calculateProjectedSavingsFirstBonusPeriod(input, additionalSavingsThisMonth, monthLetInFirstTerm)
        val highestBalanceFirstBonusPeriod = calculateHighestBalanceFirstBonusPeriod(input, projectedSavingsFirstBonusPeriod)
        val projectedFirstBonus = calculateProjectedFirstBonus(highestBalanceFirstBonusPeriod)
        val projectedAdditionalSavingsFinalBonusPeriod = calculateProjectedAdditionalSavingsFinalBonusPeriod(input)
        val projectedFinalBonus = calculateProjectedFinalBonus(totalProjectedSavings, highestBalanceFirstBonusPeriod)
        val totalProjectedBonuses = calculateTotalProjectedBonuses(projectedFirstBonus, projectedFinalBonus)
        val totalProjectedSavingsIncludingBonuses = calculateTotalProjectedSavingsIncludeBonuses(totalProjectedSavings, totalProjectedBonuses)

        return FirstBonusCalculatorResponse(
                totalProjectedSavingsIncludingBonuses,
                totalProjectedSavings,
                totalProjectedBonuses,
                projectedSavingsFirstBonusPeriod,
                projectedFirstBonus,
                projectedAdditionalSavingsFinalBonusPeriod,
                projectedFinalBonus
        )
    }
}
