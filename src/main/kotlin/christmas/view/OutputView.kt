package christmas.view

import christmas.domain.event.Event
import christmas.domain.event.EventBadge
import christmas.domain.menu.Menu
import java.text.NumberFormat
import java.util.*

class OutputView {
    fun decemberEventPlannerPrompt() {
        println(PROMPT_DECEMBER_EVENT_PLANNER)
    }

    fun printDateInputPrompt() {
        println(PROMPT_INPUT_DATE)
    }

    fun printOrderInputPrompt() {
        println(PROMPT_INPUT_ORDER)
    }

    fun printBenefitPreviewPrompt() {
        println(PROMPT_BENEFIT_PREVIEW)
    }

    fun printErrorMessage(exception: Exception) {
        println(ERROR_MESSAGE_START_PAD + exception.message)
    }

    fun printOrderedMenu(menus: Map<Menu, Int>) {
        println(TITLE_ORDERED_MENU)
        menus.entries.forEach { println("${it.key.getName()} ${it.value}개") }
    }

    fun printTotalAmount(totalAmount: Int) {
        println(TITLE_BEFORE_DISCOUNT_TOTAL_AMOUNT)
        println(formatAmount(totalAmount))
    }

    fun printFreeGift(applyDiscount: Map<Event, Int>) {
        println(TITLE_FREE_GIFT)
        val noFreeGift = (applyDiscount.filter { it.key.getEventString() == "증정 이벤트" && it.value == 0 }.size == 1)
        val freeGiftText = when (noFreeGift) {
            true -> NONE
            false -> ONE_CHAMPAGNE
        }
        println(freeGiftText)
    }

    fun printBenefitDetails(totalBenefit: Map<Event, Int>) {
        println(TITLE_BENEFIT_DETAILS)
        val noBenefit = (totalBenefit.filter { it.value == 0 }.size == totalBenefit.size)
        when (noBenefit) {
            true -> println(NONE)
            false -> totalBenefit.entries.forEach {
                if (it.value != 0) println("${it.key.getEventString()}: -${formatAmount(it.value)}")
            }
        }
    }

    fun printTotalBenefitAmount(totalAmount: Int) {
        println(TITLE_TOTAL_BENEFIT_AMOUNT)
        println(formatAmount(0 - totalAmount))
    }

    fun printExpectedPaymentAmount(totalAmount: Int) {
        println(TITLE_EXPECTED_PAYMENT_AMOUNT)
        println(formatAmount(totalAmount))
    }

    fun printDecemberEventBadge(totalBenefitAmount: Int) {
        println(TITLE_DECEMBER_EVENT_BADGE)
        val badge = when {
            (20000 <= totalBenefitAmount) -> EventBadge.SANTA.getName()
            (10000 <= totalBenefitAmount) -> EventBadge.TREE.getName()
            (5000 <= totalBenefitAmount) -> EventBadge.STAR.getName()
            else -> NONE
        }
        println(badge)
    }

    private fun formatAmount(amount: Int): String {
        val format = NumberFormat.getNumberInstance(Locale.KOREA)
        return format.format(amount) + WON
    }

    companion object {

        const val PROMPT_DECEMBER_EVENT_PLANNER = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
        const val PROMPT_INPUT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
        const val PROMPT_INPUT_ORDER = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
        const val PROMPT_BENEFIT_PREVIEW = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
        const val ERROR_MESSAGE_START_PAD = "[ERROR] "
        const val TITLE_ORDERED_MENU = "\n<주문 메뉴>"
        const val TITLE_BEFORE_DISCOUNT_TOTAL_AMOUNT = "\n<할인 전 총주문 금액>"
        const val TITLE_FREE_GIFT = "\n<증정 메뉴>"
        const val TITLE_BENEFIT_DETAILS = "\n<혜택 내역>"
        const val TITLE_TOTAL_BENEFIT_AMOUNT = "\n<총혜택 금액>"
        const val TITLE_EXPECTED_PAYMENT_AMOUNT = "\n<할인 후 예상 결제 금액>"
        const val TITLE_DECEMBER_EVENT_BADGE = "\n<12월 이벤트 배지>"
        const val NONE = "없음"
        const val WON = "원"
        const val ONE_CHAMPAGNE = "샴페인 1개"
    }
}