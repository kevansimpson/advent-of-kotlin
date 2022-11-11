package org.base.advent.k2019

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2019/day/06">Day 06</a>
 */
class Day06 : PuzzleReader {

    private val input = readLines("2019/input06.txt")
//    private val input = listOf("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN")
    private val orbits = mapOrbits(input)
    private val reversed = reverseOrbits(orbits)

    override fun solve1(): Any = orbits.keys.sumOf { countOrbits(orbits, it) }

    override fun solve2(): Any = transferOrbits(orbits = orbits)

    private fun transferOrbits(from: String = "YOU", to: String = "SAN", orbits: Map<String, List<String>>): Int {
        val visited = mutableSetOf(to)
        var planets = mutableSetOf(reversed[to]!!)
        var count = 0

        while (planets.isNotEmpty()) {
            val next = mutableSetOf<String>()
            for (p in planets) {
                next.addAll(orbits.getOrDefault(p, mutableListOf()))
                next.add(reversed.getOrDefault(p, to))
                visited.add(p)
            }
            next.removeAll(visited)
            if (next.contains(from)) break
            else count += 1

            planets = next
        }

        return count
    }

    private fun countOrbits(orbits: Map<String, List<String>>, planet: String): Int =
            with (orbits.getOrDefault(planet, listOf())) {
//                println(this)
                size + sumOf { countOrbits(orbits, it) }
            }

    private fun mapOrbits(planets: List<String>): Map<String, List<String>> =
            planets.fold<String, MutableMap<String, MutableList<String>>>(mutableMapOf()) { map, p ->
                val orbit = p.split(")")
//                println("ORBIT = ${orbit.first()} - ${orbit.last()}")
                map.getOrPut(orbit.first()) { mutableListOf() }.add(orbit.last())
                map
            }.toSortedMap()

    private fun reverseOrbits(orbits: Map<String, List<String>>): Map<String, String> =
            orbits.keys.fold<String, MutableMap<String, String>>(mutableMapOf()) { map, o ->
                map.apply { orbits[o]!!.forEach { map[it] = o } }
            }.toSortedMap()
}