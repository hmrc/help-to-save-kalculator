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
package uk.gov.hmrc

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import uk.gov.hmrc.helptosavecalculator.Calculator
import uk.gov.hmrc.helptosavecalculator.FirstBonusTermCalculator.runFirstBonusCalculator
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidRegularPaymentException
import uk.gov.hmrc.helptosavecalculator.models.FirstBonusInput

class FirstBonusTermCalculatorTest {
    @Test
    fun `Throw Exception when regular payment is below 1`() {
        assertFailsWith<InvalidRegularPaymentException> {
            Calculator.run(regularPayment = 0.0)
        }
    }

    @Test
    fun `Throw Exception when regular payment is above 50`() {
        assertFailsWith<InvalidRegularPaymentException> {
            Calculator.run(regularPayment = 51.0)
        }
    }

    @Test
    fun `A new account with no payment AND 1 pound regular payment`() {
        val input = FirstBonusInput(1.0,
                0.0,
                0.0,
                "2020-03",
                "2022-02-28",
                "2024-02-28",
                0.0)
        val calculator = runFirstBonusCalculator(input)

        assertEquals(72.00, calculator.totalProjectedSavingsIncludingBonuses)
        assertEquals(48.00, calculator.totalProjectedSavings)
        assertEquals(24.00, calculator.totalProjectedBonuses)
        assertEquals(24.00, calculator.projectedSavingsFirstBonusPeriod)
        assertEquals(12.00, calculator.projectedFirstBonus)
        assertEquals(24.00, calculator.projectedAdditionalSavingsFinalBonusPeriod)
        assertEquals(12.00, calculator.projectedFinalBonus)
    }

    @Test
    fun `A new account with no payment AND 25 pound regular payment`() {
        val input = FirstBonusInput(25.0,
                0.0,
                0.0,
                "2020-03",
                "2022-02-28",
                "2024-02-28",
                0.0)
        val calculator = runFirstBonusCalculator(input)

        assertEquals(1800.00, calculator.totalProjectedSavingsIncludingBonuses)
        assertEquals(1200.00, calculator.totalProjectedSavings)
        assertEquals(600.00, calculator.totalProjectedBonuses)
        assertEquals(600.00, calculator.projectedSavingsFirstBonusPeriod)
        assertEquals(300.00, calculator.projectedFirstBonus)
        assertEquals(600.00, calculator.projectedAdditionalSavingsFinalBonusPeriod)
        assertEquals(300.00, calculator.projectedFinalBonus)
    }

    @Test
    fun `A new account with no payment AND 50 pound regular payment`() {
        val input = FirstBonusInput(50.0,
                0.0,
                0.0,
                "2020-03",
                "2022-02-28",
                "2024-02-28",
                0.0)
        val calculator = runFirstBonusCalculator(input)

        assertEquals(3600.00, calculator.totalProjectedSavingsIncludingBonuses)
        assertEquals(2400.00, calculator.totalProjectedSavings)
        assertEquals(1200.00, calculator.totalProjectedBonuses)
        assertEquals(1200.00, calculator.projectedSavingsFirstBonusPeriod)
        assertEquals(600.00, calculator.projectedFirstBonus)
        assertEquals(1200.00, calculator.projectedAdditionalSavingsFinalBonusPeriod)
        assertEquals(600.00, calculator.projectedFinalBonus)
    }

    @Test
    fun `A new account paid in 50 first month, withdrawn 256 AND 25 pounds regular payment`() {
        val input = FirstBonusInput(25.0,
                25.0,
                50.0,
                "2020-03",
                "2022-02-28",
                "2024-02-28",
                50.0)
        val calculator = runFirstBonusCalculator(input)

        assertEquals(1800.00, calculator.totalProjectedSavingsIncludingBonuses)
        assertEquals(1200.00, calculator.totalProjectedSavings)
        assertEquals(600.00, calculator.totalProjectedBonuses)
        assertEquals(600.00, calculator.projectedSavingsFirstBonusPeriod)
        assertEquals(300.00, calculator.projectedFirstBonus)
        assertEquals(600.00, calculator.projectedAdditionalSavingsFinalBonusPeriod)
        assertEquals(300.00, calculator.projectedFinalBonus)
    }
}
