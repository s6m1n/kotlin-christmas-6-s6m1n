import christmas.model.domain.DayType
import christmas.model.domain.Event
import christmas.model.domain.MenuType
import christmas.model.Date
import christmas.model.Order

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