import java.io.File
import kotlin.math.pow

fun second(input: String): Int {
    var cardsPlayed = 0
    val winsList = mutableListOf<Int>()
    val cardsToPlay = MutableList(input.split("\n").size) { 1 }
    input.split("\n").forEachIndexed { cardId, card ->
        val (winningNumbers, myNumbers) = card.replace("  ", " ")
                .let {
                    Regex("Card( +)\\d+: ").replace(it, "")
                }.split(" | ")
                .map { it.split(" ") }
                .map { it.toSet() }
        val wins = myNumbers.intersect(winningNumbers).size
        winsList.add(wins)
    }
    for (cardId in 0 until cardsToPlay.size) {
        val wins = winsList[cardId]
        val timesToPlay = cardsToPlay[cardId]
        repeat(timesToPlay) {
            // println("playing card ${cardId + 1}, wins: $wins")
            cardsPlayed++
            for (i in 1..wins) {
                cardsToPlay[cardId + i]++
                // println(" - new card: ${cardId + i + 1}")
            }
        }
    }
    return cardsPlayed
}

val input = File("input").readLines().joinToString("\n")
val testInput2 = File("test-input-2").readLines().joinToString("\n")
println("first test result: ${second(testInput2)}, expected: 30")
println("first result: ${second(input)}")