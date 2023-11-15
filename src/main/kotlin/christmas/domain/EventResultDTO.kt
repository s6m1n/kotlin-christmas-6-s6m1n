package christmas.domain

data class EventResultDTO(
    val menus: Map<String, Int>,
    val totalAmount: Int,
    val hasFreeGift: Boolean,
    val benefitDetails: Map<String, Int>,
    val totalBenefitAmount: Int,
    val finalAmount: Int,
    val eventBadge: String
)