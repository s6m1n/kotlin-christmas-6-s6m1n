package christmas.model

import WeekdayDiscount
import christmas.model.domain.Menu
import christmas.model.service.DateService
import christmas.model.service.OrderService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WeekdayDiscountTest {

    private val dateService = DateService()
    private val orderService = OrderService()

    private val mainMenus: Map<Menu, Int> = mapOf(
        Menu.T_BONE_STEAK to 1,
        Menu.BARBEQUE_LIBS to 1,
        Menu.SEAFOOD_PASTA to 1,
        Menu.CHRISTMAS_PASTA to 1,
    )

    @Nested
    @DisplayName("isApplicable 메서드 테스트")
    inner class IsApplicableTest {

        @ParameterizedTest
        @ValueSource(ints = [7, 10, 14, 17, 21, 24, 28, 31])
        fun `주말일 경우 true를 반환`(date: Int) {
            val weekdayDiscount = WeekdayDiscount(date, mainMenus, dateService, orderService)
            assertTrue(weekdayDiscount.isApplicable())
        }

        @ParameterizedTest
        @ValueSource(ints = [1, 2, 8, 9, 15, 16, 22, 23, 29, 30])
        fun `주말이 아닐 경우 false를 반환`(date: Int) {
            val weekdayDiscount = WeekdayDiscount(date, mainMenus, dateService, orderService)
            assertFalse(weekdayDiscount.isApplicable())
        }
    }

    @Nested
    @DisplayName("getDiscountAmount 메서드 테스트")
    inner class GetDiscountAmountTest {

        @Test
        fun `Dessert 메뉴가 두 개인 경우 2023원`() {
            val testMenus: Map<Menu, Int> = mapOf(
                Menu.CHOCOLATE_CAKE to 1,
                Menu.ICE_CREAM to 1,
            )
            val expectedDiscount = 4046
            val weekdayDiscount = WeekdayDiscount(1, testMenus, dateService, orderService)
            val actualDiscount = weekdayDiscount.getDiscountAmount()
            Assertions.assertEquals(expectedDiscount, actualDiscount)
        }

        @Test
        fun `Dessert 메뉴가 없을 경우 0원`() {
            val notMainMenus: Map<Menu, Int> = mapOf(
                Menu.BUTTON_MUSHROOM_SOUP to 1,
                Menu.TAPAS to 1,
                Menu.ZERO_COLA to 1,
            )

            val expectedDiscount = 0
            val weekdayDiscount = WeekdayDiscount(1, notMainMenus, dateService, orderService)
            val actualDiscount = weekdayDiscount.getDiscountAmount()
            Assertions.assertEquals(expectedDiscount, actualDiscount)
        }
    }
}