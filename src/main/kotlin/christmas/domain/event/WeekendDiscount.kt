package christmas.domain.event

import christmas.domain.date.Date
import christmas.domain.Order
import christmas.domain.date.DayType
import christmas.domain.menu.MenuType

class WeekendDiscount(
    private val date: Date,
    private val order: Order
) : Event {

    override fun isApplicable(): Boolean = date.getDayOfWeek().getDayType() == DayType.WEEKEND

    override fun getDiscountAmount(): Int = 2023 * order.getTotalQuantityOfMenuType(MenuType.MAIN)

    override fun getEventString(): String = "주말 할인"
}