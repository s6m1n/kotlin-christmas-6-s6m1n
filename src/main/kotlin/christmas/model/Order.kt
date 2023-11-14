package christmas.model

import christmas.model.domain.Menu
import christmas.model.domain.MenuType

class Order(private val orderedMenus: Map<Menu, Int>) {

    init {
        validateQuantity()
        validateIncludeNonBeverageItems()
    }

    private fun validateQuantity() {
        require(orderedMenus.values.sumOf { it } <= 20) { "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다." }
    }

    private fun validateIncludeNonBeverageItems() {
        val menuTypes = orderedMenus.map { it.key.getMenuType() }
        if (menuTypes.contains(MenuType.BEVERAGE) && menuTypes.size == 1) throw IllegalArgumentException("음료만 주문할 수 없습니다.")
    }

    fun getTotalQuantityOfMenuType(menuType: MenuType): Int {
        return orderedMenus.entries.sumOf { (menu, quantity) ->
            when (menu.getMenuType() == menuType) {
                true -> quantity
                false -> 0
            }
        }
    }

    fun getOrder() = orderedMenus
    fun getAmountSum() = orderedMenus.entries.sumOf { it.key.getPrice() * it.value }
}