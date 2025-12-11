package ru.tbank.education.school.lesson3

fun main() {
    // Исходный список продуктов
    val products = listOf("Молоко", "Хлеб", "Сахар", "Сыр", "Масло", "Колбаса", "Сметана", "Яблоки")

    // todo 1. Проверка наличия "Хлеб" в коллекции

    println("Ищем хлеб")
    if ("Хлеб" in products) {
        println("Хлеб найден\n")
    } else {
        println("Хлеб не найден\n")
    }

    // todo 2. Сортировка по алфавиту и вывод

    var productsSorted = products.sorted()
    var i: Int = 1
    productsSorted.forEach {
        println("Элемент $i: $it")
        i++
    }

    // todo 3. Вывод только продуктов, начинающихся на букву "С"

    println("\nЭлементы, которые начинаются с буквы \"C\":")
    productsSorted.forEach { if (it.startsWith("C", ignoreCase = true)) println(it) }
}