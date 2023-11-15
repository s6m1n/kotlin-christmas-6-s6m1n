package christmas.service

import christmas.domain.BenefitDetails
import christmas.domain.Order
import christmas.domain.date.Date
import christmas.domain.event.*

class DecemberEventPlanner {

    fun applyEvents(date: Date, order: Order): BenefitDetails {
        val events = createEvents(date, order)
        return calculateBenefitDetails(order, events)
    }

    private fun createEvents(date: Date, order: Order): List<Event> {
        return listOf(
            ChristmasDiscount(date),
            WeekdayDiscount(date, order),
            WeekendDiscount(date, order),
            SpecialDiscount(date),
            FreeGiftEvent(order.getAmountSum())
        )
    }

    private fun calculateBenefitDetails(order: Order, events: List<Event>): BenefitDetails {
        return when (isApplicable(order)) {
            true -> discount(events)
            false -> passWithoutDiscounting(events)
        }
    }

    private fun isApplicable(order: Order) = (10000 <= order.getAmountSum())

    private fun discount(events: List<Event>): BenefitDetails {
        return BenefitDetails(events.associateWith { event ->
            when (event.isApplicable()) {
                true -> event.getDiscountAmount()
                false -> 0
            }
        })
    }

    private fun passWithoutDiscounting(events: List<Event>): BenefitDetails {
        return BenefitDetails(events.associateWith { 0 })
    }
}