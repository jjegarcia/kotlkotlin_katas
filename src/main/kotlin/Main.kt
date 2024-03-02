import java.util.*

const val FIZZ = "Fizz"
const val BUZZ = "Buzz"

sealed class Message(val divider: Int, val content: String) {
    object Fizz : Message(3, FIZZ)
    object Buzz : Message(5, BUZZ)
//    object FizzBuzz : Message()
}

//class MyCode {
//    var time = 0

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println(SeatingStudents(arrayOf(12, 1, 6, 9, 12)))
//    println(findLongestUnique("2aabbcbbbadef"))

    val array = arrayOf(1, 3, 2, 3, 3)
    val map = getFrequencyMap(array)

    val test = sortByFrequency(array)

    val test0 = getMinimumItems(test.toMutableList())

    val test1 = getMinimumItems(sortByFrequency(arrayOf(3, 3, 3, 1, 2)).toMutableList())
    val test2 = getMinimumItems(sortByFrequency(arrayOf(1, 3, 3, 3, 2)).toMutableList())
    val test3 = getMinimumItems(sortByFrequency(arrayOf(1, 2, 3, 3, 3)).toMutableList())

    val test4 = processQueue(Queue(0, arrayOf(2, 2, 3, 1)))

    val test5 = candy(arrayOf(1, 0, 2))
//        val test6 = candy2(arrayOf(1, 0, 2))
    val test7 = findRequestsInQueue(arrayOf(2, 2, 3, 1))
    val test8 = getMinimumFruits(fruits = arrayOf(1, 2, 3, 3, 3))
}

fun getMinimumFruits(fruits: Array<Int>): Int {
    return getMinimumItems(sortByFrequency(fruits).toMutableList()).size
}

fun findRequestsInQueue(wait: Array<Int>): Array<Int> {
    if (wait.isEmpty()) return arrayOf()
    val time = 0
    val requests = wait.toMutableList()
    val newRequests = processRequests(requests, time)
    return newRequests.toTypedArray()
}

fun processRequests(requests: MutableList<Int>, time: Int): MutableList<Int> {
    requests.removeAt(0)
    val newRequests = removeDueItems(requests, time)
    return newRequests
}

fun removeDueItems(requests: MutableList<Int>, time: Int): MutableList<Int> {
    val newRequests = mutableListOf<Int>()
    requests.forEach { item ->
        if (item > time + 1) {
            newRequests.add(item)
        }
    }
    return newRequests
}

data class Queue(val time: Int, val items: Array<Int>)
data class MutableQueue(val time: Int, val items: MutableList<Int>)

fun processQueue(queue: Queue): Queue {
    println(queue)
    val mutableQueue = MutableQueue(queue.time, queue.items.toMutableList())
    if (queue.items.isEmpty()) return queue
    mutableQueue.items.removeAt(0)
    val newQueue = removeDueItems(mutableQueue.copy(items = mutableQueue.items))
    return processQueue(queue.copy(items = newQueue.items.toTypedArray(), time = queue.time + 1))
}

private fun removeDueItems(queue: MutableQueue): MutableQueue {
    val newItems = mutableListOf<Int>()
    queue.items.forEach { item ->
        if (item > queue.time + 1) {
            newItems.add(item)
        }
    }
    return queue.copy(items = newItems)
}

fun candy2(arr: Array<Int>): Int {
    val candies = arr.map { 1 }.toMutableList()
    arr.forEachIndexed() { index, item ->
        candies[index] = Math.max(candies[index], candies[index + 1] + 1)
    }
    return 0
}

fun candy(arr: Array<Int>): Int {
    val n = arr.size
    val candies = IntArray(n)
    for (i in 0 until n) {
        candies[i] = 1
    }
    for (i in 1 until n) {
        if (arr[i] > arr[i - 1]) {
            candies[i] = candies[i - 1] + 1
        }
    }
    for (i in n - 2 downTo 0) {
        if (arr[i] > arr[i + 1]) {
            candies[i] = Math.max(candies[i], candies[i + 1] + 1)
        }
    }
    return candies.sum()
}

fun findLongestUnique(str: String): String {
    val k = str[0].code - 48
    val myString = str.subSequence(1, str.length)
    var candidate = ""
    var bundle = ""
    var newCandidate = ""
    myString.forEachIndexed { index, char ->
        newCandidate += char
        if (!bundle.contains(char)) {
            bundle += char
            if (bundle.length == k + 1) {
                val lastIndex = getLastIndex(newCandidate, bundle[0])
                newCandidate = newCandidate.subSequence(lastIndex + 1, newCandidate.length) as String
                bundle = bundle.subSequence(1, bundle.length - 1) as String
                bundle += char
            }
        }
        if (newCandidate.length > candidate.length) candidate = newCandidate
    }
    return candidate
}

fun getLastIndex(str: String, char: Char): Int {
    var outIndex = 0
    str.forEachIndexed { index, c ->
        if (c == char) {
            outIndex = index
        }
    }
    return outIndex
}

fun scanUniques(str: String): String {
    var uniques = ""
    str.forEach { char ->
        if (!uniques.contains(char)) uniques = uniques + char
    }
    return uniques
}

fun SeatingStudents(arr: Array<Int>): Int {
    val k = arr[0]
    var count = 0
    val seatsTaken = arr.copyOfRange(1, arr.size)
    for (i in 1..k) {
        if (!seatsTaken.contains(i + 2) && !seatsTaken.contains(i) && (i + 2 < k) || (i % 2 == 1 && !seatsTaken.contains(
                i
            ) && !seatsTaken.contains(i + 1))
        ) {
            count++
        }
    }
    return count
}

fun fizzBuzz(n: Int): Unit {
    // Write your code here
    for (i in 1..n) {
        println(determineOut(i))
    }
}

private fun determineOut(i: Int): String {
    val isFizz = determineMatch(i, Message.Fizz)
    val isBuzz = determineMatch(i, Message.Buzz)
    return when {
        isFizz && isBuzz -> "${Message.Fizz.content}${Message.Buzz.content}"
        isFizz -> Message.Fizz.content
        isBuzz -> Message.Buzz.content
        else -> i.toString()
    }
}

private fun determineMatch(i: Int, message: Message) = i.rem(message.divider) == 0

fun capitaliser(message: String): String {
    var test = ""
    val parsed = message.split(" ").map { word ->
        word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
    parsed.forEachIndexed { index, word ->
        if (index < parsed.size - 1) test += word + " "
        else test += word
    }
    return test
}

fun printTimeArray(array: Array<Time>) {
    array.forEachIndexed { index, time ->
        println("${index + 1}) ${time.string}")
    }
}

fun createTimeArray(inputArray: Array<String>): Array<Time> {
    return inputArray.map { str ->
        Time(str, createTimeItem(item = str))
    }.sortedBy { time ->
        time.minutes
    }.toTypedArray()
}

fun createTimeItem(item: String): Int {
    val test = item.split(":")
    return if (test[1].contains("pm")) {
        (test[0].toInt() + 12) * 60 + test[1].replace("pm", "").toInt()
    } else {
        test[0].toInt() + test[1].replace("am", "").toInt()
    }
}

fun factorial(n: Int): Int {
    return if (n > 1) {
        n * factorial(n - 1)
    } else n
}

fun getMinimumItems(items: MutableList<Int>): MutableList<Int> {
    if (items.size < 2) return items
    items.forEachIndexed { index, item ->
        if (index < items.size - 1) {
            if (item != items[index + 1]) {
                items.removeAt(index)
                items.removeAt(index)
                return getMinimumItems(items)
            }
        }
    }
    return items
}

fun getFrequencyMap(arr: Array<Int>): Map<Int, Int> {
    val frequencyMap = mutableMapOf<Int, Int>()
    arr.forEach { item ->
        if (frequencyMap.containsKey(item)) {
            frequencyMap[item] = (frequencyMap[item] ?: 0) + 1
        } else {
            frequencyMap[item] = 1
        }
    }
    return frequencyMap
}

fun sortByFrequency(arr: Array<Int>): Array<Int> {
    val frequencyMap = getFrequencyMap(arr)
    val sortedMap = frequencyMap.toList().sortedByDescending { (_, value) -> value }.toMap()
    val sortedArray = mutableListOf<Int>()
    sortedMap.forEach { (key, value) ->
        for (i in 1..value) {
            sortedArray.add(key)
        }
    }
    return sortedArray.toTypedArray()
}

data class Time(
    val string: String,
    val minutes: Int
)

