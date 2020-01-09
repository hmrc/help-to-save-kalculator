package uk.gov.hmrc.helptosavecalculator.utils

import com.soywiz.klock.DateTime
import com.soywiz.klock.MonthSpan
import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeMonthsTest {

    @Test
    fun `Get Months when comparing 2 DateTime now`() {
        assertEquals(0, DateTime.now().monthsSince(DateTime.now()))
    }

    @Test
    fun `Get Months when comparing 1st Jan, 1st December (previous year)`() {
        assertEquals(
            1, DateTime(year = DateTime.now().year.minus(1).year, month = 12, day = 1) //1st December last year
                .monthsSince(
                    DateTime(
                        year = DateTime.now().year.year, month = 1, day = 1))) //1st Jan this year
    }

    @Test
    fun `Get Months when comparing 1st Jan, 31st Jan`() {
        assertEquals(
            0, DateTime(year = DateTime.now().year.year, month = 1, day = 1) //1st Jan this year
                .monthsSince(
                    DateTime(
                        year = DateTime.now().year.year, month = 1, day = 31)))
    }

    @Test
    fun `Get Months when comparing 1st Jan, 1st Jan 4 Years later`() {
        assertEquals(
            48, DateTime(year = DateTime.now().year.year, month = 1, day = 1) //1st Jan this year
                .monthsSince(
                    DateTime(
                        year = DateTime.now().year.plus(4).year, month = 1, day = 1)))
    }

    @Test
    fun `Get Months when comparing 1st of last month, with now`() {
        assertEquals(
            1, DateTime.now().minus(MonthSpan(1)).startOfMonth
                .monthsSince())
    }

}