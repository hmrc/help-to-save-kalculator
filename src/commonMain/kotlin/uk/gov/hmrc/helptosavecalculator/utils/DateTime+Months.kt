package uk.gov.hmrc.helptosavecalculator.utils

import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeRange

internal fun DateTime.monthsSince(now: DateTime = DateTime.now()): Int {
    return DateTimeRange(this.startOfMonth, now.startOfMonth).span.totalMonths
}