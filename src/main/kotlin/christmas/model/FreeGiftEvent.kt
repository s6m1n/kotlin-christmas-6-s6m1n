package christmas.model

import christmas.model.domain.Event

class FreeGiftEvent(private val amountBeforeDiscount: Int) : Event {
    override fun isApplicable() = (120_000 <= amountBeforeDiscount)
    override fun getDiscountAmount() = 25_000
}