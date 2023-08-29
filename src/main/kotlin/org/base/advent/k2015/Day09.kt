package org.base.advent.k2015

import org.base.advent.PuzzleFunction
import org.base.advent.util.Permutations.permutations

/**
 * <a href="https://adventofcode.com/2015/day/9">Day 9</a>
 */
class Day09 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val santa = animatronicSanta(input)
        return with (santa.distanceMap.values) { min() to max() }
    }

    private fun animatronicSanta(input: List<String>): Santa {
        val locations: MutableSet<String> = mutableSetOf()
        val jumps: MutableMap<Pair<String, String>, Int> = mutableMapOf()
        input.map { REGEX.matchEntire(it) }
                .forEach {
                    val (city1, city2, dist) = it!!.destructured
                    locations.add(city1)
                    locations.add(city2)
                    jumps[Pair(city1, city2)] = dist.toInt()
                    jumps[Pair(city2, city1)] = dist.toInt()
                }
        val distanceMap = mutableMapOf<String, Int>()
        permutations(locations.toList()).forEach { perm ->
            val list = perm.toList()
            distanceMap[list.toString()] = calculateDistance(list, jumps)
        }

        return Santa(locations, jumps, distanceMap)
    }

    private fun calculateDistance(path: List<String>, jumps: Map<Pair<String, String>, Int>): Int =
        (0 until path.size - 1).fold(0) { dist, i -> dist + (jumps[Pair(path[i], path[i + 1])] ?: 0) }

    companion object {
        private val REGEX = "(.+)\\sto\\s(.+)\\s=\\s(\\d+)".toRegex()
    }
}

data class Santa(val locations: Set<String>,
                 val jumps: Map<Pair<String, String>, Int>,
                 val distanceMap: Map<String, Int>)
