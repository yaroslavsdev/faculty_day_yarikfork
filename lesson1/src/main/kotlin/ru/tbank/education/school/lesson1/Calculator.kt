package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType = OperationType.ADD): Double? {
    val result = when (operation) {
        OperationType.ADD -> a + b
        OperationType.SUBTRACT -> a - b
        OperationType.MULTIPLY -> a * b
        OperationType.DIVIDE -> {
            if (b != 0.0) {
                a / b
            } else {
                null
            }
        }
        else -> null
    }
    return result
}

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    var nums = this.split(" ")
    var oper: OperationType? = when (nums[1]) {
        "+" -> OperationType.ADD
        "-" -> OperationType.SUBTRACT
        "*" -> OperationType.MULTIPLY
        "/" -> OperationType.DIVIDE
        else -> null
    }
    var a: Double? = nums[0].toDoubleOrNull()
    var b: Double? = nums[2].toDoubleOrNull()

    if (a == null || b == null || oper == null) {
        println("Error")
        return null
    }

    val result = calculate(a, b, oper)
    result?.let {
        ans -> println("Answer: $ans")
    } ?: println("Error")

    return result
}
