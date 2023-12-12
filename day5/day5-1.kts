import java.io.File

fun first(input: String): Long {
    var seeds = listOf<Long>()
    var nextCategory = seeds.toMutableList()
    input.replace("seeds: ", "")
        .replace("seed-to-soil map:", "")
        .replace("soil-to-fertilizer map:", "")
        .replace("fertilizer-to-water map:", "")
        .replace("water-to-light map:", "")
        .replace("light-to-temperature map:", "")
        .replace("temperature-to-humidity map:", "")
        .replace("humidity-to-location map:", "")
        .replace("\r", "")
        .split("\n\n\n")
        .also { seeds = it.first().split(" ").map { it.toLong() } }
        .drop(1)
        .forEach { category ->
            val sourceRanges = mutableListOf<LongRange>()
            val destinationDeltas = mutableListOf<Long>()
            category.split("\n").forEach {
                val (destinationRangeStart, sourceRangeStart, rangeLength) = it.split(" ").map { it.toLong() }
                val sourceRange = sourceRangeStart..(sourceRangeStart + rangeLength)
                val destinationDelta = destinationRangeStart - sourceRangeStart
                sourceRanges.add(sourceRange)
                destinationDeltas.add(destinationDelta)
            }

            seeds.forEach { seed ->
                sourceRanges.firstOrNull { seed in it }?.let { sourceRange ->
                    val destinationDelta = destinationDeltas[sourceRanges.indexOf(sourceRange)]
                    val destination = seed + destinationDelta
                    nextCategory.add(destination)
                } ?: run {
                    nextCategory.add(seed)
                }
            }
            seeds = nextCategory
            nextCategory = mutableListOf()
        }
    return seeds.min()
}

val input = File("input").readLines().joinToString("\n")
val testInput1 = File("test-input-1").readLines().joinToString("\n")
println("first test result: ${first(testInput1)}, expected: 35")
println("first result: ${first(input)}")