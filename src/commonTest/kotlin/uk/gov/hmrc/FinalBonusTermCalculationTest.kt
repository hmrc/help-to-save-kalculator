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
package uk.gov.hmrc

import kotlin.test.Test
import kotlin.test.assertEquals
import uk.gov.hmrc.helptosavecalculator.FinalBonusTermCalculation
import uk.gov.hmrc.helptosavecalculator.models.FinalBonusInput
import uk.gov.hmrc.helptosavecalculator.models.FinalBonusStatus
import uk.gov.hmrc.helptosavecalculator.models.YearMonthDayInput

class FinalBonusTermCalculationTest {

    @Test
    fun `GIVEN regular payment is above paid in this month WHEN calculateAdditionalSavingsThisMonth called THEN return the total`() {
        val input = FinalBonusInput(25.0,
                0.0,
                10.0,
                40.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                0.0,
                0.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateAdditionalSavingsThisMonth(input)

        assertEquals(15.0, result)
    }

    @Test
    fun `GIVEN regular payment is below paid in this month WHEN calculateAdditionalSavingsThisMonth called THEN return zero`() {
        val input = FinalBonusInput(10.0,
                0.0,
                25.0,
                25.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                0.0,
                0.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateAdditionalSavingsThisMonth(input)

        assertEquals(0.0, result)
    }

    @Test
    fun `GIVEN calculateTotalProjectedSavingsIncludeBonuses called THEN return the total`() {
        val valueOne = 1.0
        val valueTwo = 2.0
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateTotalProjectedSavingsIncludeBonuses(valueOne, valueTwo)

        assertEquals(3.0, result)
    }

    @Test
    fun `GIVEN calculateTotalProjectedSavings called THEN return the total`() {
        val input = FinalBonusInput(10.0,
                0.0,
                25.0,
                25.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                0.0,
                0.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateTotalProjectedSavings(input, 2.0, 10)

        assertEquals(102.0, result)
    }

    @Test
    fun `GIVEN highest balance in final term is above highest balance in first term WHEN calculateTotalProjectedBonuses called THEN return the total`() {
        val input = FinalBonusInput(10.0,
                0.0,
                25.0,
                25.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                2.0,
                0.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateTotalProjectedBonuses(10.0, input)

        assertEquals(4.0, result)
    }

    @Test
    fun `GIVEN highest balance in final term is below highest balance in first term WHEN calculateTotalProjectedBonuses called THEN return zero`() {
        val input = FinalBonusInput(10.0,
                0.0,
                25.0,
                25.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                10.0,
                0.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateTotalProjectedBonuses(2.0, input)

        assertEquals(0.0, result)
    }

    @Test
    fun `GIVEN calculateMaybeHighestBalanceSoFar called THEN return the total`() {
        val input = FinalBonusInput(10.0,
                0.0,
                25.0,
                25.0,
                YearMonthDayInput(2022, 3),
                YearMonthDayInput(2024, 2, 28),
                10.0,
                5.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateMaybeHighestBalanceSoFar(input)

        assertEquals(20.0, result)
    }

    @Test
    fun `GIVEN highest balance so far is above total projected savings WHEN calculateHighestBalanceFinalBonusPeriod called THEN return highest balance so far`() {
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateHighestBalanceFinalBonusPeriod(10.0, 5.0)

        assertEquals(10.0, result)
    }

    @Test
    fun `GIVEN highest balance so far is below total projected savings WHEN calculateHighestBalanceFinalBonusPeriod called THEN return projected savings`() {
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateHighestBalanceFinalBonusPeriod(5.0, 10.0)

        assertEquals(10.0, result)
    }

    @Test
    fun `GIVEN calculateMonthsLeftInScheme called THEN return the remaining months left in scheme`() {
        val input = FinalBonusInput(10.0,
                0.0,
                25.0,
                25.0,
                YearMonthDayInput(2023, 3),
                YearMonthDayInput(2024, 2, 28),
                10.0,
                5.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.calculateMonthsLeftInScheme(input)

        assertEquals(11, result)
    }

    @Test
    fun `GIVEN customer already earned final bonus WHEN finalBonusStatus called THEN return earned`() {
        val input = FinalBonusInput(10.0,
                0.0,
                25.0,
                25.0,
                YearMonthDayInput(2023, 3),
                YearMonthDayInput(2024, 2, 28),
                10.0,
                5.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.finalBonusStatus(input, 23)

        assertEquals(FinalBonusStatus.EARNED, result)
    }

    @Test
    fun `GIVEN customer can possibly earn a final bonus WHEN finalBonusStatus called THEN return possibleToEarn`() {
        val input = FinalBonusInput(10.0,
                0.0,
                25.0,
                25.0,
                YearMonthDayInput(2023, 3),
                YearMonthDayInput(2024, 2, 28),
                10.0,
                0.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.finalBonusStatus(input, 23)

        assertEquals(FinalBonusStatus.POSSIBLE_TO_EARN, result)
    }

    @Test
    fun `GIVEN customer can not earn final bonus WHEN finalBonusStatus called THEN return cannotEarn`() {
        val input = FinalBonusInput(50.0,
                0.0,
                0.0,
                25.0,
                YearMonthDayInput(2023, 3),
                YearMonthDayInput(2024, 2, 28),
                1200.0,
                0.0)
        val calculation = FinalBonusTermCalculation()
        val result = calculation.finalBonusStatus(input, 22)

        assertEquals(FinalBonusStatus.CANNOT_EARN, result)
    }
}
