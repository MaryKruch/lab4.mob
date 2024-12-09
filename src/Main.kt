import kotlin.math.sqrt

// Задача 1: Поменять местами минимальный и максимальный элемент списка
fun swapMinMax(list: MutableList<Int>) {
    if (list.size < 2) return

    val minIndex = list.indexOf(list.minOrNull()!!)
    val maxIndex = list.indexOf(list.maxOrNull()!!)

    val temp = list[minIndex]
    list[minIndex] = list[maxIndex]
    list[maxIndex] = temp
}

// Задача 2: Преобразовать строку или список чисел в множество и вывести его мощность
fun toSet(input: Any): Pair<Set<Any>, Int> {
    val set: Set<Any> = when (input) {
        is String -> input.toSet()
        is List<*> -> setOf(input.toSet())
        else -> throw IllegalArgumentException("Input must be a String or List")
    }
    return Pair(set, set.size)
}

// Задача 3: Обработка базы данных о продажах интернет-магазина
fun processSalesData(salesData: List<String>): Map<String, Map<String, Int>> {
    val salesMap = mutableMapOf<String, MutableMap<String, Int>>()

    for (record in salesData) {
        val (buyer, item, quantity) = record.split(" ")
        val quantityInt = quantity.toInt()

        if (buyer !in salesMap) {
            salesMap[buyer] = mutableMapOf()
        }

        val itemMap = salesMap[buyer]!!
        itemMap[item] = itemMap.getOrDefault(item, 0) + quantityInt
    }

    return salesMap
}

fun printSalesData(salesMap: Map<String, Map<String, Int>>) {
    val sortedBuyers = salesMap.keys.sorted()
    for (buyer in sortedBuyers) {
        println("Покупатель: $buyer")
        val sortedItems = salesMap[buyer]!!.toSortedMap()
        for ((item, quantity) in sortedItems) {
            println("  Товар: $item, Количество: $quantity")
        }
    }
}

fun main() {
    // Задача 1: Поменять местами минимальный и максимальный элемент списка
    println("Задача 1: Поменять местами минимальный и максимальный элемент списка")
    val list = mutableListOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5)
    println("Исходный список: $list")
    swapMinMax(list)
    println("Список после замены: $list")

    // Задача 2: Преобразовать строку или список чисел в множество и вывести его мощность
    println("\nЗадача 2: Преобразовать строку или список чисел в множество и вывести его мощность")
    val stringInput = "hello world"
    val listInput = listOf(1, 2, 3, 4, 5, 1, 2, 3)
    val (stringSet, stringSetSize) = toSet(stringInput)
    val (listSet, listSetSize) = toSet(listInput)
    println("Множество из строки: $stringSet, Мощность: $stringSetSize")
    println("Множество из списка: $listSet, Мощность: $listSetSize")

    // Задача 3: Обработка базы данных о продажах интернет-магазина
    println("\nЗадача 3: Обработка базы данных о продажах интернет-магазина")
    val salesData = listOf(
        "Alice book 2",
        "Bob toy 1",
        "Alice toy 3",
        "Charlie book 1",
        "Bob book 2"
    )
    val salesMap = processSalesData(salesData)
    printSalesData(salesMap)
}