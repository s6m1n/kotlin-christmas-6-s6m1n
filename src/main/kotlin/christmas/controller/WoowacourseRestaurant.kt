package christmas.controller

import christmas.model.service.BenefitDetails

class WoowacourseRestaurant {

    private val viewController = ViewController()
    private val date = viewController.startInputDate()
    private val order = viewController.startInputOrder()

    fun start() {
        val benefitDetails = applyEventPlanner()
        showResult(benefitDetails)
    }

    private fun applyEventPlanner(): BenefitDetails {
        val eventPlanner = DecemberEventPlanner(date, order)
        return BenefitDetails(eventPlanner.apply())
    }

    private fun showResult(events: BenefitDetails) {
        viewController.showEventResults(order, events)
    }
}