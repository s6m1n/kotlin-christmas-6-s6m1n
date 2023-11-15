package christmas.domain.date

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DateTest {

    @Nested
    @DisplayName("getDayOfWeek 메소드가 날짜에 적절한 요일을 반환하는지 테스트")
    inner class GetDayOfWeekTest {
        @ParameterizedTest
        @ValueSource(ints = [1, 8, 15, 22, 29])
        fun `금요일에 해당하는 날짜가 적절한 요일을 반환하는지 확인`(input: Int) {
            val date = Date(input)
            assertEquals(DayOfWeek.FRIDAY, date.getDayOfWeek())
        }

        @ParameterizedTest
        @ValueSource(ints = [2, 9, 16, 23, 30])
        fun `토요일에 해당하는 날짜가 적절한 요일을 반환하는지 확인`(input: Int) {
            val date = Date(input)
            assertEquals(DayOfWeek.SATURDAY, date.getDayOfWeek())
        }

        @ParameterizedTest
        @ValueSource(ints = [3, 10, 17, 24, 31])
        fun `일요일에 해당하는 날짜가 적절한 요일을 반환하는지 확인`(input: Int) {
            val date = Date(input)
            assertEquals(DayOfWeek.SUNDAY, date.getDayOfWeek())
        }

        @ParameterizedTest
        @ValueSource(ints = [4, 11, 18, 25])
        fun `월요일에 해당하는 날짜가 적절한 요일을 반환하는지 확인`(input: Int) {
            val date = Date(input)
            assertEquals(DayOfWeek.MONDAY, date.getDayOfWeek())
        }

        @ParameterizedTest
        @ValueSource(ints = [5, 12, 19, 26])
        fun `화요일에 해당하는 날짜가 적절한 요일을 반환하는지 확인`(input: Int) {
            val date = Date(input)
            assertEquals(DayOfWeek.TUESDAY, date.getDayOfWeek())
        }

        @ParameterizedTest
        @ValueSource(ints = [6, 13, 20, 27])
        fun `수요일에 해당하는 날짜가 적절한 요일을 반환하는지 확인`(input: Int) {
            val date = Date(input)
            assertEquals(DayOfWeek.WEDNESDAY, date.getDayOfWeek())
        }

        @ParameterizedTest
        @ValueSource(ints = [7, 14, 21, 28])
        fun `목요일에 해당하는 날짜가 적절한 요일을 반환하는지 확인`(input: Int) {
            val date = Date(input)
            assertEquals(DayOfWeek.THURSDAY, date.getDayOfWeek())
        }
    }

    @Nested
    @DisplayName("validateRange 메소드가 1~31 밖에서 예외를 던지는지 테스트")
    inner class ValidateRangeTest {
        @Test
        fun `날짜가 1보다 작으면 예외가 발생한다`() {
            val exception = assertThrows<IllegalArgumentException> {
                Date(32)
            }
            assertEquals("유효하지 않은 날짜입니다. 다시 입력해 주세요.", exception.message)
        }

        @Test
        fun `날짜가 31보다 크면 예외가 발생한다`() {
            val exception = assertThrows<IllegalArgumentException> {
                Date(32)
            }
            assertEquals("유효하지 않은 날짜입니다. 다시 입력해 주세요.", exception.message)
        }
    }
}