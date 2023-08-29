package org.base.advent.k2019

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2019/day/04">Day 04</a>
 */
class Day04 : PuzzleFunction<IntRange, Pair<Int, Int>> {
    override fun apply(input: IntRange): Pair<Int, Int> {
        val prefiltered = input.map { it.toString() }.filter { isOrdered(it) }
        return prefiltered.count { hasAdjacent(it) } to prefiltered.count { hasOnlyAdjacent(it) }
    }

    private fun hasAdjacent(code: String): Boolean = ADJACENT.matches(code)

    private fun hasOnlyAdjacent(code: String): Boolean =
            code.toCharArray().toSet().fold(mutableMapOf<Char, Int>()) { map, ch ->
                map.apply { this[ch] = StringUtils.countMatches(code, ch) }
            }.containsValue(2)


    private fun isOrdered(code: String): Boolean = with (code) {
        this == toCharArray().apply { sort() }.joinToString("")
    }

    companion object {
        private val ADJACENT = "\\d*(\\d)(\\1)\\d*".toRegex()
    }
}