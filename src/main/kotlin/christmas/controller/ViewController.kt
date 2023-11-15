package christmas.controller

import christmas.domain.BenefitDetails
import christmas.domain.Order
import christmas.domain.Order.Companion.createOrder
import christmas.domain.date.Date
import christmas.view.InputView
import christmas.view.OutputView

class ViewController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun showWelcomeMessage() {
        outputView.promptDecemberEventPlanner()
    }

    fun inputDate(): Date {
        outputView.promptDateInput()
        return getValidDateFromUser()
    }

    private fun getValidDateFromUser(): Date =
        try {
            Date(inputView.getValidDate())
        } catch (exception: IllegalArgumentException) {
            outputView.printErrorMessage(exception)
            this.getValidDateFromUser()
        }

    fun inputOrder(): Order {
        outputView.promptOrderInput()
        return getValidOrderFromUser()
    }

    private fun getValidOrderFromUser(): Order {
        return try {
            createOrder(inputView.getValidOrder())
        } catch (exception: IllegalArgumentException) {
            outputView.printErrorMessage(exception)
            getValidOrderFromUser()
        }
    }

    fun showEventResults(order: Order, benefitDetails: BenefitDetails) {
        outputView.promptBenefitPreview()
        outputView.showOrderedMenu(order.getOrder())
        outputView.showTotalAmount(order.getAmountSum())
        outputView.showFreeGift(order.hasFreeGift())
        outputView.showBenefitDetails(benefitDetails.getEvents())
        outputView.showTotalBenefitAmount(benefitDetails.getBenefitAmountSum())
        outputView.showExpectedPaymentAmount(benefitDetails.getFinalAmount(order))
        outputView.showDecemberEventBadge(benefitDetails.getBenefitAmountSum())
    }
}