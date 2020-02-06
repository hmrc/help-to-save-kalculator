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
package uk.gov.hmrc.helptosavecalculator.utils.validators

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import uk.gov.hmrc.helptosavecalculator.validation.RegularPaymentValidators

class RegularPaymentValidatorsTests {

    @Test
    fun `Validate RegularPayments below minimum`() {
        assertFalse(RegularPaymentValidators.isAboveMinimumRegularPayments(0.0))
    }

    @Test
    fun `Validate RegularPayments above zero`() {
        assertTrue(RegularPaymentValidators.isAboveMinimumRegularPayments(1.0))
    }

    @Test
    fun `Validate RegularPayments below max`() {
        assertTrue(RegularPaymentValidators.isBelowMaximumRegularPayments(50.0))
    }

    @Test
    fun `Validate RegularPayments above max`() {
        assertFalse(RegularPaymentValidators.isBelowMaximumRegularPayments(51.0))
    }
}
