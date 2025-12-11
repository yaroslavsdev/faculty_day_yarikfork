package ru.tbank.education.school.lesson3

fun main() {
    val sum = {x: Int, y: Int -> Int
        val result = x + y;
        result
    }
    println(sum(1, 3))
}