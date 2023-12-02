import java.io.File

fun second(input: String): Int {
    var sum = 0
    input.split("\n").forEach { game ->
        game.split(": ").let { it ->
            var maxRedCubes = 0
            var maxGreenCubes = 0
            var maxBlueCubes = 0
            val draws = it[1].split("; ")
            draws.forEach { draw ->
                draw.split(", ").forEach { cubesDrawn ->
                    cubesDrawn.split(" ").let {
                        val amount = it[0].toInt()
                        val color = it[1]
                        if (color == "red" && amount > maxRedCubes) maxRedCubes = amount
                        if (color == "green" && amount > maxGreenCubes) maxGreenCubes = amount
                        if (color == "blue" && amount > maxBlueCubes) maxBlueCubes = amount
                    }
                }
            }
            sum += maxRedCubes * maxGreenCubes * maxBlueCubes
        }
    }
    return sum
}

val input = File("input").readLines().joinToString("\n")
val testInput2 = File("test-input-2").readLines().joinToString("\n")
println("second test result: ${second(testInput2)}, expected: 2286")
println("second result: ${second(input)}")