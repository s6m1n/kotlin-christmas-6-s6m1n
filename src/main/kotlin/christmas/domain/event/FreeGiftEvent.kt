package christmas.domain.event

class FreeGiftEvent(private val amountBeforeDiscount: Int) : Event {
    override fun isApplicable() = (120_000 <= amountBeforeDiscount)
    override fun getDiscountAmount() = 25_000
    override fun getEventString(): String = FREE_GIFT_EVENT

    companion object {
        const val FREE_GIFT_EVENT = "증정 이벤트"
    }
}