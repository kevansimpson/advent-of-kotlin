package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractDigits

/**
 * <a href="https://adventofcode.com/2023/day/1">Day 1</a>
 */
class Day01 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> =
        input.fold(0 to 0) { sums, str ->
            (sums.first + combine(str.extractDigits())) to (sums.second + mapDigits(str))
        }

    internal fun combine(list: List<Int>): Int = "${list.first()}${list.last()}".toInt()

    private fun extractAllDigits(str: String): List<Int> =
        if (str.isBlank())
            listOf()
        else
            (DIGITS.find(str)?.value?.let { listOf(digitMap[it] ?: it.toInt()) } ?: listOf()) +
                    extractAllDigits(str.substring(1))

    internal fun mapDigits(str: String): Int = combine(extractAllDigits(str))

    companion object {
        private val DIGITS = "^(one|two|three|four|five|six|seven|eight|nine|[-\\d])".toRegex()
        private val digitMap: Map<String, Int> = mapOf(
            "one" to 1, "two" to 2, "three" to 3,
            "four" to 4, "five" to 5, "six" to 6,
            "seven" to 7, "eight" to 8, "nine" to 9)
    }
}