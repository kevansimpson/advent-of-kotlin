package org.base.advent.k2016

import org.apache.commons.lang3.StringUtils.countMatches
import org.base.advent.PuzzleFunction
import org.base.advent.PuzzleSolver
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid
import org.base.advent.util.ReusableDigest
import org.base.advent.util.ReusableDigest.hashHex
import org.base.advent.util.ReusableDigest.hexCharToInt

/**
 * <a href="https://adventofcode.com/2016/day/18">Day 18</a>
 */
class Day18 : PuzzleFunction<String, Pair<Int, Int>> {
    override fun apply(input: String): Pair<Int, Int> {
        var row = padSafe(input)
        var safe = countMatches(input, ".")
        for (r in 1 until 40) {
            row = padSafe(nextRow(row, input.length))
            safe += countMatches(row, ".") - 2
        }
        val at40 = safe
        for (r in 40 until 400000) {
            row = padSafe(nextRow(row, input.length))
            safe += countMatches(row, ".") - 2
        }
        return at40 to safe
    }

    private fun nextRow(row: String, length: Int): String =
        StringBuilder().apply {
            for (i in 1..length)
                append(if (row[i - 1] == row[i + 1]) "." else "^")
        }.toString()

    private fun padSafe(row: String) = ".$row."
}