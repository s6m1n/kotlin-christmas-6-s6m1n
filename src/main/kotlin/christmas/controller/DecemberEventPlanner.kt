package christmas.controller

import christmas.domain.date.Date
import christmas.domain.Order
import christmas.domain.event.Event
import christmas.domain.event.*

class DecemberEventPlanner(
    private val date: Date,
    private val order: Order,
) {

    fun apply(): Map<Event, Int> {
        val events: List<Event> = listOf(
            ChristmasDiscount(date),
            WeekdayDiscount(date, order),
            WeekendDiscount(date, order),
            SpecialDiscount(date),
            FreeGiftEvent(order.getAmountSum())
        )
        return when (isApplicable()) {
            true -> discount(events)
            false -> passWithoutDiscounting(events)
        }
    }

    private fun isApplicable() = (10000 <= order.getAmountSum())

    private fun discount(events: List<Event>): Map<Event, Int> {
        return events.associateWith { event ->
            when (event.isApplicable()) {
                true -> event.getDiscountAmount()
                false -> 0
            }
        }
    }

    private fun passWithoutDiscounting(events: List<Event>): Map<Event, Int> {
        return events.map { it to 0 }.toMap()
    }
}