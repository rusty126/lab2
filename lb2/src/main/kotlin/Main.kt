    import kotlinx.coroutines.runBlocking
import tasks.BasicSyntax
import tasks.Collections
import tasks.Coroutines
import tasks.NullSafety
import tasks.Person
import utils.TestUtils.expect
import kotlin.time.TimeSource
import kotlin.time.Duration.Companion.seconds

/** Запускает проверки всех задач. Пример запуска: gradlew run. */
fun main() {
    runBlocking {
        runChecks()
    }
}

/**
 * Проверяет результаты задач и выводит их в консоль.
 * Вызывается из main внутри runBlocking.
 */
private suspend fun runChecks() {
    println("Лабораторная работа №2 — Ковальчук Никита Алексеевич")
    expect("1.1 Приветствие", "Привет, Анна! Через 10 лет тебе будет 35 лет.", BasicSyntax.greetUser("Анна", 25))
    val seasons = listOf("Зима", "Зима", "Весна", "Весна", "Весна", "Лето", "Лето", "Лето", "Осень", "Осень", "Осень", "Зима")
    seasons.forEachIndexed { index, season -> expect("1.2 Месяц ${index + 1}", season, BasicSyntax.getSeason(index + 1)) }
    expect("Неверный месяц", "Некорректный месяц", BasicSyntax.getSeason(0))
    expect("Месяц 13", "Некорректный месяц", BasicSyntax.getSeason(13))
    expect("1.3 Факториал 5", 120L, BasicSyntax.factorial(5))
    expect("Факториал 0", 1L, BasicSyntax.factorial(0))
    expect("Отрицательный факториал", -1L, BasicSyntax.factorial(-3))
    expect("Факториал 20", 2432902008176640000L, BasicSyntax.factorial(20))
    expect("Переполнение факториала", true, runCatching { BasicSyntax.factorial(21) }.exceptionOrNull() is IllegalArgumentException)
    expect("2.1 Число", 123, NullSafety.parseIntSafe("123"))
    expect("Неверная строка", null, NullSafety.parseIntSafe("abc"))
    expect("Вход null", null, NullSafety.parseIntSafe(null))
    expect("Переполнение Int", null, NullSafety.parseIntSafe("2147483648"))
    expect("2.2 Удвоение", listOf(2, 6, 10), NullSafety.filterNonNullAndDouble(listOf(1, null, 3, null, 5)))
    expect("Все элементы null", emptyList<Int>(), NullSafety.filterNonNullAndDouble(listOf(null)))
    expect("3.1 Средний возраст", 30.0, Collections.averageAge(listOf(Person("Анна", 25), Person("Иван", 30), Person("Мария", 35))))
    expect("Пустой список людей", 0.0, Collections.averageAge(emptyList()))
    expect("3.2 Группировка", mapOf('A' to listOf("apple", "Apricot"), 'B' to listOf("banana")), Collections.groupByFirstLetter(listOf("apple", "Apricot", "banana", "")))
    expect("3.3 Длинное слово", "pineapple", Collections.findLongestWord(listOf("apple", "banana", "pineapple")))
    expect("Пустой список слов", null, Collections.findLongestWord(emptyList()))
    expect("Одинаковая длина", "кот", Collections.findLongestWord(listOf("кот", "дом")))
    println("4.1 Ожидаем две секунды:")
    val delayStarted = TimeSource.Monotonic.markNow()
    Coroutines.delayedPrint("Начинаем...", 2000L)
    expect("4.1 Задержка не меньше двух секунд", true, delayStarted.elapsedNow() >= 2.seconds)
    println("4.2 Три задачи запускаются одновременно:")
    val tasksStarted = TimeSource.Monotonic.markNow()
    expect("Результаты корутин", listOf("Результат 1", "Результат 2", "Результат 3"), Coroutines.runParallelTasks())
    println("Время выполнения трёх задач: ${tasksStarted.elapsedNow().inWholeMilliseconds} мс")
    println("Все проверки пройдены.")
}
