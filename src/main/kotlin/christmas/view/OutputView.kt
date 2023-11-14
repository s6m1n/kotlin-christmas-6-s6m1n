package christmas.view

import christmas.domain.event.Event
import christmas.domain.event.EventBadge
import christmas.domain.menu.Menu
import java.text.NumberFormat
import java.util.*

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

    fun benefitPreviewPrompt() {
        println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }

    fun printMenu(menus: Map<Menu, Int>) {
        println("\n<주문 메뉴>")
        menus.entries.forEach { println("${it.key.getName()} ${it.value}개") }
    }

    fun printTotalAmount(totalAmount: Int) {
        println("\n<할인 전 총주문 금액>")
        println(formatAmount(totalAmount))
    }

    fun printFreeGift(applyDiscount: Map<Event, Int>) {
        println("\n<증정 메뉴>")
        val noFreeGift = (applyDiscount.filter { it.key.getEventString() == "증정 이벤트" && it.value == 0 }.size == 1)
        val freeGiftText = when (noFreeGift) {
            true -> "없음"
            false -> "샴페인 1개"
        }
        println(freeGiftText)
    }

    fun printBenefitDetails(totalBenefit: Map<Event, Int>) {
        println("\n<혜택 내역>")
        val noBenefit = (totalBenefit.filter { it.value == 0 }.size == totalBenefit.size)
        when (noBenefit) {
            true -> println("없음")
            false -> totalBenefit.entries.forEach {
                if (it.value != 0) println("${it.key.getEventString()}: -${formatAmount(it.value)}")
            }
        }
    }

    fun printTotalBenefitAmount(totalAmount: Int) {
        println("\n<총혜택 금액>")
        println(formatAmount(0 - totalAmount))
    }

    fun printExpectedPaymentAmount(totalAmount: Int) {
        println("\n<할인 후 예상 결제 금액>")
        println(formatAmount(totalAmount))
    }


    fun printDecemberEventBadge(totalBenefitAmount: Int) {
        println("\n<12월 이벤트 배지>")
        val badge = when {
            (20000 <= totalBenefitAmount) -> EventBadge.SANTA.getName()
            (10000 <= totalBenefitAmount) -> EventBadge.TREE.getName()
            (5000 <= totalBenefitAmount) -> EventBadge.STAR.getName()
            else -> "없음"
        }
        println(badge)
    }

    private fun formatAmount(amount: Int): String {
        val format = NumberFormat.getNumberInstance(Locale.KOREA)
        return format.format(amount) + "원"
    }

    companion object {
        const val ERROR_MESSAGE_START_PAD = "[ERROR] "
    }
}