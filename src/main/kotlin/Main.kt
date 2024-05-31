package org.example

import java.util.*

fun main(args: Array<String>) {

    println("factorial:${factorial(4)}")
    printTimeArray(createTimeArray(arrayOf("8:45pm", "7:30am", "4:45pm")))
    println("capitalised:${capitaliser("here we go again")}")
}

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