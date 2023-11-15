package christmas.domain.event

import christmas.domain.Order
import christmas.domain.date.Date
import christmas.domain.date.DayType
import christmas.domain.menu.MenuType

class WeekdayDiscount(
    private val date: Date,
    private val order: Order
) : Event {

    override fun isApplicable(): Boolean =
        date.getDayOfWeek().getDayType() == DayType.WEEKDAY

    override fun getDiscountAmount(): Int =
        2023 * order.getTotalQuantityOfMenuType(MenuType.DESSERT)

    override fun getEventString(): String = WEEKDAY_DISCOUNT

    companion object {
        const val WEEKDAY_DISCOUNT = "평일 할인"
    }
}