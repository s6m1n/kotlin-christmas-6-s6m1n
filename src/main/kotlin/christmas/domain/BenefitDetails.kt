package christmas.domain

import christmas.domain.event.Event
import christmas.domain.event.FreeGiftEvent

class BenefitDetails(private val benefitDetails: Map<Event, Int>) {

    fun getBenefitDetails() = benefitDetails

    fun getBenefitAmountSum() = benefitDetails.values.sumOf { it }

    fun getDiscountAmountSum() = benefitDetails.filter {
        it.key.getEventString() != FreeGiftEvent.FREE_GIFT_EVENT
    }.values.sumOf { it }
}