package org.base.advent.k2024

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2024/day/19">Day 19</a>
 */
class Day19 : PuzzleFunction<List<String>, Pair<Int, Long>> {

    override fun apply(input: List<String>): Pair<Int, Long> {
        val patterns = setOf(*input[0].split(", ").toTypedArray())
        val desired = input.subList(2, input.size)
        val cache = mutableMapOf<String, Long>()
        var possible = 0
        var count = 0L

        desired.forEach { d ->
            val c = countPossibleDesigns(d, patterns, cache)
            if (c > 0)
                possible++
            count += c
        }

        return possible to count
    }

    private fun countPossibleDesigns(design: String, patterns: Set<String>, cache: MutableMap<String, Long>): Long {
        if (design.isEmpty())
            return 1L
        if (cache.containsKey(design))
            return cache[design]!!

        var count = 0L
        patterns.forEach { p ->
            if (design.startsWith(p))
                count += countPossibleDesigns(design.substring(p.length), patterns, cache)
        }

        cache[design] = count
        return count
    }
}