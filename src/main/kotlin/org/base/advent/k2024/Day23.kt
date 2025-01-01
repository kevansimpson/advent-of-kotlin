package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Permutations.combinations

/**
 * <a href="https://adventofcode.com/2024/day/23">Day 23</a>
 */
class Day23 : PuzzleFunction<List<String>, Pair<Int, String>> {

    override fun apply(input: List<String>): Pair<Int, String> {
        val linksMap = createLinksMap(input)
        val chief: MutableSet<Set<String>> = mutableSetOf()
        var password = emptySet<String>()

        for (c1 in linksMap.keys) {
            val links = linksMap[c1]!!
            for (pair in combinations(links.connections.toList(), 2)) {
                val c2 = pair[0]
                val c3 = pair[1]
                val c2Links = linksMap[c2]!!
                val c3Links = linksMap[c3]!!
                if (c2Links.connections.containsAll(listOf(c1, c3)) &&
                    c3Links.connections.containsAll(listOf(c1, c2))) {
                    if (c1.startsWith("t") || c2.startsWith("t") || c3.startsWith("t"))
                        chief.add(setOf(c1, c2, c3))

                    val pswd = links.toSet().intersect(c2Links.toSet().intersect(c3Links.toSet()))
                    if (pswd.size > password.size && isPassword(pswd, linksMap))
                        password = pswd
                }
            }
        }

        return chief.size to password.sorted().joinToString(",")
    }

    private fun isPassword(candidate: Set<String>, linksMap: Map<String, Links>): Boolean {
        for (c in candidate)
            if (!linksMap[c]!!.toSet().containsAll(candidate))
                return false
        return true
    }

    private fun createLinksMap(input: List<String>): Map<String, Links> {
        val linksMap = mutableMapOf<String, Links>()
        input.forEach { line ->
            val pair = line.split("-")
            val c1 = linksMap[pair[0]] ?: Links(pair[0])
            c1.connections.add(pair[1])
            linksMap[pair[0]] = c1
            val c2 = linksMap[pair[1]] ?: Links(pair[1])
            c2.connections.add(pair[0])
            linksMap[pair[1]] = c2
        }
        return linksMap
    }

    data class Links(val name: String, val connections: MutableSet<String> = mutableSetOf()) {
        private val all: Set<String> by lazy { connections.toMutableSet().apply { this.add(name) } }
        fun toSet() = all
    }
}