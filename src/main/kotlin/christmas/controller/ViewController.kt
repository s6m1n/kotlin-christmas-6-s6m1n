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
        outputView.decemberEventPlannerPrompt()
    }

    fun inputDate(): Date {
        outputView.printDateInputPrompt()
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
        outputView.printOrderInputPrompt()
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
        outputView.printBenefitPreviewPrompt()
        outputView.printOrderedMenu(order.getOrder())
        outputView.printTotalAmount(order.getAmountSum())
        outputView.printFreeGift(benefitDetails.getEvents())
        outputView.printBenefitDetails(benefitDetails.getEvents())
        outputView.printTotalBenefitAmount(benefitDetails.getBenefitAmountSum())
        outputView.printExpectedPaymentAmount(benefitDetails.getFinalAmount(order))
        outputView.printDecemberEventBadge(benefitDetails.getBenefitAmountSum())
    }
}