fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println(SeatingStudents(arrayOf(12, 1, 6, 9, 12)))
}

fun SeatingStudents(arr: Array<Int>): Int {
    val k = arr[0]
    var count = 0
    val seatsTaken = arr.copyOfRange(1, arr.size)
    for (i in 1..k) {
        if (!seatsTaken.contains(i + 2) && !seatsTaken.contains(i) && (i + 2 < k)) {
            count++
        }
        if (i % 2 == 1 && !seatsTaken.contains(i) && !seatsTaken.contains(i + 1)) {
            count++
        }
    }
    return count
}

