import java.io.File

fun first(input: String): Int {
    var sum = 0
    input.split("\n").forEach { line ->
        var firstDigit = 0
        var lastDigit = 0
        line.forEach { char ->
            if (char.isDigit()) {
                if (firstDigit == 0) {
                    firstDigit = char.toString().toInt()
                }
                lastDigit = char.toString().toInt()
            }
        }
        sum += firstDigit * 10 + lastDigit
    }
    return sum
}

val input = File("input").readLines().joinToString("\n")
val testInput1 = File("test-input-1").readLines().joinToString("\n")
println("first test result: ${first(testInput1)}, expected: 142")
println("first result: ${first(input)}")