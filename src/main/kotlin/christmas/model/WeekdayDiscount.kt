import christmas.model.domain.*
import christmas.model.service.DateService
import christmas.model.service.OrderService

class WeekdayDiscount(
    private val date: Int,
    private val order: Map<Menu, Int>,
    private val dateService: DateService,
    private val orderService: OrderService
) : Event {

    override fun isApplicable(): Boolean =
        dateService.getDayOfWeek(date).getDayType() == DayType.WEEKDAY

    override fun getDiscountAmount(): Int =
        2023 * orderService.countMenusByType(order, MenuType.DESSERT)
}
