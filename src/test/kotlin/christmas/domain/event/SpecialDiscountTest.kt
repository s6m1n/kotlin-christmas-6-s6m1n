package christmas.domain.event

import christmas.domain.date.Date
import christmas.domain.event.SpecialDiscount
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SpecialDiscountTest {

    @Nested
    @DisplayName("isApplicable 메서드 테스트")
    inner class IsApplicableTest {

        @ParameterizedTest
        @ValueSource(ints = [3, 10, 17, 24, 25, 31])
        fun `달력에 별이 있는 날짜일 경우 true를 반환`(date: Int) {
            val discount = SpecialDiscount(Date(date))
            assertTrue(discount.isApplicable())
        }

        @ParameterizedTest
        @ValueSource(ints = [1, 8, 15, 22, 29])
        fun `달력에 별이 있는 날짜가 아닐 경우 false를 반환`(date: Int) {
            val discount = SpecialDiscount(Date(date))
            assertFalse(discount.isApplicable())
        }
    }
}