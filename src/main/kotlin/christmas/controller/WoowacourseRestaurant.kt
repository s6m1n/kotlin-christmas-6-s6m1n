package christmas.controller

import christmas.domain.BenefitDetails
import christmas.domain.Order
import christmas.domain.date.Date
import christmas.service.DecemberEventPlanner

class WoowacourseRestaurant {

    private val viewController = ViewController()
    private val eventPlanner = DecemberEventPlanner()

    fun start() {
        viewController.showWelcomeMessage()
        val date = requestDateFromUser()
        val order = requestOrderFromUser()
        val benefitDetails = eventPlanner.applyEvents(date, order)
        showResult(order, benefitDetails)
    }

    private fun requestDateFromUser(): Date {
        return viewController.inputDate()
    }

    private fun requestOrderFromUser(): Order {
        return viewController.inputOrder()
    }

    private fun showResult(order: Order, benefitDetails: BenefitDetails) {
        viewController.showEventResults(order, benefitDetails)
    }
}