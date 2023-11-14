package christmas.domain.event

interface Event {
    fun isApplicable(): Boolean
    fun getDiscountAmount(): Int
    fun getEventString() : String
}