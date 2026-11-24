package tasks

/** Решения задач по коллекциям. */
object Collections {
    /**
     * Считает средний возраст: для возрастов 25 и 30 результат 27.5.
     * @param people Список людей.
     * @return Средний возраст, для пустого списка — 0.0.
     */
    fun averageAge(people: List<Person>): Double =
        if (people.isEmpty()) 0.0 else people.map { it.age }.average()

    /**
     * Группирует слова: «apple» и «Apple» попадают в группу 'A'.
     * Пустые строки пропускаются, так как у них нет первой буквы.
     * @param words Исходные слова.
     * @return Группы по первой букве в верхнем регистре.
     */
    fun groupByFirstLetter(words: List<String>): Map<Char, List<String>> =
        words.filter { it.isNotEmpty() }.groupBy { it.first().uppercaseChar() }

    /**
     * Находит самое длинное слово: для [«кот», «собака»] вернёт «собака».
     * @param words Список слов.
     * @return Первое самое длинное слово или null для пустого списка.
     */
    fun findLongestWord(words: List<String>): String? =
        words.maxByOrNull { it.length }
}
