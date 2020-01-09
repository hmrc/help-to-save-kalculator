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
package uk.gov.hmrc.helptosavecalculator.exceptions

class InvalidRegularPaymentException(regularPayment: Int) :
    Exception("Regular payment must be between 1 and 50. You provided $regularPayment")

class InvalidAccountStartDate : Exception("You have provided a current balance but no account start date")

class InvalidCurrentBalance : Exception("You have provided an account start date but no current balance")

class InvalidCurrentBonusAmount :
    Exception("You have provided an current balance so you provide current bonus amounts for the first and second period")