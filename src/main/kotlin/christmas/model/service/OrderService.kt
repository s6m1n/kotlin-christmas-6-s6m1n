package christmas.model.service

import christmas.model.domain.Menu
import christmas.model.domain.MenuType

class OrderService {
    fun countMenusByType(order: Map<Menu, Int>, menuType: MenuType): Int {
        return order.entries.sumOf { getMenusQuantity(it, menuType) }
    }

    fun getMenusQuantity(order: Map.Entry<Menu, Int>, menuType: MenuType): Int {
        return if (order.key.getMenuType() == menuType) order.value
        else 0
    }
}