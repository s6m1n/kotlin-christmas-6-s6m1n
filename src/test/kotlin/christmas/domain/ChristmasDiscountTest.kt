import christmas.domain.event.ChristmasDiscount
import christmas.domain.date.Date
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class ChristmasDiscountTest {

    @Nested
    @DisplayName("isApplicable 메서드 테스트")
    inner class IsApplicableTest {

        @ParameterizedTest
        @ValueSource(ints = [1, 2, 24, 25])
        fun `12월 1일부터 25일 사이인 경우 true를 반환`(date: Int) {
            val discount = ChristmasDiscount(Date(date))
            assertTrue(discount.isApplicable())
        }

        @ParameterizedTest
        @ValueSource(ints = [26, 27, 30, 31])
        fun `25일 이후인 경우 false를 반환`(date: Int) {
            val discount = ChristmasDiscount(Date(date))
            assertFalse(discount.isApplicable())
        }

    }

    @DisplayName("getDiscountAmount 메서드 테스트")
    @ParameterizedTest
    @MethodSource("날짜에 해당하는 할인 금액")
    fun `날짜에 따른 할인 금액이 적절한지 테스트`(date: Int, expectedDiscount: Int) {
        val discount = ChristmasDiscount(Date(date))
        val actualDiscount = discount.getDiscountAmount()
        assertEquals(expectedDiscount, actualDiscount)
    }

    companion object {
        @JvmStatic
        fun `날짜에 해당하는 할인 금액`() = listOf(
            Arguments.of(1, 1000),
            Arguments.of(2, 1100),
            Arguments.of(12, 2100),
            Arguments.of(13, 2200),
            Arguments.of(24, 3300),
            Arguments.of(25, 3400),
        )
    }
}