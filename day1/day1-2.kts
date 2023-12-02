import java.io.File

fun second(input: String): Int {
    var sum = 0
    input.split("\n").forEach { line ->
        val replaceMap = mapOf(
            0 to "zero",
            1 to "one",
            2 to "two",
            3 to "three",
            4 to "four",
            5 to "five",
            6 to "six",
            7 to "seven",
            8 to "eight",
            9 to "nine"
        )
        var replacedLine = line
        replaceMap.forEach { (key, value) ->
            replacedLine = replacedLine.replace(key.toString(), value)
        }
        val indexMap = replaceMap.map { (key, value) ->
            key to replacedLine.indexOf(value)
        }.toMap().sortMapByValue().filter { it.value != -1 }
        val lastIndexMap = replaceMap.map { (key, value) ->
            key to replacedLine.lastIndexOf(value)
        }.toMap().sortMapByValue().filter { it.value != -1 }
        val firstDigit = indexMap.keys.first()
        val lastDigit = lastIndexMap.keys.last()
        sum += firstDigit * 10 + lastDigit
    }
    return sum
}

fun <K, V : Comparable<V>> Map<K, V>.sortMapByValue(): Map<K, V> {
    return this.toList().sortedBy { (_, value) -> value }.toMap()
}

val input = File("input").readLines().joinToString("\n")
val testInput2 = File("test-input-2").readLines().joinToString("\n")
println("second test result: ${second(testInput2)}, expected: 281")
println("second result: ${second(input)}")