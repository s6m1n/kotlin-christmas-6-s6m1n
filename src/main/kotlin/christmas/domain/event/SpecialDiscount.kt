package christmas.domain.event

import christmas.domain.date.Date

class SpecialDiscount(private val date: Date) : Event {
    override fun isApplicable() = listOf(3, 10, 17, 24, 25, 31).contains(date.getDate())
    override fun getDiscountAmount() = 1000
    override fun getEventString(): String = SPECIAL_DISCOUNT

    companion object {
        const val SPECIAL_DISCOUNT = "특별 할인"
    }
}