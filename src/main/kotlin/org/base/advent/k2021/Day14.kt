package org.base.advent.k2021

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleFunction
import org.base.advent.TimeSaver

/**
 * <a href="https://adventofcode.com/2021/day/14">Day 14</a>
 */
class Day14 : PuzzleFunction<List<String>, Pair<Long, Long>> {
    override fun apply(input: List<String>): Pair<Long, Long> =
        with (PolymerMath(input)) {
            smallPolymer to bigPolymer
        }

    data class PolymerMath(val input: List<String>) : TimeSaver {
        private val rootPolymer by lazy { input[0] }
        private val insertions by lazy {
            input.subList(2, input.size).map { it.split(" -> ") }.associate { it[0] to it[1][0] }
        }

        val smallPolymer = if (fullSolve) delta(countPolymer(nextPolymer(rootPolymer))) else growLarge(10)
        val bigPolymer = growLarge()

        // hat tip to https://github.com/maikka39/Advent-of-Code/blob/master/2021/14/part2.py
        private fun growLarge(steps: Int = 40): Long {
            val pairs = mutableMapOf<String, Long>()
            val elems = mutableMapOf<Char, Long>()
            rootPolymer.dropLast(1).forEachIndexed { i, ch ->
                val key = rootPolymer.substring(i, i + 2)
                safeIncrement(pairs, key, 1L)
                safeIncrement(elems, ch, 1L)
            }
            safeIncrement(elems, rootPolymer.last())

            (1..steps).forEach { _ ->
                pairs.toMap().forEach { (comb, count) ->
                    val newElem = insertions[comb] ?: throw IllegalArgumentException(comb)
                    safeIncrement(elems, newElem, count)
                    safeIncrement(pairs, comb, -count)
                    safeIncrement(pairs, "${comb[0]}$newElem", count)
                    safeIncrement(pairs, "$newElem${comb[1]}", count)
                }
            }
            return delta(elems)
        }

        private fun <T> safeIncrement(map: MutableMap<T, Long>, key: T, incr: Long = 1L) {
            map[key] = (map[key] ?: 0L) + incr
        }

        private fun nextPolymer(polymer: String): String = (1..10).fold(polymer) { p, _ ->
            val bldr = StringBuilder()
            for (index in 0 until (p.length - 1)) {
                val one = p[index]
                val two = p[index + 1]
                bldr.append(one).append(insertions["$one$two"])
            }
            bldr.append(p.last())
            bldr.toString()
        }

        private fun countPolymer(polymer: String): Map<Char, Long> =
            polymer.toCharArray().toSet().associateWith { StringUtils.countMatches(polymer, it).toLong() }.toSortedMap()

        private fun delta(count: Map<Char, Long>): Long = count.values.maxOrNull()!! - count.values.minOrNull()!!
    }
}