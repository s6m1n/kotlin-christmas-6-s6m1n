package christmas.domain.event

import christmas.domain.date.Date

class ChristmasDiscount(private val date: Date) : Event {
    override fun isApplicable() = date.getDate() <= 25
    override fun getDiscountAmount() = 1000 + (100 * (date.getDate() - 1))
    override fun getEventString(): String = CHRISTMAS_DISCOUNT

    companion object {
        const val CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인"
    }
}