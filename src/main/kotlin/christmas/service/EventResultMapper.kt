package christmas.service

import christmas.domain.BenefitDetails
import christmas.domain.EventResultDTO
import christmas.domain.Order

class EventResultMapper(private val eventPlannerService: DecemberEventPlannerService) {

    fun toEventResultDTO(order: Order, benefitDetails: BenefitDetails): EventResultDTO {
        val totalBenefitAmount = benefitDetails.getBenefitAmountSum()
        return EventResultDTO(
            menus = getOrderStringMap(order),
            totalAmount = order.getAmountSum(),
            hasFreeGift = order.hasFreeGift(),
            benefitDetails = getBenefitDetailsStringMap(benefitDetails),
            totalBenefitAmount = totalBenefitAmount,
            finalAmount = eventPlannerService.getFinalAmount(order, benefitDetails),
            eventBadge = eventPlannerService.getEventBadge(totalBenefitAmount)
        )
    }

    private fun getOrderStringMap(order: Order) = order.getOrder().mapKeys { it.key.getName() }

    private fun getBenefitDetailsStringMap(benefitDetails: BenefitDetails) =
        benefitDetails.getBenefitDetails().mapKeys { it.key.getEventString() }
}