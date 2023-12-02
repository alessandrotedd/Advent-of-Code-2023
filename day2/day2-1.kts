import java.io.File

fun first(input: String): Int {
    val maxRedCubes = 12
    val maxGreenCubes = 13
    val maxBlueCubes = 14

    var sum = 0

    input.split("\n").forEach { game ->
        run game@{
            game.split(": ").let { it ->
                val gameId = it[0].replace("Game ", "").toInt()
                val draws = it[1].split("; ")
                draws.forEach { draw ->
                    draw.split(", ").forEach { cubesDrawn ->
                        cubesDrawn.split(" ").let {
                            val amount = it[0].toInt()
                            val color = it[1]
                            if (color == "red" && amount > maxRedCubes ||
                                color == "green" && amount > maxGreenCubes ||
                                color == "blue" && amount > maxBlueCubes
                            ) {
                                return@game
                            }
                        }
                    }
                }
                sum += gameId
            }
        }
    }
    return sum
}

val input = File("input").readLines().joinToString("\n")
val testInput1 = File("test-input-1").readLines().joinToString("\n")
println("first test result: ${first(testInput1)}, expected: 8")
println("first result: ${first(input)}")