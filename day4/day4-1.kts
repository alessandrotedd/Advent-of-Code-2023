import java.io.File
import kotlin.math.pow

fun first(input: String): Int {
    var score = 0
    input.split("\n").forEach { card ->
        val (winningNumbers, myNumbers) = card.replace("  ", " ")
                .let {
                    Regex("Card( +)\\d+: ").replace(it, "")
                }.split(" | ")
                .map { it.split(" ") }
                .map { it.toSet() }
        val myWinningNumbers = myNumbers.intersect(winningNumbers)
        if (myWinningNumbers.isNotEmpty()) {
            score += 2.0.pow(myWinningNumbers.size.toDouble() - 1).toInt()
        }
    }
    return score
}

val input = File("input").readLines().joinToString("\n")
val testInput1 = File("test-input-1").readLines().joinToString("\n")
println("first test result: ${first(testInput1)}, expected: 13")
println("first result: ${first(input)}")