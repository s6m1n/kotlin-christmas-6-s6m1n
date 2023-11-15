package christmas.domain.menu

import christmas.domain.menu.Menu.Companion.toMenu
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MenuTest {

    @Test
    fun `toMenu 메서드가 적절한 Menu를 반환하는지 확인`() {
        assertEquals(Menu.CAESAR_SALAD, "시저샐러드".toMenu())
    }

    @Test
    fun `toMenu 메서드가 메뉴판에 없는 메뉴에 예외를 던지는지 확인`() {
        val exception = assertThrows<IllegalArgumentException> {
            "InvalidMenu".toMenu()
        }
        assertEquals("유효하지 않은 주문입니다. 다시 입력해 주세요.", exception.message)
    }
}