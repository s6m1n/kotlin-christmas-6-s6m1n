package christmas.controller

import christmas.domain.BenefitDetails
import christmas.domain.EventResultDTO
import christmas.domain.Order
import christmas.domain.date.Date
import christmas.service.DecemberEventPlannerService
import christmas.service.EventResultMapper

class EventPlannerController {
    private val plannerService = DecemberEventPlannerService()

    fun applyEvents(date: Date, order: Order): BenefitDetails {
        val events = plannerService.createEvents(date, order)
        return plannerService.calculateBenefitDetails(order, events)
    }

    fun getResultDTO(order: Order, benefitDetails: BenefitDetails): EventResultDTO {
        val mapper = EventResultMapper(plannerService)
        return mapper.toEventResultDTO(order, benefitDetails)
    }
}