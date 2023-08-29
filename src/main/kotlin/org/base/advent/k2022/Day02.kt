package org.base.advent.k2022

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2022/day/02">Day 02</a>
 */
class Day02 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val pairs = input.map { SCORES[it] }
        return pairs.sumOf { it!!.first } to pairs.sumOf { it!!.second }
    }

    companion object {
        private val SCORES = mapOf(
                "A X" to (4 to 3), "B X" to (1 to 1), "C X" to (7 to 2),
                "A Y" to (8 to 4), "B Y" to (5 to 5), "C Y" to (2 to 6),
                "A Z" to (3 to 8), "B Z" to (9 to 9), "C Z" to (6 to 7))
    }
}