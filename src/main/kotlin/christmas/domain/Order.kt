package christmas.domain

import christmas.domain.menu.Menu
import christmas.domain.menu.Menu.Companion.toMenu
import christmas.domain.menu.MenuType
import christmas.view.InputView.Companion.HYPHEN

class Order(private val orderedMenus: Map<Menu, Int>) {

    init {
        validateQuantity()
        validateIncludeNonBeverageItems()
    }

    private fun validateQuantity() {
        require(orderedMenus.values.sumOf { it } <= 20) { EXCEEDED_MAX_ORDER_QUANTITY_MSG }
    }

    private fun validateIncludeNonBeverageItems() {
        val onlyBeverage = orderedMenus.keys.all { it.getMenuType() == MenuType.BEVERAGE }
        if (onlyBeverage) throw IllegalArgumentException(BEVERAGE_ONLY_ORDER_NOT_ALLOWED_MSG)
    }

    fun getOrder() = orderedMenus

    fun getAmountSum() = orderedMenus.entries.sumOf { it.key.getPrice() * it.value }

    fun hasFreeGift() = 120_000 <= getAmountSum()

    fun getTotalQuantityOfMenuType(menuType: MenuType): Int {
        return orderedMenus.entries.sumOf { (menu, quantity) ->
            when (menu.getMenuType() == menuType) {
                true -> quantity
                false -> 0
            }
        }
    }

    companion object {

        const val EXCEEDED_MAX_ORDER_QUANTITY_MSG = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."
        const val BEVERAGE_ONLY_ORDER_NOT_ALLOWED_MSG = "음료만 주문할 수 없습니다."

        fun createOrder(orderList: List<String>): Order {
            val orderedMenus = orderList.associate { order ->
                val (menuText, quantityText) = order.split(HYPHEN)
                menuText.toMenu() to quantityText.toInt()
            }
            return Order(orderedMenus)
        }
    }
}
