package tasks

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.select

/** Решения задач с корутинами. */
object Coroutines {
    /**
     * Печатает сообщение и после задержки — «Готово!».
     * Пример вызова внутри runBlocking: delayedPrint("Начинаем...", 2000L).
     * @param message Начальное сообщение.
     * @param delayMs Неотрицательная задержка в миллисекундах.
     * @throws IllegalArgumentException Если задержка отрицательная.
     */
    suspend fun delayedPrint(message: String, delayMs: Long) {
        require(delayMs >= 0) { "Задержка не может быть отрицательной" }
        println(message)
        delay(delayMs)
        println("Готово!")
    }

    /**
     * Запускает три задачи одновременно и печатает их результаты по завершении.
     * Пример вызова внутри runBlocking: runParallelTasks().
     * @return Результаты в порядке завершения задач.
     */
    suspend fun runParallelTasks(): List<String> = coroutineScope {
        val pending = (1..3).map { number ->
            async {
                delay(number * 1000L)
                "Результат $number"
            }
        }.toMutableList()
        val results = mutableListOf<String>()
        while (pending.isNotEmpty()) {
            // Выбираем завершившуюся задачу, а не ждём список по очереди.
            val completed = select {
                pending.forEach { task ->
                    task.onAwait { task }
                }
            }
            val result = completed.await()
            println(result)
            results.add(result)
            pending.remove(completed)
        }
        results
    }
}
