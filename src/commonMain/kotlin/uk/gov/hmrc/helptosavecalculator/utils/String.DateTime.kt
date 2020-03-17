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
package uk.gov.hmrc.helptosavecalculator.utils

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.parse

internal fun String.convertYearMonthToDateTime(): DateTime {
    val dateFormat = DateFormat("yyyy-MM-dd")
    return dateFormat.parse("$this-01").local
}

internal fun String.convertYearMonthDayToDateTime(): DateTime {
    val dateFormat = DateFormat("yyyy-MM-dd")
    return dateFormat.parse(this).local
}
