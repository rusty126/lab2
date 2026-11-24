package utils

/** Проверки результатов задач для запуска из main. */
object TestUtils {
    /**
     * Сравнивает ожидаемый и полученный результаты. Например, expect("сумма", 3, 1 + 2).
     *
     * @param title Название проверки.
     * @param expected Ожидаемый результат.
     * @param actual Полученный результат.
     * @throws IllegalStateException Если результаты отличаются.
     */
    fun expect(title: String, expected: Any?, actual: Any?) {
        check(expected == actual) { "$title: ожидалось $expected, получено $actual" }
        println("OK: $title → $actual")
    }
}
