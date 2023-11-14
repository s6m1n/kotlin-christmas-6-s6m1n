package christmas.controller

import christmas.model.Date
import christmas.model.Order
import christmas.model.Order.Companion.createOrder
import christmas.model.service.BenefitDetails
import christmas.view.InputView
import christmas.view.OutputView

class ViewController {
    private val inputView = InputView()
    private val outputView = OutputView()

    init {
        outputView.decemberEventPlannerPrompt()
    }

    fun startInputDate(): Date {
        outputView.printDateInputPrompt()
        return getDate()
    }

    private fun getDate(): Date =
        try {
            Date(inputView.getValidDate())
        } catch (exception: IllegalArgumentException) {
            outputView.printErrorMessage(exception)
            this.getDate()
        }

    fun startInputOrder(): Order {
        outputView.printOrderInputPrompt()
        return getOrder()
    }

    private fun getOrder(): Order {
        return try {
            createOrder(inputView.getValidOrder())
        } catch (exception: IllegalArgumentException) {
            outputView.printErrorMessage(exception)
            getOrder()
        }
    }

    fun showEventResults(order: Order, benefitDetails: BenefitDetails) {
        outputView.benefitPreviewPrompt()
        outputView.printMenu(order.getOrder())
        outputView.printTotalAmount(order.getAmountSum())
        outputView.printFreeGift(benefitDetails.getEvents())
        outputView.printBenefitDetails(benefitDetails.getEvents())
        outputView.printTotalBenefitAmount(benefitDetails.getBenefitAmountSum())
        outputView.printExpectedPaymentAmount(benefitDetails.getFinalAmount(order))
        outputView.printDecemberEventBadge(benefitDetails.getBenefitAmountSum())
    }
}