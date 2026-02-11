package org.example.app

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.atomics.AtomicInt


//Задание 1. Создание потоков
//Создайте 3 потока с именами "Thread-A", "Thread-B", "Thread-C". Каждый поток должен вывести своё имя 5 раз с задержкой 500мс.

//object CreateThreads {
//    fun run(): List<Thread> {
//        val names = listOf("Thread-A", "Thread-B", "Thread-C")
//
//        val threads = names.map { name ->
//            Thread {
//                repeat(5) {
//                    println(Thread.currentThread().name)
//                    Thread.sleep(500)
//                }
//            }.apply {
//                this.name = name
//            }
//        }
//
//        threads.forEach { it.start() }
//        return threads
//    }
//}




//Задание 2. Race condition
//Создайте переменную counter = 0. Запустите 10000 потоков, каждый из которых увеличивает counter на 1. Выведите финальное значение и объясните результат.


//object RaceCondition {
//    fun run(): Int {
//        var counter: AtomicInteger = AtomicInteger(0)
//
//        val threads = mutableListOf<Thread>()
//
//        for (i in 1..10) {
//            threads.add (
//                Thread {
//                    repeat(10000) { counter.incrementAndGet() }
//                }
//            )
//        }
//
//        threads.forEach { it.start() }
//        threads.forEach { it.join() }
//
//        return counter.toInt()
//    }
//}
//
//fun main() {
//    val race = RaceCondition
//    println(race.run())
//}




//Задание 3. Synchronized
//Исправьте задание 2 с помощью @Synchronized или synchronized {} блока, чтобы результат всегда был 10000.

//object SynchronizedCounter {
//    fun run(): Int {
//        var counter: Int = 0
//        val lock = Any()
//
//        val threads = mutableListOf<Thread>()
//
//        for (i in 1..10) {
//            threads.add (
//                Thread {
//                    repeat(10000) {
//                        synchronized(lock) {
//                            counter++;
//                        }
//                    }
//                }
//            )
//        }
//
//        threads.forEach { it.start() }
//        threads.forEach { it.join() }
//
//        return counter
//    }
//}



//Задание 4. Deadlock
//Создайте пример deadlock с двумя ресурсами и двумя потоками. Затем исправьте его.

//object Deadlock {
//    fun runDeadlock() {
//        val lockA = Any()
//        val lockB = Any()
//
//        val threadA = Thread {
//            synchronized(lockA) {
//                println("Thread A started")
//                Thread.sleep(2500)
//                synchronized(lockB) {
//                    println("Thread A is locked")
//                }
//            }
//        }
//
//        val threadB = Thread {
//            synchronized(lockB) {
//                println("Thread B started")
//                Thread.sleep(500)
//                synchronized(lockA) {
//                    println("Thread B is locked")
//                }
//            }
//        }
//
//        threadA.start()
//        threadB.start()
//
//        threadA.join()
//        threadB.join()
//    }
//
//    fun runFixed(): Boolean {
//        val key = Any()
//        val otvertka = Any()
//
//        val threadA = Thread {
//            synchronized(key) {
//                println("Thread A: key")
//                Thread.sleep(2500)
//                synchronized(otvertka) {
//                    println("Thread A: otvertka")
//                }
//            }
//        }
//
//        val threadB = Thread {
//            synchronized(key) {
//                println("Thread B: key")
//                Thread.sleep(500)
//                synchronized(otvertka) {
//                    println("Thread B: otvertka")
//                }
//            }
//        }
//
//        threadA.start()
//        threadB.start()
//
//        threadA.join()
//        threadB.join()
//
//        return true
//    }
//
//}

//Задание 5. ExecutorService
//Используя Executors.newFixedThreadPool(4), выполните 20 задач. Каждая задача выводит свой номер и имя потока, затем спит 200мс.

object ExecutorServiceExample {
    fun run(): List<String> {
        val executor = Executors.newFixedThreadPool(4)


        repeat(20) { i ->
            executor.submit {
                val threadName = Thread.currentThread().name
                println("Task $i executed by $threadName")
                Thread.sleep(200)
            }
        }
    }
    return emptyList()
}

fun main() {
    val a = ExecutorServiceExample

}