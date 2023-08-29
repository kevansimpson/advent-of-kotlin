package org.base.advent.k2016

import org.base.advent.PuzzleSolver
import java.util.*

/**
 * <a href="https://adventofcode.com/2016/day/3">Day 3</a>
 */
class Day03 : PuzzleSolver<List<String>> {

    override fun solve1(input: List<String>): Any = trianglesByRow(codes(input))

    override fun solve2(input: List<String>): Any = trianglesByColumn(codes(input))

    private fun codes(input: List<String>) =
        input.map { REGEX.findAll(it.trim()).toList().map { m -> m.value.toInt() }.toIntArray() }

    private fun trianglesByRow(codes: List<IntArray>): Int = codes.count { isValidTriangle(it) }

    private fun trianglesByColumn(codes: List<IntArray>): Int =
            (codes.indices step 3).fold(0) { valid, index ->
                val row1 = codes[index]
                val row2 = codes[index + 1]
                val row3 = codes[index + 2]
                valid + countTriangle(row1[0], row2[0], row3[0]) +
                        countTriangle(row1[1], row2[1], row3[1]) +
                        countTriangle(row1[2], row2[2], row3[2])
            }

    private fun countTriangle(a: Int, b: Int, c: Int): Int = if (isValidTriangle(intArrayOf(a, b, c))) 1 else 0

    private fun isValidTriangle(sides: IntArray): Boolean {
        Arrays.sort(sides)
        return sides.size == 3 && (sides[0] + sides[1]) > sides[2]
    }

    companion object {
        private val REGEX = "(\\d+)".toRegex()
    }
}