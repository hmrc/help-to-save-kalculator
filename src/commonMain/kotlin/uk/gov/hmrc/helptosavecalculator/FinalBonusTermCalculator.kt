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
import uk.gov.hmrc.helptosavecalculator.models.FinalBonusCalculatorResponse
import uk.gov.hmrc.helptosavecalculator.models.FinalBonusInput
import uk.gov.hmrc.helptosavecalculator.validation.RegularPaymentValidators

object FinalBonusTermCalculator {

    private val calculation = FinalBonusTermCalculation()

    fun runFinalBonusCalculator(input: FinalBonusInput): FinalBonusCalculatorResponse = calculateFinalBonus(input)

    private fun calculateFinalBonus(input: FinalBonusInput): FinalBonusCalculatorResponse {
        validateUserInput(input.regularPayment)

        val monthLeftInScheme = calculation.calculateMonthsLeftInScheme(input)
        val additionalSavingsThisMonth = calculation.calculateAdditionalSavingsThisMonth(input)
        val totalProjectedSavings = calculation.calculateTotalProjectedSavings(input,
                additionalSavingsThisMonth,
                monthLeftInScheme)
        val maybeHighestBalanceSoFar = calculation.calculateMaybeHighestBalanceSoFar(input)
        val highestBalanceFinalBonusPeriod = calculation.calculateHighestBalanceFinalBonusPeriod(
                maybeHighestBalanceSoFar,
                totalProjectedSavings)
        val totalProjectedBonuses = calculation.calculateTotalProjectedBonuses(
                highestBalanceFinalBonusPeriod,
                input)
        val totalProjectedSavingsIncludingBonuses = calculation.calculateTotalProjectedSavingsIncludeBonuses(
                totalProjectedSavings,
                totalProjectedBonuses)
        val finalBonusStatus = calculation.finalBonusStatus(input, monthLeftInScheme)

        return FinalBonusCalculatorResponse(
                totalProjectedSavingsIncludingBonuses,
                totalProjectedSavings,
                totalProjectedBonuses,
                finalBonusStatus
        )
    }

    private fun validateUserInput(regularPayment: Double) {
        if (!RegularPaymentValidators.isValidRegularPayments(regularPayment)) {
            throw InvalidRegularPaymentException(regularPayment)
        }
    }
}
