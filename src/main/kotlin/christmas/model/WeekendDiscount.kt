package christmas.model

import christmas.model.domain.DayType
import christmas.model.domain.Event
import christmas.model.domain.MenuType

class WeekendDiscount(
    private val date: Date,
    private val order: Order
) : Event {

    override fun isApplicable(): Boolean = date.getDayOfWeek().getDayType() == DayType.WEEKEND

    override fun getDiscountAmount(): Int = 2023 * order.getTotalQuantityOfMenuType(MenuType.MAIN)

    override fun getEventString(): String = "주말 할인"
}