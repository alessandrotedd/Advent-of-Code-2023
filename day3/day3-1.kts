import java.io.File

fun first(input: String): Int {
    var sum = 0
    var readNumber = 0
    var symbolFound = false
    val matrix = input.split("\n").map { it.toList() }

    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            val char = matrix[i][j]
            if (char.isDigit()) {
                // print(char)
                if (readNumber == 0) symbolFound = false
                findSymbolAroundDigit@ for (k in -1..1) {
                    for (l in -1..1) {
                        val isInBounds = i + k >= 0 && i + k < matrix.size && j + l >= 0 && j + l < matrix[i].size
                        if (isInBounds) {
                            val currentSymbol = matrix[i + k][j + l]
                            if (!currentSymbol.isDigit() && currentSymbol != '.') {
                                // print(currentSymbol)
                                symbolFound = true
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
                    if (symbolFound) {
                        sum += readNumber
                        // println("reading: $readNumber")
                    } // else println("skipping: $readNumber")
                    readNumber = 0
                }
            }
        }
    }
    return sum
}

val input = File("input").readLines().joinToString("\n")
val testInput1 = File("test-input-1").readLines().joinToString("\n")
println("first test result: ${first(testInput1)}, expected: 4361")
println("first result: ${first(input)}")