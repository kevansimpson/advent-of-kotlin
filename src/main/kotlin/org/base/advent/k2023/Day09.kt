package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractLong

/**
 * <a href="https://adventofcode.com/2023/day/9">Day 09</a>
 */
class Day09 : PuzzleFunction<List<String>, Pair<Long, Long>> {

    override fun apply(input: List<String>): Pair<Long, Long> =
        with (input.map { it.extractLong() }) {
            sumOf { extrapolate(it).last() } to sumOf { extrapolateBackwards(it).first() }
        }

    private fun extrapolate(history: List<Long>): List<Long> =
        if (history.all { it == history[0] })
            history
        else {
            val next = mutableListOf<Long>()
            for (i in 0 until history.size - 1)
                next.add(history[i + 1] - history[i])
            history + (history.last() + extrapolate(next).last())
        }

    private fun extrapolateBackwards(history: List<Long>): List<Long> =
        if (history.all { it == history[0] })
            history
        else {
            val next = mutableListOf<Long>()
            for (i in 0 until history.size - 1)
                next.add(history[i + 1] - history[i])
            listOf(history.first() - extrapolateBackwards(next).first()) + history
        }
}