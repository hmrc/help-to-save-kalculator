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

import uk.gov.hmrc.helptosavecalculator.Calculator
import uk.gov.hmrc.helptosavecalculator.exceptions.InvalidRegularPaymentException
import uk.gov.hmrc.helptosavecalculator.models.MonthlyBreakdown
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CalculatorTest {
    @Test
    fun `Gives list of 48 months wit breakdown`() {
        assertEquals(
            MonthlyBreakdown(monthNumber = 1, balance = 50, secondYearBonus = 25.0, fourthYearBonus = 0.0),
            Calculator(regularPayment = 50).run().monthlyBreakdown[0])
        assertEquals(25.0, Calculator(regularPayment = 50).run().monthlyBreakdown[0].totalBonusToDate)

        assertEquals(
            MonthlyBreakdown(monthNumber = 2, balance = 100, secondYearBonus = 50.0, fourthYearBonus = 0.0),
            Calculator(regularPayment = 50).run().monthlyBreakdown[1])
        assertEquals(50.0, Calculator(regularPayment = 50).run().monthlyBreakdown[1].totalBonusToDate)

        assertEquals(
            MonthlyBreakdown(monthNumber = 3, balance = 150, secondYearBonus = 75.0, fourthYearBonus = 0.0),
            Calculator(regularPayment = 50).run().monthlyBreakdown[2])
        assertEquals(50.0, Calculator(regularPayment = 50).run().monthlyBreakdown[1].totalBonusToDate)

        assertEquals(
            MonthlyBreakdown(monthNumber = 24, balance = 1200, secondYearBonus = 600.0, fourthYearBonus = 0.0),
            Calculator(regularPayment = 50).run().monthlyBreakdown[23])
        assertEquals(600.0, Calculator(regularPayment = 50).run().monthlyBreakdown[23].totalBonusToDate)

        assertEquals(
            MonthlyBreakdown(
                monthNumber = 25, balance = 1250, secondYearBonus = 600.0, fourthYearBonus = 25.0),
            Calculator(regularPayment = 50).run().monthlyBreakdown[24])
        assertEquals(625.0, Calculator(regularPayment = 50).run().monthlyBreakdown[24].totalBonusToDate)

        assertEquals(
            MonthlyBreakdown(
                monthNumber = 48, balance = 2400, secondYearBonus = 600.0, fourthYearBonus = 600.0),
            Calculator(regularPayment = 50).run().monthlyBreakdown[47])
        assertEquals(1200.0, Calculator(regularPayment = 50).run().monthlyBreakdown[47].totalBonusToDate)
    }

    @Test
    fun `Throw Exception when regular payment is below 1`() {
        assertFailsWith<InvalidRegularPaymentException> {
            Calculator(regularPayment = 0).run()
        }
    }

    @Test
    fun `Throw Exception when regular payment is above 50`() {
        assertFailsWith<InvalidRegularPaymentException> {
            Calculator(regularPayment = 51).run()
        }
    }
}
