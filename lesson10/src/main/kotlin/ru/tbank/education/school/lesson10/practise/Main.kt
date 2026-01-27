import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun main() {
    task7()
}

/*
1) Строки + регулярные выражения
["Name: Ivan, score=17", ...]
Извлечь имя и score, собрать пары, вывести победителя.
*/
fun task1() {
    val lines = listOf(
        "Name: Ivan, score=17",
        "Name: Olga, score=23",
        "Name: Max, score=5"
    )

    val re = Regex("""^Name:\s*([A-Za-z]+)\s*,\s*score=(\d+)\s*$""")

    val pairs: List<Pair<String, Int>> = lines.mapNotNull { s ->
        val m = re.find(s) ?: return@mapNotNull null
        val name = m.groupValues[1]
        val score = m.groupValues[2].toInt()
        name to score
    }

    println("Task 1 pairs: $pairs")

    val best = pairs.maxByOrNull { it.second }
    if (best != null) {
        println("Task 1 best: ${best.first} (${best.second})")
    } else {
        println("Task 1: no valid lines")
    }
}

/*
2) Даты + коллекции
["2026-01-22", ...]
Преобразовать в даты, отсортировать, посчитать сколько в январе 2026.
*/
fun task2() {
    val dateStrings = listOf(
        "2026-01-22",
        "2026-02-01",
        "2025-12-31",
        "2026-01-05"
    )

    val fmt = DateTimeFormatter.ISO_LOCAL_DATE

    val dates = dateStrings.map { LocalDate.parse(it, fmt) }.sorted()

    println("Task 2 sorted dates: ${dates.joinToString { it.format(fmt) }}")

    val countJan2026 = dates.count { it.year == 2026 && it.month == Month.JANUARY }
    println("Task 2 count in Jan 2026: $countJan2026")
}

/*
3) Коллекции + строки
"apple orange apple banana orange apple"
Частоты слов, вывести слова с частотой > 1 по алфавиту.
*/
fun task3() {
    val text = "apple orange apple banana orange apple"

    val words = text.trim().split(Regex("""\s+""")).filter { it.isNotEmpty() }

    val freq = mutableMapOf<String, Int>()
    for (w in words) {
        freq[w] = (freq[w] ?: 0) + 1
    }

    println("Task 3 freq: $freq")

    val repeated = freq
        .filter { (_, c) -> c > 1 }
        .keys
        .sorted()

    println("Task 3 repeated words: ${repeated.joinToString(", ")}")
}

/*
4) Регулярные выражения: проверка формата
Дан список строк:
`["A-123", "B-7", "AA-12", "C-001", "D-99x"]`

Задача: оставить только строки формата: **одна заглавная буква**, затем `-`, затем **1–3 цифры**. Вывести отфильтрованный список.
*/
fun task4() {
    val listOfStrings = listOf("A-123", "B-7", "AA-12", "C-001", "D-99x")
    val regexPattern = Regex("""^([A-Z])-\d{1,3}$""")

    val checkedStrings = listOfStrings.filter { regexPattern.find(it) != null }
    checkedStrings.forEach { print("$it ") }
    println()
}

/*
Дан список строк:
`["  Hello   world  ", "A   B    C", "   one"]`

Задача: для каждой строки убрать пробелы по краям и заменить подряд идущие пробелы внутри на один пробел. Вывести результат.
*/

fun task5() {
    val listOfStrings = listOf("  Hello   world  ", "A   B    C", "   one")
    val patternToReplace = Regex("\\s{2,}")

    val validatedStrings = listOfStrings.map {
        it.trim().replace(patternToReplace, " ")
    }

    validatedStrings.forEach { println(it) }
}

/*
6) Даты: разница между двумя датами

Дан список пар дат (строки `"YYYY-MM-DD"`):
`[("2026-01-01","2026-01-10"), ("2025-12-31","2026-01-01"), ("2026-02-01","2026-01-22")]`

Задача: для каждой пары посчитать разницу в днях (`вторая - первая`) и вывести список чисел.
*/

fun task6() {
    val listOfDates = listOf(("2026-01-01" to "2026-01-10"), ("2025-12-31" to "2026-01-01"), ("2026-02-01" to "2026-01-22"))
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    val formattedDates = listOfDates.map {
        LocalDate.parse(it.first, formatter) to
        LocalDate.parse(it.second, formatter)
    }

    val differences = mutableListOf<Long>()
    for (pair in formattedDates) {
        val current = ChronoUnit.DAYS.between(pair.first, pair.second)
        differences.add(current)
    }

    differences.forEach { println(it) }
}


/*
7) Коллекции: группировка по ключу

Дан список строк:
`["math:Ivan", "bio:Olga", "math:Max", "bio:Ivan", "cs:Olga"]`

Задача: построить словарь вида `предмет -> список учеников`, сохранив порядок появления учеников внутри предмета. Вывести словарь.
*/

fun task7() {
    val inputList = listOf("math:Ivan", "bio:Olga", "math:Max", "bio:Ivan", "cs:Olga")
    val pattern = Regex("""([A-Za-z]+):([A-Za-z]+)""")
    val result = mutableMapOf<String, MutableList<String>>()
    inputList.forEach {
        if (pattern.containsMatchIn(it)) {
            val m = pattern.find(it)!!
            val subject = m.groupValues[1]
            val name = m.groupValues[2]

            if (result[subject] == null) {
                result[subject] = mutableListOf(name)
            } else {
                result[subject]!!.add(name)
            }
        }
    }

    result.forEach {
        print("${it.key}: ")
        it.value.forEach {
            print("${it} ")
        }
        print("\n")
    }
}


/*
8) Регулярные выражения + даты: извлечение времени из текста

Дан список строк:
`["Start at 2026/01/22 09:14", "No time here", "End: 22-01-2026 18:05"]`

Задача: найти строки, где есть дата и время, извлечь их и привести к формату `"YYYY-MM-DD HH:MM"`. Строки без времени игнорировать.
*/

fun task8() {
    val inputList = listOf("Start at 2026/01/22 09:14", "No time here", "End: 22-01-2026 18:05")

    // val formatter = DateTimeFormatter.ISO_LOCAL_DATE
    val regex1 = Regex("""(\d{4})/(\d{2})/(\d{2}) (\d{2}):(\d{2})""")
    val regex2 = Regex("""(\d{2})-(\d{2})-(\d{4}) (\d{2}):(\d{2})""")

    val result = mutableListOf<String>()

    for (s in inputList) {
        when {
            regex1.containsMatchIn(s) -> {
                val m = regex1.find(s)!!
                val formatted = "${m.groupValues[1]}-${m.groupValues[2]}-${m.groupValues[3]} ${m.groupValues[4]}:${m.groupValues[5]}"
                result.add(formatted)
            }
            regex2.containsMatchIn(s) -> {
                val m = regex2.find(s)!!
                val formatted = "${m.groupValues[3]}-${m.groupValues[2]}-${m.groupValues[1]} ${m.groupValues[4]}:${m.groupValues[5]}"
                result.add(formatted)
            }
        }
    }

    result.forEach { println(it) }
}