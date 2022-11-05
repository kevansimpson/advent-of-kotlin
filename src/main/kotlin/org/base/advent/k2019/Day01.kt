package org.base.advent.k2019

import org.base.advent.PuzzleReader
import kotlin.math.floor

/**
 * <a href="https://adventofcode.com/2019/day/01">Day 01</a>
 */
class Day01 : PuzzleReader {

    private val input = readLines("2019/input01.txt").map { it.toInt() }

    override fun solve1(): Any = input.sumOf { calculate(it) }

    override fun solve2(): Any = input.sumOf { accumulate(it) }

    private fun calculate(mass: Int) = (floor((mass / 3).toDouble()) - 2).toInt()

    private fun accumulate(mass: Int): Int {
        var fuel = calculate(mass)
        var total = 0
        while (fuel > 0) {
            total += fuel
            fuel = calculate(fuel)
        }
        return total
    }
}