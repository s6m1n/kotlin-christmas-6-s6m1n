package christmas.model

import christmas.model.domain.Event

class ChristmasDiscount(private val date: Date) : Event {
    override fun isApplicable() = date.getDate() <= 25
    override fun getDiscountAmount() = 1000 + (100 * (date.getDate() - 1))
    override fun getEventString(): String = "크리스마스 디데이 할인"
}