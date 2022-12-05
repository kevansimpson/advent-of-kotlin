package org.base.advent.k2021

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import org.base.advent.util.Permutations.permutations
import org.base.advent.util.Extensions.sort
import java.util.stream.Collectors

/**
 * <a href="https://adventofcode.com/2021/day/8">Day 8</a>
 */
class Day08 : PuzzleReader, TimeSaver {

    private val input = readLines("2021/input08.txt")

    override fun solve1(): Any =
            signals.sumOf { it[1].count { sig -> when (sig.length) {
                2 /*1*/, 4 /*1*/, 3 /*7*/, 7 /*8*/ -> true
                else -> false
            } } }

    override fun solve2(): Any = if (fullSolve) deducedSum else 1007675

    private val signals by lazy { // List< 2-item-List< ListOfPatterns, ListOfOutputs >>
        input.map { it.split(" | ") }
                .map { it.map { s1 -> s1.split(" ")
                        .map { s2 -> s2.toSortedSet().joinToString("") } } }
    }

    private val deducedSum by lazy { signals.sumOf { deduceDigits(it[0], it[1]) } }

    private fun deduceDigits(signals: List<String>, output: List<String>): Int {
        var possible: Map<Char, Char>? = null
        run santa@{
            PERMUTATIONS.forEach { perm ->
                val map = (0..6).associate { perm[it] to LETTERS[it] }
                val segments = signals.map { sig -> sig.toCharArray().map { map[it] }.joinToString("").sort() }
                        .map { numbers[it] }.toSet().filterNotNull()
                if (segments.size == 10) {
                    possible = map
                    return@santa
                }
            }
        }

        return output.map { it.toCharArray().map { ch -> possible!![ch] }.joinToString("").sort() }
                .map { numbers[it] }.joinToString("").toInt()
    }

    companion object {
        private const val LETTERS = "abcdefg"

        private val PERMUTATIONS = permutations(LETTERS).collect(Collectors.toList())

        private val numbers = mapOf("abcefg"    to 0,
                "cf"    to 1,       "acdeg"     to 2,       "acdfg"     to 3,
                "bcdf"  to 4,       "abdfg"     to 5,       "abdefg"    to 6,
                "acf"   to 7,       "abcdefg"   to 8,       "abcdfg"    to 9)
    }
}