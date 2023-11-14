package christmas.domain.date

import christmas.view.InputView.Companion.INVALID_DATE

class Date(private val date: Int) {

    init {
        validateRange(date)
    }

    private fun validateRange(number: Int) {
        require(number in 1..31) { INVALID_DATE }
    }

    fun getDate(): Int = date
    fun getDayOfWeek(): DayOfWeek {
        return DayOfWeek.entries.find { (date - it.startDate) % 7 == 0 } ?: DayOfWeek.MONDAY
    }
}