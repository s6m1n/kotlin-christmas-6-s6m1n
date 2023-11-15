package christmas.service

import christmas.domain.BenefitDetails
import christmas.domain.EventResultDTO
import christmas.domain.Order

class EventResultMapper(private val eventPlannerService: DecemberEventPlannerService) {

    fun toEventResultDTO(order: Order, benefitDetails: BenefitDetails): EventResultDTO {
        val menus = order.getOrder().mapKeys { it.key.getName() }
        val totalAmount = order.getAmountSum()
        val hasFreeGift = order.hasFreeGift()
        val benefitDetailsMap = benefitDetails.getBenefitDetails().mapKeys { it.key.getEventString() }
        val totalBenefitAmount = benefitDetails.getBenefitAmountSum()
        val finalAmount = eventPlannerService.getFinalAmount(order, benefitDetails)
        val eventBadge = eventPlannerService.getEventBadge(totalBenefitAmount)

        return EventResultDTO(
            menus = menus,
            totalAmount = totalAmount,
            hasFreeGift = hasFreeGift,
            benefitDetails = benefitDetailsMap,
            totalBenefitAmount = totalBenefitAmount,
            finalAmount = finalAmount,
            eventBadge = eventBadge
        )
    }
}