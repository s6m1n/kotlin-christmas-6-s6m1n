package christmas.controller

import christmas.model.Date
import christmas.model.Order
import christmas.model.domain.Menu
import christmas.model.domain.Menu.Companion.stringToMenu
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
            val orderInput = inputView.getValidOrder().toOrderFormat()
            Order(orderInput)
        } catch (exception: IllegalArgumentException) {
            outputView.printErrorMessage(exception)
            getOrder()
        }
    }

    private fun List<String>.toOrderFormat(): Map<Menu, Int> {
        return this.mapNotNull { order ->
            val (menuText, quantityText) = order.split("-")
            val menuName = menuText.stringToMenu() ?: throw IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.")
            val quantity = quantityText.toInt()
            menuName to quantity
        }.toMap()
    }
}