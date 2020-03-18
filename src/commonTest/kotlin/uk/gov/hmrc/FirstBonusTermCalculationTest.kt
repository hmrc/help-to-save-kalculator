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
import uk.gov.hmrc.helptosavecalculator.FirstBonusTermCalculation
import uk.gov.hmrc.helptosavecalculator.models.FirstBonusInput
import uk.gov.hmrc.helptosavecalculator.models.YearMonthDayInput

class FirstBonusTermCalculationTest {

    @Test
    fun `GIVEN calculateTotalProjectedSavingsIncludeBonuses called THEN return the total`() {
        val valueOne = 1.0
        val valueTwo = 2.0
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateTotalProjectedSavingsIncludeBonuses(valueOne, valueTwo)

        assertEquals(3.0, result)
    }

    @Test
    fun `GIVEN regular payment is above paid in this month WHEN calculateAdditionalSavingsThisMonth called THEN return the total`() {
        val input = FirstBonusInput(50.0,
                25.0,
                25.0,
                YearMonthDayInput(2020, 3),
                YearMonthDayInput(2022, 2, 28),
                YearMonthDayInput(2024, 2, 28),
                50.0)
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateAdditionalSavingsThisMonth(input)

        assertEquals(25.0, result)
    }

    @Test
    fun `GIVEN regular payment is below paid in this month WHEN calculateAdditionalSavingsThisMonth called THEN return zero`() {
        val input = FirstBonusInput(25.0,
                25.0,
                50.0,
                YearMonthDayInput(2020, 3),
                YearMonthDayInput(2022, 2, 28),
                YearMonthDayInput(2024, 2, 28),
                50.0)
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateAdditionalSavingsThisMonth(input)

        assertEquals(0.0, result)
    }

    @Test
    fun `GIVEN calculateTotalProjectedSavings called THEN return the total`() {
        val input = FirstBonusInput(25.0,
                25.0,
                50.0,
                YearMonthDayInput(2020, 3),
                YearMonthDayInput(2022, 2, 28),
                YearMonthDayInput(2024, 2, 28),
                50.0)
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateTotalProjectedSavings(input, 2.0, 10)

        assertEquals(277.0, result)
    }

    @Test
    fun `GIVEN calculateTotalProjectedBonuses called THEN return the total`() {
        val valueOne = 1.0
        val valueTwo = 2.0
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateTotalProjectedBonuses(valueOne, valueTwo)

        assertEquals(3.0, result)
    }

    @Test
    fun `GIVEN calculateProjectedSavingsFirstBonusPeriod called THEN return the total`() {
        val input = FirstBonusInput(25.0,
                25.0,
                50.0,
                YearMonthDayInput(2020, 3),
                YearMonthDayInput(2022, 2, 28),
                YearMonthDayInput(2024, 2, 28),
                50.0)
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateProjectedSavingsFirstBonusPeriod(input, 2.0, 10)

        assertEquals(277.0, result)
    }

    @Test
    fun `GIVEN balanceMustBeMoreThanForBonus is above projectedSavingsFirstBonusPeriod WHEN calculateHighestBalanceFirstBonusPeriod called THEN return balanceMustBeMoreThanForBonus`() {
        val input = FirstBonusInput(25.0,
                25.0,
                50.0,
                YearMonthDayInput(2020, 3),
                YearMonthDayInput(2022, 2, 28),
                YearMonthDayInput(2024, 2, 28),
                50.0)
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateHighestBalanceFirstBonusPeriod(input, 2.0)

        assertEquals(50.0, result)
    }

    @Test
    fun `GIVEN balanceMustBeMoreThanForBonus is below projectedSavingsFirstBonusPeriod WHEN calculateHighestBalanceFirstBonusPeriod called THEN return projectedSavingsFirstBonusPeriod`() {
        val input = FirstBonusInput(25.0,
                25.0,
                50.0,
                YearMonthDayInput(2020, 3),
                YearMonthDayInput(2022, 2, 28),
                YearMonthDayInput(2024, 2, 28),
                1.0)
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateHighestBalanceFirstBonusPeriod(input, 2.0)

        assertEquals(2.0, result)
    }

    @Test
    fun `GIVEN calculateProjectedFirstBonus called THEN return the total`() {
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateProjectedFirstBonus(10.0)

        assertEquals(5.0, result)
    }

    @Test
    fun `GIVEN calculateProjectedAdditionalSavingsFinalBonusPeriod called THEN return the total`() {
        val input = FirstBonusInput(10.0,
                25.0,
                50.0,
                YearMonthDayInput(2020, 3),
                YearMonthDayInput(2022, 2, 28),
                YearMonthDayInput(2024, 2, 28),
                1.0)
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateProjectedAdditionalSavingsFinalBonusPeriod(input)

        assertEquals(240.0, result)
    }

    @Test
    fun `GIVEN highestBalanceFinalBonusPeriod is above highestBalanceFirstBonusPeriod WHEN calculateProjectedFinalBonus called THEN return the total`() {
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateProjectedFinalBonus(10.0, 5.0)

        assertEquals(2.5, result)
    }

    @Test
    fun `GIVEN highestBalanceFinalBonusPeriod is below highestBalanceFirstBonusPeriod WHEN calculateProjectedFinalBonus called THEN return zero`() {
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateProjectedFinalBonus(5.0, 10.0)

        assertEquals(0.0, result)
    }

    @Test
    fun `GIVEN calculateMonthsLeftInScheme called THEN return the remaining months left in scheme`() {
        val input = FirstBonusInput(10.0,
                25.0,
                50.0,
                YearMonthDayInput(2020, 3),
                YearMonthDayInput(2022, 2, 28),
                YearMonthDayInput(2024, 2, 28),
                1.0)
        val calculation = FirstBonusTermCalculation()
        val result = calculation.calculateMonthsLeftInScheme(input)

        assertEquals(Pair(47, 23), result)
    }
}
