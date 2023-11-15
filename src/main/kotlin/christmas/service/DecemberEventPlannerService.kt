package christmas.service

import christmas.domain.BenefitDetails
import christmas.domain.Order
import christmas.domain.date.Date
import christmas.domain.event.*
import christmas.view.OutputView

class DecemberEventPlannerService {

    fun createEvents(date: Date, order: Order): List<Event> {
        return listOf(
            ChristmasDiscount(date),
            WeekdayDiscount(date, order),
            WeekendDiscount(date, order),
            SpecialDiscount(date),
            FreeGiftEvent(order.getAmountSum())
        )
    }

    fun calculateBenefitDetails(order: Order, events: List<Event>): BenefitDetails {
        return when (isApplicable(order)) {
            true -> matchEventWithBenefitAmount(events)
            false -> passWithoutEvent(events)
        }
    }

    fun isApplicable(order: Order) = (10000 <= order.getAmountSum())

    private fun matchEventWithBenefitAmount(events: List<Event>): BenefitDetails {
        return BenefitDetails(events.associateWith { event ->
            when (event.isApplicable()) {
                true -> event.getDiscountAmount()
                false -> 0
            }
        })
    }

    private fun passWithoutEvent(events: List<Event>): BenefitDetails {
        return BenefitDetails(events.associateWith { 0 })
    }

    fun getFinalAmount(order: Order, benefitDetails: BenefitDetails): Int {
        val discountAmount = benefitDetails.getDiscountAmountSum()
        val totalAmount = order.getAmountSum()
        return (totalAmount - discountAmount)
    }

    fun getEventBadge(totalBenefitAmount: Int): String {
        return when {
            (20000 <= totalBenefitAmount) -> EventBadge.SANTA.getName()
            (10000 <= totalBenefitAmount) -> EventBadge.TREE.getName()
            (5000 <= totalBenefitAmount) -> EventBadge.STAR.getName()
            else -> OutputView.NONE
        }
    }
}