package christmas.domain

import christmas.domain.event.Event
import christmas.domain.event.FreeGiftEvent.Companion.FREE_GIFT_EVENT

class BenefitDetails(private val benefitDetails: Map<Event, Int>) {

    fun getEvents() = benefitDetails

    fun getBenefitAmountSum() = benefitDetails.entries.sumOf { it.value }

    fun getFinalAmount(order: Order): Int {
        val discountAmount = benefitDetails.filter { it.key.getEventString() != FREE_GIFT_EVENT }.values.sumOf { it }
        return order.getAmountSum() - discountAmount
    }
}