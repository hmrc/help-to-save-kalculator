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
import uk.gov.hmrc.helptosavecalculator.models.FirstBonusCalculatorResponse
import uk.gov.hmrc.helptosavecalculator.models.FirstBonusInput
import uk.gov.hmrc.helptosavecalculator.validation.RegularPaymentValidators

object FirstBonusTermCalculator {

    private val calculation = FirstBonusTermCalculation()

    fun runFirstBonusCalculator(input: FirstBonusInput) = calculateFirstBonus(input)

    private fun calculateFirstBonus(input: FirstBonusInput): FirstBonusCalculatorResponse {
        validateUserInput(input.regularPayment)

        val (monthLeftInScheme, monthLeftInFirstTerm) = calculation.calculateMonthsLeftInScheme(input)
        val additionalSavingsThisMonth = calculation.calculateAdditionalSavingsThisMonth(input)
        val totalProjectedSavings = calculation.calculateTotalProjectedSavings(input,
                additionalSavingsThisMonth,
                monthLeftInScheme)
        val projectedSavingsFirstBonusPeriod = calculation.calculateProjectedSavingsFirstBonusPeriod(input,
                additionalSavingsThisMonth,
                monthLeftInFirstTerm)
        val highestBalanceFirstBonusPeriod = calculation.calculateHighestBalanceFirstBonusPeriod(input,
                projectedSavingsFirstBonusPeriod)
        val projectedFirstBonus = calculation.calculateProjectedFirstBonus(highestBalanceFirstBonusPeriod)
        val projectedAdditionalSavingsFinalBonusPeriod =
                calculation.calculateProjectedAdditionalSavingsFinalBonusPeriod(input)
        val projectedFinalBonus = calculation.calculateProjectedFinalBonus(totalProjectedSavings,
                highestBalanceFirstBonusPeriod)
        val totalProjectedBonuses = calculation.calculateTotalProjectedBonuses(projectedFirstBonus,
                projectedFinalBonus)
        val totalProjectedSavingsIncludingBonuses =
                calculation.calculateTotalProjectedSavingsIncludeBonuses(totalProjectedSavings,
                totalProjectedBonuses)

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

    private fun validateUserInput(regularPayment: Double) {
        if (!RegularPaymentValidators.isValidRegularPayments(regularPayment)) {
            throw InvalidRegularPaymentException(regularPayment)
        }
    }
}
