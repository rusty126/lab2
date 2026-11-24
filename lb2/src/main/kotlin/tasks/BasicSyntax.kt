package tasks

/**
 * Решения задач по базовому синтаксису.
 * @author Ковальчук Никита Алексеевич
 * @version 1.0
 */
object BasicSyntax {
    /**
     * Составляет приветствие. Например: greetUser("Анна", 25).
     * @param name Имя пользователя.
     * @param age Текущий возраст.
     * @return Приветствие с возрастом через десять лет.
     */
    fun greetUser(name: String, age: Int): String =
        "Привет, $name! Через 10 лет тебе будет ${age.toLong() + 10} лет."

    /**
     * Определяет сезон. Например: getSeason(3) возвращает «Весна».
     * @param month Номер месяца.
     * @return Название сезона или сообщение о неверном месяце.
     */
    fun getSeason(month: Int): String = when (month) {
        12, 1, 2 -> "Зима"
        in 3..5 -> "Весна"
        in 6..8 -> "Лето"
        in 9..11 -> "Осень"
        else -> "Некорректный месяц"
    }

    /**
     * Вычисляет факториал. Например: factorial(5) возвращает 120.
     * @param n Число; точный результат помещается в Long при n от 0 до 20.
     * @return Факториал или -1 для отрицательного числа.
     * @throws IllegalArgumentException Если n больше 20.
     */
    fun factorial(n: Int): Long {
        if (n < 0) return -1L
        require(n <= 20) { "Факториал числа больше 20 не помещается в Long" }
        return factorialTail(n, 1L)
    }

    /**
     * Хвостовая рекурсия: factorialTail(3, 1) возвращает 6.
     * @param n Количество оставшихся множителей.
     * @param accumulator Накопленное произведение.
     * @return Итоговое произведение.
     */
    private tailrec fun factorialTail(n: Int, accumulator: Long): Long =
        if (n <= 1) accumulator else factorialTail(n - 1, accumulator * n)
}
