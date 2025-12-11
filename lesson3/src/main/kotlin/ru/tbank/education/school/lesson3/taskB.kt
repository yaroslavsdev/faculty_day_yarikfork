//package ru.tbank.education.school.lesson3
//
//data class Book(
//    val title: String,
//    val author: String,
//    val year: Int,
//    val genre: String
//)
//
//data class Person(
//    val name: String,
//    val age: Int
//)
//
//class Library {
//    private val books = mutableListOf<Book>()
//    private val people = mutableListOf<Person>()
//    private val takenBooks = mutableMapOf<Book, Person>() // Кто какую книгу взял
//
//    // Добавляет книгу в библиотеку
//    fun addBook(book: Book) {
//        books.add(book)
//    }
//
//    // Добавляет человека в список посетителей
//    fun addPerson(person: Person) {
//        people.add(person)
//    }
//
//    // Возвращает список всех доступных книг (не взятых)
//    fun getAvailableBooks(): List<Book> {
//        val freeBooks = books.filter { !takenBooks.containsKey(it) }
//        return freeBooks
//    }
//
//    // Возвращает список книг определённого автора
//    fun getBooksByAuthor(author: String): List<Book> {
//        val need = books.filter { it.author == author }
//        return need
//    }
//
//    // Возвращает список книг определённого жанра
//    fun getBooksByGenre(genre: String): List<Book> {
//        // todo: вернуть книги, у которых жанр совпадает с переданным (игнорируя регистр)
//    }
//
//    // Человек берёт книгу по названию
//    fun takeBook(personName: String, bookTitle: String): Boolean {
//        // 1. Найти человека по имени
//        // 2. Найти книгу по названию
//        // 3. Проверить, что книга существует и доступна (её нет в takenBooks)
//        // 4. Если всё в порядке — добавить запись в takenBooks и вернуть true
//        // 5. Иначе — вернуть false
//
//        val person = people.find { it.name.equals(personName, ignoreCase = true) }
//        val book = books.find { it.title.equals(bookTitle, ignoreCase = true) }
//        if (person != null && book != null && !takenBooks.containsKey(book)) {
//            takenBooks[book] = person
//            return true
//        }
//        return false
//    }
//
//    // Возвращает список всех посетителей
//    fun getAllPeople(): List<Person> {
//        val newList = people.toList()
//        return newList
//    }
//
//    // Возвращает книгу, которую взял человек (по имени)
//    fun getBooksTakenByPerson(personName: String): List<Book> {
//        // todo: вернуть список книг, которые взял человек с указанным именем
//        val booksByNameMap = takenBooks.filter { it.value.name.equals(personName, ignoreCase = true) }
//        //val booksByName =
//        //return booksByName
//    }
//
//    // Возвращает информацию о том, кто взял конкретную книгу
//    fun getPersonWhoTookBook(bookTitle: String): Person? {
//        // todo: найти книгу по названию и вернуть человека, который её взял (или null)
//        return null
//    }
//}
//
////Пример использования:
//fun main() {
//    val library = Library()
//
//    // Добавляем книги
//    library.addBook(Book("Война и мир", "Лев Толстой", 1869, "Роман"))
//    library.addBook(Book("Преступление и наказание", "Фёдор Достоевский", 1866, "Роман"))
//    library.addBook(Book("Мастер и Маргарита", "Михаил Булгаков", 1967, "Фантастика"))
//
//    // Добавляем людей
//    library.addPerson(Person("Анна", 25))
//    library.addPerson(Person("Иван", 30))
//
//    // Проверяем доступные книги
//    println("Доступные книги: ${library.getAvailableBooks().map { it.title }}")
//
//    // Берём книгу
//    val success = library.takeBook("Анна", "Мастер и Маргарита")
//    println("Книга взята: $success")
//
//    // Проверяем, кто взял книгу
//    val person = library.getPersonWhoTookBook("Мастер и Маргарита")
//    println("Книгу 'Мастер и Маргарита' взял: ${person?.name}")
//
//    // Проверяем, какие книги взял человек
//    println("Анна взяла: ${library.getBooksTakenByPerson("Анна").map { it.title }}")
//
//    // Проверяем доступные книги после взятия
//    println("Доступные книги: ${library.getAvailableBooks().map { it.title }}")
//
//    // Книги по жанру
//    println("Книги в жанре 'Роман': ${library.getBooksByGenre("Роман").map { it.title }}")
//}