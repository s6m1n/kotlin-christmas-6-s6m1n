package christmas.model.domain

interface Event {
    fun isApplicable(): Boolean
    fun getDiscountAmount(): Int
    fun getEventString() : String
}