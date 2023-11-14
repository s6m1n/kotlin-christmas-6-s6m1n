package christmas.domain.event

import christmas.domain.date.DayType
import christmas.domain.menu.MenuType
import christmas.domain.date.Date
import christmas.domain.Order

class WeekdayDiscount(
    private val date: Date,
    private val order: Order
) : Event {

    override fun isApplicable(): Boolean =
        date.getDayOfWeek().getDayType() == DayType.WEEKDAY

    override fun getDiscountAmount(): Int =
        2023 * order.getTotalQuantityOfMenuType(MenuType.DESSERT)

    override fun getEventString(): String = "평일 할인"
}