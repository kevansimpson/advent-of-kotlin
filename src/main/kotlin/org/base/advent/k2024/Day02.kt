package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2024/day/2">Day 2</a>
 */
class Day02 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val safeSingle = intArrayOf(0, 0)
        for (line in input) {
            val levels = line.split(" ").map { it.toInt() }.toIntArray()
            if (isSafe(levels))
                safeSingle[0]++
            if (isSingleBad(levels))
                safeSingle[1]++
        }

        return safeSingle[0] to safeSingle[1]
    }

    private fun isSafe(levels: IntArray): Boolean {
        val sorted = levels.copyOf().sorted().toIntArray()
        if (levels[0] > levels[1])
            sorted.reverse()
        else if (levels[0] == levels[1])
            return false

        if (levels.contentEquals(sorted)) {
            for (i in 0..< (levels.size - 1)) {
                val diff = abs(levels[i] - levels[i + 1])
                if (diff < 1 || diff > 3)
                    return false
            }
            return true
        }

        return false
    }

    private fun isSingleBad(levels: IntArray): Boolean {
        for (i in levels.indices) {
            if (isSafe(remove(levels, i)))
                return true
        }

        return false
    }

    private fun remove(arr: IntArray, index: Int): IntArray {
        val removed = IntArray(arr.size - 1)
        var k = 0
        for (i in arr.indices) {
            if (i != index)
                removed[k++] = arr[i]
        }

        return removed
    }
}