package christmas.controller

import christmas.domain.BenefitDetails
import christmas.domain.Order
import christmas.domain.date.Date

class WoowacourseRestaurantController {

    private val viewController = ViewController()
    private val eventPlannerController = EventPlannerController()

    fun start() {
        viewController.showWelcomeMessage()
        val date = requestDateFromUser()
        val order = requestOrderFromUser()
        val benefitDetails = eventPlannerController.applyEvents(date, order)
        showResult(order, benefitDetails)
    }

    private fun requestDateFromUser(): Date {
        return viewController.inputDate()
    }

    private fun requestOrderFromUser(): Order {
        return viewController.inputOrder()
    }

    private fun showResult(order: Order, benefitDetails: BenefitDetails) {
        val resultDTO = eventPlannerController.getResultDTO(order, benefitDetails)
        viewController.showEventResults(resultDTO)
    }
}