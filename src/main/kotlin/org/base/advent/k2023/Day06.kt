package org.base.advent.k2023

import org.base.advent.PuzzleFunction

data class Race(val time: Long, val distance: Long)

/**
 * <a href="https://adventofcode.com/2023/day/6">Day 06</a>
 */
class Day06 : PuzzleFunction<List<Race>, Pair<Long, Long>> {

    override fun apply(input: List<Race>): Pair<Long, Long> =
        input.map { runFullRace(it) }.fold(1L) { a, b -> a * b } to runRace(mergeRaces(input))

    private fun runRace(race: Race): Long =
        with (race) {
            var left = 1
            while (((left * (time - left)) < distance))
                left++

            var right = time
            while (((right * (time - right)) < distance))
                right--

            right - left + 1L
        }

    private fun mergeRaces(input: List<Race>): Race =
        with (input.fold("" to "") { sum, r -> sum.first + r.time.toString() to sum.second + r.distance }) {
            Race(first.toLong(), second.toLong())
        }

    private fun runFullRace(race: Race): Int =
        with (race) {
            (1..time).map { it * (time - it) }.count { it > distance }
        }
}