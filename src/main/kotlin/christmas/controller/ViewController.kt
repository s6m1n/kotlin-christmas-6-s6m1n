package christmas.controller

import christmas.domain.EventResultDTO
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

    private fun getValidDateFromUser(): Date = try {
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

    fun showEventResults(eventResult: EventResultDTO) {
        with(outputView) {
            promptBenefitPreview()
            showOrderedMenu(eventResult.menus)
            showTotalAmount(eventResult.totalAmount)
            showFreeGift(eventResult.hasFreeGift)
            showBenefitDetails(eventResult.benefitDetails)
            showTotalBenefitAmount(eventResult.totalBenefitAmount)
            showExpectedPaymentAmount(eventResult.finalAmount)
            showDecemberEventBadge(eventResult.eventBadge)
        }
    }
}