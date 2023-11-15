package christmas.domain

import christmas.domain.date.Date
import christmas.domain.event.ChristmasDiscount
import christmas.domain.event.FreeGiftEvent
import christmas.domain.event.SpecialDiscount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BenefitDetailsTest {

    @Test
    fun `getBenefitAmountSum 메서드가 적절한 값을 반환하는지`() {
        val date = 24
        val christmas = ChristmasDiscount(Date(date))
        val special = SpecialDiscount(Date(date))
        val free = FreeGiftEvent(120_000)

        val benefitDetailsMap = mapOf(christmas to 3400, free to 25000, special to 1000)
        val benefitDetails = BenefitDetails(benefitDetailsMap)
        assertEquals(29400, benefitDetails.getBenefitAmountSum())
    }

    @Test
    fun `getDiscountAmountSum 메서드가 적절한 값을 반환하는지`() {
        val date = 24
        val christmas = ChristmasDiscount(Date(date))
        val special = SpecialDiscount(Date(date))
        val free = FreeGiftEvent(120_000)

        val benefitDetailsMap = mapOf(christmas to 3400, free to 25000, special to 1000)
        val benefitDetails = BenefitDetails(benefitDetailsMap)
        assertEquals(4400, benefitDetails.getDiscountAmountSum())
    }
}