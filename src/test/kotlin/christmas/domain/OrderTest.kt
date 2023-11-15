package christmas.domain

import christmas.domain.Order.Companion.createOrder
import christmas.domain.menu.Menu
import christmas.domain.menu.MenuType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderTest {

    @Test
    fun `validateQuantity 메서드가 20개 이상의 주문에 예외를 던지는지 확인`() {
        val exception = assertThrows<IllegalArgumentException> {
            Order(mapOf(Menu.BUTTON_MUSHROOM_SOUP to 7, Menu.T_BONE_STEAK to 7, Menu.CHAMPAGNE to 7))
        }
        assertEquals("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.", exception.message)
    }

    @Test
    fun `validateIncludeNonBeverageItems 메서드가 음료만 있는 주문에 예외를 던지는지 확인`() {
        val exception = assertThrows<IllegalArgumentException> {
            Order(mapOf(Menu.RED_WINE to 1))
        }
        assertEquals("음료만 주문할 수 없습니다.", exception.message)
    }

    @Test
    fun `getAmountSum 메소드가 적절한 총 주문 금액을 반환하는지`() {
        val orderedMenus = mapOf(Menu.BUTTON_MUSHROOM_SOUP to 1)
        val order = Order(orderedMenus)
        assertEquals(6000, order.getAmountSum())
    }

    @Test
    fun `hasFreeGift 메소드가 적절한 Boolean값을 반환하는지`() {
        val orderedMenus = mapOf(Menu.T_BONE_STEAK to 3)
        val order = Order(orderedMenus)
        assertTrue(order.hasFreeGift())
    }

    @Test
    fun `getTotalQuantityOfMenuType 메소드가 MenuType에 대해 적절한 수량을 반환하는지`() {
        val orderedMenus = mapOf(Menu.BUTTON_MUSHROOM_SOUP to 2, Menu.T_BONE_STEAK to 1)
        val order = Order(orderedMenus)
        assertEquals(2, order.getTotalQuantityOfMenuType(MenuType.APPETIZER))
    }

    @Test
    fun `createOrder 메소드가 적절한 Order 객체를 반환하는지`() {
        val orderList = listOf("양송이수프-2", "티본스테이크-1", "제로콜라-3")
        val order = createOrder(orderList)
        assertEquals(
            mapOf(Menu.BUTTON_MUSHROOM_SOUP to 2, Menu.T_BONE_STEAK to 1, Menu.ZERO_COLA to 3), order.getOrder()
        )
    }
}
