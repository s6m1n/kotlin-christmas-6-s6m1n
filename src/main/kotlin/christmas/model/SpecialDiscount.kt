package christmas.model

import christmas.model.domain.Event

class SpecialDiscount(private val date: Int) : Event {
    override fun isApplicable() = listOf(3, 10, 17, 24, 25, 31).contains(date)
    override fun getDiscountAmount() = 1000
}