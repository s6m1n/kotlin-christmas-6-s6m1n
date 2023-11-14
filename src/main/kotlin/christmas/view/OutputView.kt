package christmas.view

class OutputView {
    fun decemberEventPlannerPrompt() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printDateInputPrompt() {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
    }

    fun printOrderInputPrompt() {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
    }

    fun printErrorMessage(exception: Exception) {
        println(ERROR_MESSAGE_START_PAD + exception.message)
    }

    companion object {
        const val ERROR_MESSAGE_START_PAD = "[ERROR] "
    }
}