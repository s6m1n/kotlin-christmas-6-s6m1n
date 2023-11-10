package christmas.model

import christmas.model.domain.Event

class ChristmasDiscount(private val date: Int) : Event {
    override fun isApplicable() = date <= 25
    override fun getDiscountAmount() = 1000 + (100 * (date - 1))
}