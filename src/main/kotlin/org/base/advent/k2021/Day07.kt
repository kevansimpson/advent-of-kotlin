package org.base.advent.k2021

import org.base.advent.PuzzleReader
import org.base.advent.util.Permutations.nthTriangleNumber
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2021/day/7">Day 7</a>
 */
class Day07 : PuzzleReader {

    private val input = readSingleLine("2021/input07.txt").split(",").map { it.toInt() }

    override fun solve1(): Any = leastFuel { it }

    override fun solve2(): Any = leastFuel { nthTriangleNumber(it) }

    private val min = input.minOrNull()!!
    private val max = input.maxOrNull()!!

    private fun leastFuel(calc: (Int) -> Int): Int =
            (min..max).fold(Pair(-1, Int.MAX_VALUE)) { pair, position ->
                val fuel = input.fold(0) { total, pos -> total + calc(abs(pos - position)) }
                if (fuel <= pair.second) position to fuel else pair
            }.second
}