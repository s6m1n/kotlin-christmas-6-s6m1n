package christmas.model

import christmas.model.domain.Event

class SpecialDiscount(private val date: Date) : Event {
    override fun isApplicable() = listOf(3, 10, 17, 24, 25, 31).contains(date.getDate())
    override fun getDiscountAmount() = 1000
    override fun getEventString(): String = "특별 할인"
}