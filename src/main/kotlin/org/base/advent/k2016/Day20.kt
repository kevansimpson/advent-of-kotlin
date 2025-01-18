package org.base.advent.k2016

import org.base.advent.PuzzleFunction
import org.base.advent.PuzzleSolver
import org.base.advent.util.Extensions.merge

/**
 * <a href="https://adventofcode.com/2016/day/20">Day 20</a>
 */
class Day20 : PuzzleFunction<List<String>, Pair<Long, Long>> {
    override fun apply(input: List<String>): Pair<Long, Long> {
        val ranges = input.map { it.split("-") }
            .map { it[0].toLong()..it[1].toLong() }
            .sortedBy { it.first }
        val result = longArrayOf(-1L, 0L)
        var overlap = LongRange.EMPTY
        ranges.forEach { range ->
            val pair = range.merge(overlap)
            if (pair.second != null) {
                result[1] +=  (pair.second!!.first - pair.first.last - 1L)
                if (result[0] < 0L)
                    result[0] = pair.first.last + 1L

                overlap = pair.second!!
            }
            else
                overlap = pair.first
        }

        return result[0] to result[1]
    }
}