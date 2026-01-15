package ru.tbank.education.school.lesson8.practise

import com.tngtech.archunit.lang.ArchRule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 *
 * Сценарии для тестирования:
 *
 * 1. Позитивные сценарии (happy path):
 *    - Обычный случай: basePrice = 1000, discount = 10%, tax = 20% → проверить корректность формулы.
 *    - Без скидки: discountPercent = 0 → итог = basePrice + налог.
 *    - Без налога: taxPercent = 0 → итог = basePrice минус скидка.
 *    - Без скидки и без налога: итог = basePrice.
 *
 * 2. Негативные сценарии (исключения):
 *    - Отрицательная цена: basePrice < 0 → IllegalArgumentException.
 *    - Скидка вне диапазона: discountPercent < 0 или > 100 → IllegalArgumentException.
 *    - Налог вне диапазона: taxPercent < 0 или > 30 → IllegalArgumentException.
 */


class CalculateFinalPriceTest {

    @Test
    fun `valid data should return true`() {
        Assertions.assertTrue(calculateFinalPrice(1000.0, 10, 20) == 1000 * 0.9 * 1.2)
        Assertions.assertTrue(calculateFinalPrice(1000.0, 0, 20) == 1000 * 1.2)
        Assertions.assertTrue(calculateFinalPrice(1000.0, 10, 0) == 1000 * 0.9)
        Assertions.assertTrue(calculateFinalPrice(700.0, 0, 0) == 700.0)
    }

    @Test
    fun `invalid data should return false`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            calculateFinalPrice(-1000.0, 10, 20)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            calculateFinalPrice(1000.0, -10, 20)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            calculateFinalPrice(1000.0, 10, 31)
        }
    }
}