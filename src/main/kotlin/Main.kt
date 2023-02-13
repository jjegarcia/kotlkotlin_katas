fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println(SeatingStudents(arrayOf(12, 1, 6, 9, 12)))
    println(findLongestUnique("3aabacbebebe"))
}

fun findLongestUnique(str: String): String {
    val k = str[0].toInt() - 48
    val myString = str.subSequence(1, str.length)
    var candidate = ""
    var bundle = ""
    var newCandidate = ""
    myString.forEachIndexed { index, char ->
        if (bundle.length <= k) newCandidate += char
        if (!bundle.contains(char)) {
            if (bundle.length == k) {
                val lastIndex = getLastIndex(newCandidate, bundle[0])
                if (newCandidate.length > candidate.length) candidate = newCandidate
                newCandidate = newCandidate.subSequence(lastIndex + 1, newCandidate.length) as String
                bundle = bundle.subSequence(1, bundle.length) as String
                bundle += char
            } else {
                if (bundle.length < k) bundle += char
            }
        }
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

