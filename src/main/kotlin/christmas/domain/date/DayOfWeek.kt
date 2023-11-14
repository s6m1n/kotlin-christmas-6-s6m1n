package christmas.domain.date

enum class DayOfWeek(val startDate: Int) {
    MONDAY(4),
    TUESDAY(5),
    WEDNESDAY(6),
    THURSDAY(7),
    FRIDAY(1),
    SATURDAY(2),
    SUNDAY(3);

    fun getDayType(): DayType {
        return when (this) {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, SUNDAY -> DayType.WEEKDAY
            FRIDAY, SATURDAY -> DayType.WEEKEND
        }
    }
}