package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2024/day/1">Day 1</a>
 */
class Day01 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val list = input.map { it.split(" ")
            .filter { s -> s.isNotBlank() } }.map { arr -> arr[0].toInt() to arr[1].toInt() }
        val left = list.map { it.first }.sorted()
        val right = list.map { it.second }.sorted()

        return compareLists(left, right) to similarityScore(left, right)
    }

    private fun compareLists(left: List<Int>, right: List<Int>): Int {
        var sum = 0
        for (i in left.indices) {
            sum += abs(left[i] - right[i])
        }
        return sum
    }

    private fun similarityScore(left: List<Int>, right: List<Int>): Int {
        var sum = 0
        for (i in left.indices) {
            sum += left[i] * right.count { it == left[i] }
        }
        return sum
    }
}