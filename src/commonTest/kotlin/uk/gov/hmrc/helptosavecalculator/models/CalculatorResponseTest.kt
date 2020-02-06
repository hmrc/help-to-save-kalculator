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
package uk.gov.hmrc.helptosavecalculator.models

import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorResponseTest {

    @Test
    fun `Check getters on Calculator Response`() {
        val calculatorResponse = CalculatorResponse(
            monthlyPayments = 50.0,
            monthlyBreakdown = listOf<MonthlyBreakdown>(),
            endOfSchemeSavings = 2400.0,
            endOfPeriod1Bonus = 600.0,
            endOfPeriod1Savings = 1200.0,
            endOfPeriod2Bonus = 600.0,
            endOfPeriod2Savings = 1200.0)

        assertEquals(calculatorResponse.endOfSchemeBonus, 1200.0)
        assertEquals(calculatorResponse.monthlyPayments, 50.0)
        assertEquals(calculatorResponse.monthlyBreakdown, listOf<MonthlyBreakdown>())
        assertEquals(calculatorResponse.endOfSchemeSavings, 2400.0)
        assertEquals(calculatorResponse.endOfSchemeBonus, 1200.0)
        assertEquals(calculatorResponse.endOfSchemeTotal, 3600.0)

        assertEquals(calculatorResponse.endOfPeriod1Bonus, 600.0)
        assertEquals(calculatorResponse.endOfPeriod1Savings, 1200.0)
        assertEquals(calculatorResponse.endOfPeriod1Total, 1800.0)
        assertEquals(calculatorResponse.endOfPeriod2Bonus, 600.0)
        assertEquals(calculatorResponse.endOfPeriod2Savings, 1200.0)
        assertEquals(calculatorResponse.endOfPeriod2Total, 1800.0)
    }

    @Test
    fun `Check getters on MonthlyBreakdown`() {
        val monthlyBreakdown = MonthlyBreakdown(
            monthNumber = 1, savingsToDate = 50.0, period1Bonus = 25.0, period2Bonus = 0.0)

        // val balance: Int, val secondYearBonus: Double, val fourthYearBonus: Double

        assertEquals(monthlyBreakdown.monthNumber, 1)
        assertEquals(monthlyBreakdown.savingsToDate, 50.0)
        assertEquals(monthlyBreakdown.period1Bonus, 25.0)
        assertEquals(monthlyBreakdown.period2Bonus, 0.0)
        assertEquals(monthlyBreakdown.bonusToDate, 25.0)
    }
}
