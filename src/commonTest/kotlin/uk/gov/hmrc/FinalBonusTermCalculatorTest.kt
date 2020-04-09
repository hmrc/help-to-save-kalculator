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
import uk.gov.hmrc.helptosavecalculator.FinalBonusTermCalculator.runFinalBonusCalculator
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidRegularPaymentException
import uk.gov.hmrc.helptosavecalculator.models.FinalBonusInput
import uk.gov.hmrc.helptosavecalculator.models.FinalBonusStatus
import uk.gov.hmrc.helptosavecalculator.models.YearMonthDayInput

class FinalBonusTermCalculatorTest {

    @Test
    fun `GIVEN regular payment is below 1 THEN InvalidRegularPaymentException thrown`() {
        val input = FinalBonusInput(0.0,
                0.0,
                10.0,
                40.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                0.0,
                0.0)
        assertFailsWith<InvalidRegularPaymentException> {
            runFinalBonusCalculator(input)
        }
    }

    @Test
    fun `GIVEN regular payment is above 50 THEN InvalidRegularPaymentException thrown`() {
        val input = FinalBonusInput(51.0,
                0.0,
                10.0,
                40.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                0.0,
                0.0)
        assertFailsWith<InvalidRegularPaymentException> {
            runFinalBonusCalculator(input)
        }
    }

    @Test
    fun `GIVEN account just reached final term with no payment WHEN 1 pound regular payment added THEN correct calculation displayed`() {
        val input = FinalBonusInput(1.0,
                0.0,
                0.0,
                50.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                0.0,
                0.0)
        val calculator = runFinalBonusCalculator(input)

        assertEquals(36.0, calculator.totalProjectedSavingsIncludingBonuses)
        assertEquals(24.0, calculator.totalProjectedSavings)
        assertEquals(12.0, calculator.totalProjectedBonuses)
        assertEquals(FinalBonusStatus.POSSIBLE_TO_EARN, calculator.finalBonusStatus)
    }

    @Test
    fun `GIVEN account just reached final term with no payment WHEN 25 pound regular payment added THEN correct calculation displayed`() {
        val input = FinalBonusInput(25.0,
                0.0,
                0.0,
                50.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                0.0,
                0.0)
        val calculator = runFinalBonusCalculator(input)

        assertEquals(900.0, calculator.totalProjectedSavingsIncludingBonuses)
        assertEquals(600.0, calculator.totalProjectedSavings)
        assertEquals(300.0, calculator.totalProjectedBonuses)
        assertEquals(FinalBonusStatus.POSSIBLE_TO_EARN, calculator.finalBonusStatus)
    }

    @Test
    fun `GIVEN account just reached final term with no payment WHEN 50 pound regular payment added THEN correct calculation displayed`() {
        val input = FinalBonusInput(50.0,
                0.0,
                0.0,
                50.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                0.0,
                0.0)
        val calculator = runFinalBonusCalculator(input)

        assertEquals(1800.0, calculator.totalProjectedSavingsIncludingBonuses)
        assertEquals(1200.0, calculator.totalProjectedSavings)
        assertEquals(600.0, calculator.totalProjectedBonuses)
        assertEquals(FinalBonusStatus.POSSIBLE_TO_EARN, calculator.finalBonusStatus)
    }

    @Test
    fun `GIVEN account just reached final term with 50 pound paid current month AND withdrawn 25 WHEN 25 pound regular payment added THEN correct calculation displayed`() {
        val input = FinalBonusInput(25.0,
                25.0,
                50.0,
                50.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                0.0,
                25.0)
        val calculator = runFinalBonusCalculator(input)

        assertEquals(900.0, calculator.totalProjectedSavingsIncludingBonuses)
        assertEquals(600.0, calculator.totalProjectedSavings)
        assertEquals(300.0, calculator.totalProjectedBonuses)
        assertEquals(FinalBonusStatus.EARNED, calculator.finalBonusStatus)
    }

    @Test
    fun `GIVEN account can not longer earn final bonus WHEN calculator called THEN can earn final bonus return false`() {
        val input = FinalBonusInput(25.0,
                0.0,
                0.0,
                50.0,
                YearMonthDayInput(2024, 2),
                YearMonthDayInput(2024, 2, 28),
                1200.0,
                0.0)
        val calculator = runFinalBonusCalculator(input)

        assertEquals(25.0, calculator.totalProjectedSavingsIncludingBonuses)
        assertEquals(25.0, calculator.totalProjectedSavings)
        assertEquals(0.0, calculator.totalProjectedBonuses)
        assertEquals(FinalBonusStatus.CANNOT_EARN, calculator.finalBonusStatus)
    }
}
