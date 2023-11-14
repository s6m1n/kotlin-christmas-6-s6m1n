package christmas.model.service

import christmas.model.Order
import christmas.model.domain.Event

class BenefitDetails(private val benefitDetails: Map<Event, Int>) {

    fun getEvents() = benefitDetails

    fun getBenefitAmountSum() = benefitDetails.entries.sumOf { it.value }

    fun getFinalAmount(order: Order): Int {
        val discountAmount = benefitDetails.filter { it.key.getEventString() != "증정 이벤트" }.values.sumOf { it }
        return order.getAmountSum() - discountAmount
    }
}