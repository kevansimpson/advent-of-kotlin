package org.base.advent.k2015

import org.base.advent.PuzzleFunction
import org.base.advent.TimeSaver
import org.base.advent.util.Permutations.permutations

/**
 * <a href="https://adventofcode.com/2015/day/13">Day 13</a>
 */
class Day13 : PuzzleFunction<List<String>, Pair<Int, Int>>, TimeSaver {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val distanceMap = input.map { REGEX.matchEntire(it) }
            .associate {
                val (p1, gainsOr, dist, p2) = it!!.destructured
                Pair(Pair(p1, p2), dist.toInt() * (if ("gain" == gainsOr) 1 else -1))
            }
        val people = distanceMap.keys.flatMap { it.toList() }.toSet()
        return solveOptimal(distanceMap, people) to solveOptimal(distanceMap, people + ME)
    }

    private fun solveOptimal(distanceMap: Map<Pair<String, String>, Int>, persons: Set<String>): Int {
        val map = mutableMapOf<List<String>, Int>()
        permutations(persons.toList()).forEach { perm ->
            val list = perm.toList()
            map[list] = calculate(distanceMap, list)
        }

        val max = map.maxByOrNull { it.value }
        debug("Optimal seating arrangement is ${max?.key}")
        return max?.value ?: -1
    }

    private fun calculate(distanceMap: Map<Pair<String, String>, Int>, path: List<String>): Int {
        val last = path.size - 1
        var dist = delta(distanceMap, path[last], path[0]) + delta(distanceMap, path[0], path[last])
        for (i in 0 until last) {
            val p1 = path[i]
            val p2 = path[i + 1]
            dist += delta(distanceMap, p1, p2)
            dist += delta(distanceMap, p2, p1)
        }

        return dist
    }

    private fun delta(distanceMap: Map<Pair<String, String>, Int>, p1: String, p2: String): Int =
        if (ME == p1 || ME == p2) 0 else distanceMap[Pair(p1, p2)] ?: throw NullPointerException("$p1-$p2")

    companion object {
        private const val ME = "ME"
        private val REGEX = "(.+) would (.+) (\\d+).*to (.+)\\.".toRegex()
    }
}