import java.util.*

const val FIZZ = "Fizz"
const val BUZZ = "Buzz"

sealed class Message(val divider: Int, val content: String) {
    object Fizz : Message(3, FIZZ)
    object Buzz : Message(5, BUZZ)
//    object FizzBuzz : Message()
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println(SeatingStudents(arrayOf(12, 1, 6, 9, 12)))
    println(findLongestUnique("2aabbcbbbadef"))
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

data class Time(
    val string: String,
    val minutes: Int
)

