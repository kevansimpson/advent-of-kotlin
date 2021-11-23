package org.base.advent.k2015

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import org.base.advent.util.Permutations.permutations

/**
 * <a href="https://adventofcode.com/2015/day/13">Day 13</a>
 */
class Day13 : PuzzleReader, TimeSaver {

    private val input = readLines("2015/input13.txt")

    override fun solve1(): Any = solveOptimal(people)

    override fun solve2(): Any = solveOptimal(people + ME)

    private val distanceMap by lazy {
        input.map { REGEX.matchEntire(it) }.associate {
            val (p1, gainsOr, dist, p2) = it!!.destructured
            Pair(Pair(p1, p2), dist.toInt() * (if ("gain" == gainsOr) 1 else -1))
        }
    }

    private val people by lazy { distanceMap.keys.flatMap { it.toList() }.toSet() }

    private fun solveOptimal(persons: Set<String>): Int {
        val map = mutableMapOf<List<String>, Int>()
        permutations(persons.toList()).forEach { perm ->
            val list = perm.toList()
            map[list] = calculate(list)
        }

        val max = map.maxByOrNull { it.value }
        debug("Optimal seating arrangement is ${max?.key}")
        return max?.value ?: -1
    }

    private fun calculate(path: List<String>): Int {
        val last = path.size - 1
        var dist = delta(path[last], path[0]) + delta(path[0], path[last])
        for (i in 0 until last) {
            val p1 = path[i]
            val p2 = path[i + 1]
            dist += delta(p1, p2)
            dist += delta(p2, p1)
        }

        return dist
    }

    private fun delta(p1: String, p2: String): Int =
        if (ME == p1 || ME == p2) 0 else distanceMap[Pair(p1, p2)] ?: throw NullPointerException("$p1-$p2")

    companion object {
        private const val ME = "ME"
        private val REGEX = "(.+) would (.+) (\\d+).*to (.+)\\.".toRegex()
    }
}