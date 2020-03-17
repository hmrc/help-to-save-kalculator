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