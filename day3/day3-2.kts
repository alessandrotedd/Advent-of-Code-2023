import java.io.File

fun second(input: String): Int {
    var readNumber = 0
    var symbolFoundAt: Pair<Int, Int>? = null
    val matrix = input.split("\n").map { it.toList() }
    val symbolToNumbersMap = mutableMapOf<Pair<Int, Int>, List<Int>>()

    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            val char = matrix[i][j]
            if (char.isDigit()) {
                // print(char)
                if (readNumber == 0) symbolFoundAt = null
                findSymbolAroundDigit@ for (k in -1..1) {
                    for (l in -1..1) {
                        val isInBounds = i + k >= 0 && i + k < matrix.size && j + l >= 0 && j + l < matrix[i].size
                        if (isInBounds) {
                            val currentSymbol = matrix[i + k][j + l]
                            if (currentSymbol == '*') {
                                // print(currentSymbol)
                                symbolFoundAt = Pair(i + k, j + l)
                                continue@findSymbolAroundDigit
                            }
                        }
                    }
                }
                readNumber += char.toString().toInt()
                // println()
                if (matrix[i].size > j + 1 && matrix[i][j + 1].isDigit()) {
                    readNumber *= 10
                } else {
                    if (symbolFoundAt != null) {
                        symbolToNumbersMap.getOrElse(symbolFoundAt) { null }?.let {
                            symbolToNumbersMap.put(symbolFoundAt, it + readNumber)
                        } ?: run {
                            symbolToNumbersMap.putIfAbsent(symbolFoundAt, mutableListOf(readNumber))
                        }
                        // println("reading: $readNumber")
                    } // else println("skipping: $readNumber")
                    readNumber = 0
                }
            }
        }
    }
    return symbolToNumbersMap.filter {
        it.value.size == 2
    }.map {
        it.value[0] * it.value[1]
    }.sum()
}

val input = File("input").readLines().joinToString("\n")
val testInput2 = File("test-input-2").readLines().joinToString("\n")
println("first test result: ${second(testInput2)}, expected: 467835")
println("first result: ${second(input)}")