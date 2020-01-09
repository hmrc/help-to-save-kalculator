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
        val calculatorResponse = CalculatorResponse(50, listOf<MonthlyBreakdown>(), 2400, 600.0, 600.0)

        assertEquals(calculatorResponse.finalTotalBonus, 1200.0)
        assertEquals(calculatorResponse.monthlyPayments, 50)
        assertEquals(calculatorResponse.monthlyBreakdown, listOf<MonthlyBreakdown>())
        assertEquals(calculatorResponse.finalBalance, 2400)
        assertEquals(calculatorResponse.finalSecondYearBonus, 600.0)
        assertEquals(calculatorResponse.finalFourthYearBonus, 600.0)
        assertEquals(calculatorResponse.finalTotalBonus, 1200.0)
    }

    @Test
    fun `Check getters on MonthlyBreakdown`() {
        val monthlyBreakdown = MonthlyBreakdown(
            monthNumber = 1, balance = 50, secondYearBonus = 25.0, fourthYearBonus = 0.0)

        // val balance: Int, val secondYearBonus: Double, val fourthYearBonus: Double

        assertEquals(monthlyBreakdown.monthNumber, 1)
        assertEquals(monthlyBreakdown.balance, 50)
        assertEquals(monthlyBreakdown.secondYearBonus, 25.0)
        assertEquals(monthlyBreakdown.fourthYearBonus, 0.0)
        assertEquals(monthlyBreakdown.totalBonusToDate, 25.0)
    }
}
