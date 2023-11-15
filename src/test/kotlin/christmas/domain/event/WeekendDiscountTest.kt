package christmas.domain.event

import christmas.domain.Order
import christmas.domain.date.Date
import christmas.domain.menu.Menu
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WeekendDiscountTest {

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
        @ValueSource(ints = [1, 2, 8, 9, 15, 16, 22, 23, 29, 30])
        fun `주말(금,토)일 경우 true를 반환`(date: Int) {
            val discount = WeekendDiscount(Date(date), Order(mainMenus))
            assertTrue(discount.isApplicable())
        }

        @ParameterizedTest
        @ValueSource(ints = [7, 10, 14, 17, 21, 24, 28, 31])
        fun `주말(금,토)이 아닐 경우 false를 반환`(date: Int) {
            val discount = WeekendDiscount(Date(date), Order(mainMenus))
            assertFalse(discount.isApplicable())
        }
    }

    @Nested
    @DisplayName("getDiscountAmount 메서드 테스트")
    inner class GetDiscountAmountTest {

        @Test
        fun `메인 메뉴가 네 개인 경우 8092`() {
            val expectedDiscount = 8092
            val discount = WeekendDiscount(Date(1), Order(mainMenus))
            val actualDiscount = discount.getDiscountAmount()
            Assertions.assertEquals(expectedDiscount, actualDiscount)
        }

        @Test
        fun `메인 메뉴가 두 개인 경우 4046원`() {
            val testMenus: Map<Menu, Int> = mapOf(
                Menu.T_BONE_STEAK to 1, Menu.BARBEQUE_LIBS to 1
            )
            val expectedDiscount = 4046
            val discount = WeekendDiscount(Date(1), Order(testMenus))
            val actualDiscount = discount.getDiscountAmount()
            Assertions.assertEquals(expectedDiscount, actualDiscount)
        }

        @Test
        fun `Main 메뉴가 없을 경우 0원`() {
            val notMainMenus: Map<Menu, Int> = mapOf(
                Menu.BUTTON_MUSHROOM_SOUP to 1,
                Menu.TAPAS to 1,
                Menu.CHOCOLATE_CAKE to 1,
                Menu.ZERO_COLA to 1,
            )

            val expectedDiscount = 0
            val discount = WeekendDiscount(Date(1), Order(notMainMenus))
            val actualDiscount = discount.getDiscountAmount()
            Assertions.assertEquals(expectedDiscount, actualDiscount)
        }
    }
}