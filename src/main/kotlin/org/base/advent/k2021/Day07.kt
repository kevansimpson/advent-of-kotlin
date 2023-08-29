package org.base.advent.k2021

import org.base.advent.PuzzleSolver
import org.base.advent.util.Permutations.nthTriangleNumber
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2021/day/7">Day 7</a>
 */
class Day07 : PuzzleSolver<List<Int>> {
    override fun solve1(input: List<Int>): Any = leastFuel(input) { it }

    override fun solve2(input: List<Int>): Any = leastFuel(input) { nthTriangleNumber(it) }

    private fun leastFuel(input: List<Int>, calc: (Int) -> Int): Int =
        with(input.min()..input.max()) {
            fold(Pair(-1, Int.MAX_VALUE)) { pair, position ->
                val fuel = input.fold(0) { total, pos -> total + calc(abs(pos - position)) }
                if (fuel <= pair.second) position to fuel else pair
            }.second
        }
}
