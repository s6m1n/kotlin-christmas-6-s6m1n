package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.view.OutputView.Companion.ERROR_MESSAGE_START_PAD

class InputView {

    fun getValidDate(): Int {
        return try {
            Console.readLine().toInt()
        } catch (error: NumberFormatException) {
            println(ERROR_MESSAGE_START_PAD + INVALID_DATE)
            getValidDate()
        }
    }

    fun getValidOrder(): List<String> {
        return try {
            val parsedInput = Console.readLine().split(",")
            validateOrder(parsedInput)
        } catch (error: IllegalArgumentException) {
            println(ERROR_MESSAGE_START_PAD + INVALID_ORDER)
            getValidOrder()
        }
    }

    private fun validateOrder(parsedInput: List<String>): List<String> {
        notParsableByHyphenException(parsedInput)
        duplicatedMenuException(parsedInput)
        invalidQuantityException(parsedInput)
        return parsedInput
    }

    private fun invalidQuantityException(parsedInput: List<String>) {
        val menuQuantity = parsedInput.map {
            try {
                it.split("-")[1].toInt()
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException()
            }
        }
        menuQuantity.forEach {
            if (it == 0) throw IllegalArgumentException()
        }
    }

    private fun duplicatedMenuException(parsedInput: List<String>) {
        val menuNames = parsedInput.map { it.split("-").first() }
        require(menuNames.distinct().size == menuNames.size) { INVALID_ORDER }
    }

    private fun notParsableByHyphenException(parsedInput: List<String>) {
        require(parsedInput.all { parsedText -> parsedText.count { char -> char == '-' } == 1 }) { INVALID_ORDER }
    }

    companion object {
        const val INVALID_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요."
        const val INVALID_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요."
    }
}