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

import com.soywiz.klock.DateTime
import com.soywiz.klock.MonthSpan
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import uk.gov.hmrc.helptosavecalculator.Calculator
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidRegularPaymentException
import uk.gov.hmrc.helptosavecalculator.models.MonthlyBreakdown

class CalculatorTest {
    @Test
    fun `Gives list of 48 months with breakdown`() {
        assertEquals(
            MonthlyBreakdown(monthNumber = 1, savingsToDate = 50.0, period1Bonus = 25.0, period2Bonus = 0.0),
            Calculator.run(regularPayment = 50.0).monthlyBreakdown[0])
        assertEquals(25.0, Calculator.run(regularPayment = 50.0).monthlyBreakdown[0].bonusToDate)

        assertEquals(
            MonthlyBreakdown(monthNumber = 2, savingsToDate = 100.0, period1Bonus = 50.0, period2Bonus = 0.0),
            Calculator.run(regularPayment = 50.0).monthlyBreakdown[1])
        assertEquals(50.0, Calculator.run(regularPayment = 50.0).monthlyBreakdown[1].bonusToDate)

        assertEquals(
            MonthlyBreakdown(monthNumber = 3, savingsToDate = 150.0, period1Bonus = 75.0, period2Bonus = 0.0),
            Calculator.run(regularPayment = 50.0).monthlyBreakdown[2])
        assertEquals(50.0, Calculator.run(regularPayment = 50.0).monthlyBreakdown[1].bonusToDate)

        assertEquals(
            MonthlyBreakdown(monthNumber = 24, savingsToDate = 1200.0, period1Bonus = 600.0, period2Bonus = 0.0),
            Calculator.run(regularPayment = 50.0).monthlyBreakdown[23])
        assertEquals(600.0, Calculator.run(regularPayment = 50.0).monthlyBreakdown[23].bonusToDate)

        assertEquals(
            MonthlyBreakdown(
                monthNumber = 25, savingsToDate = 1250.0, period1Bonus = 600.0, period2Bonus = 25.0),
            Calculator.run(regularPayment = 50.0).monthlyBreakdown[24])
        assertEquals(625.0, Calculator.run(regularPayment = 50.0).monthlyBreakdown[24].bonusToDate)

        assertEquals(
            MonthlyBreakdown(
                monthNumber = 48, savingsToDate = 2400.0, period1Bonus = 600.0, period2Bonus = 600.0),
            Calculator.run(regularPayment = 50.0).monthlyBreakdown[47])
        assertEquals(1200.0, Calculator.run(regularPayment = 50.0).monthlyBreakdown[47].bonusToDate)
    }

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
    fun `Month breakdown if they have saved in the first 2 months`() {
        val calculator = Calculator.run(
            regularPayment = 50.0,
            currentBalance = 100.0,
            currentFirstPeriodBonus = 50.0,
            currentSecondPeriodBonus = 0.0,
            accountStartDate = DateTime.now().minus(
                MonthSpan(2)))

        assertEquals(2400.0, calculator.endOfSchemeSavings)
        assertEquals(1200.0, calculator.endOfSchemeBonus)
        assertEquals(3600.0, calculator.endOfSchemeTotal)

        assertEquals(600.0, calculator.endOfPeriod1Bonus)
        assertEquals(1200.0, calculator.endOfPeriod1Savings)
        assertEquals(1800.0, calculator.endOfPeriod1Total)

        assertEquals(600.0, calculator.endOfPeriod2Bonus)
        assertEquals(1200.0, calculator.endOfPeriod2Savings)
        assertEquals(1800.0, calculator.endOfPeriod2Total)
    }

    @Test
    fun `Month breakdown if they have not saved in the first 2 months`() {
        val calculator = Calculator.run(
            regularPayment = 50.0,
            currentBalance = 0.0,
            currentFirstPeriodBonus = 0.0,
            currentSecondPeriodBonus = 0.0,
            accountStartDate = DateTime.now().minus(
                MonthSpan(2)))
        assertEquals(2300.0, calculator.endOfSchemeSavings)
        assertEquals(1150.0, calculator.endOfSchemeBonus)
        assertEquals(3450.0, calculator.endOfSchemeTotal)

        assertEquals(550.0, calculator.endOfPeriod1Bonus)
        assertEquals(1100.0, calculator.endOfPeriod1Savings)
        assertEquals(1650.0, calculator.endOfPeriod1Total)
        assertEquals(600.0, calculator.endOfPeriod2Bonus)
        assertEquals(1200.0, calculator.endOfPeriod2Savings)
        assertEquals(1800.0, calculator.endOfPeriod2Total)
    }

    @Test
    fun `Month breakdown if they have not saved in the first 24 months`() {
        val calculator = Calculator.run(
            regularPayment = 50.0,
            currentBalance = 0.0,
            currentFirstPeriodBonus = 0.0,
            currentSecondPeriodBonus = 0.0,
            accountStartDate = DateTime.now().minus(
                MonthSpan(24)))
        assertEquals(1800.0, calculator.endOfSchemeTotal)
        assertEquals(1200.0, calculator.endOfSchemeSavings)
        assertEquals(600.0, calculator.endOfSchemeBonus)
        assertEquals(0.0, calculator.endOfPeriod1Bonus)
        assertEquals(0.0, calculator.endOfPeriod1Savings)
        assertEquals(0.0, calculator.endOfPeriod1Total)
        assertEquals(600.0, calculator.endOfPeriod2Bonus)
        assertEquals(1200.0, calculator.endOfPeriod2Savings)
        assertEquals(1800.0, calculator.endOfPeriod2Total)
    }
}
