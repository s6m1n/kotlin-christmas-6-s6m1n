package christmas.domain.event

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class FreeGiftEventTest {

    @Nested
    @DisplayName("isApplicable 메서드 테스트")
    inner class IsApplicableTest {

        @ParameterizedTest
        @ValueSource(ints = [120_000, 120_001])
        fun `12만원 이상일 경우 true를 반환`(date: Int) {
            val freeGiftEvent = FreeGiftEvent(date)
            assertTrue(freeGiftEvent.isApplicable())
        }

        @ParameterizedTest
        @ValueSource(ints = [0, 119_999])
        fun `12만원 이하일 경우 false를 반환`(date: Int) {
            val freeGiftEvent = FreeGiftEvent(date)
            assertFalse(freeGiftEvent.isApplicable())
        }
    }
}