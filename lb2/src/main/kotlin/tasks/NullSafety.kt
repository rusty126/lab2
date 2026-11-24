package tasks

/** Решения задач по null-безопасности. */
object NullSafety {
    /**
     * Безопасно преобразует строку: parseIntSafe("123") возвращает 123.
     * @param str Строка, которая может быть null.
     * @return Число или null при невозможности преобразования.
     */
    fun parseIntSafe(str: String?): Int? = str?.toIntOrNull()

    /**
     * Удаляет null и удваивает числа: [1, null, 3] превращается в [2, 6].
     * @param list Исходный список.
     * @return Новый список удвоенных чисел.
     */
    fun filterNonNullAndDouble(list: List<Int?>): List<Int> =
        list.filterNotNull().map { it * 2 }
}
