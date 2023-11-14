package christmas.domain.event

class FreeGiftEvent(private val amountBeforeDiscount: Int) : Event {
    override fun isApplicable() = (120_000 <= amountBeforeDiscount)
    override fun getDiscountAmount() = 25_000
    override fun getEventString(): String = "증정 이벤트"
}