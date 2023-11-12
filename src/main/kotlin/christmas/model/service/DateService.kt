package christmas.model.service

import christmas.model.domain.DayOfWeek

class DateService {
    fun getDayOfWeek(date: Int): DayOfWeek {
        return DayOfWeek.entries.find { (date - it.startDate) % 7 == 0 } ?: DayOfWeek.MONDAY
    }
}