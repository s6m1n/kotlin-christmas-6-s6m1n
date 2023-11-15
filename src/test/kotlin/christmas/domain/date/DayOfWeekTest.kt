package christmas.domain.date

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DayOfWeekTest {

    @Test
    fun `getDayType 메소드가 WEEKDAY를 적절히 반환하는지 확인`() {
        val weekdays =
            listOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY)
        weekdays.forEach { assertEquals(DayType.WEEKDAY, it.getDayType()) }
    }

    @Test
    fun `getDayType 메소드가 WEEKEND를 적절히 반환하는지 확인`() {
        val weekends = listOf(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)
        weekends.forEach { assertEquals(DayType.WEEKEND, it.getDayType()) }
    }
}