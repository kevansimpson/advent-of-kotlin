package org.base.advent.k2015

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2015/day/19">Day 19</a>
 */
class Day19 : PuzzleReader {

    private val input = readLines("2015/input19.txt")

    override fun solve1(): Any = applyAllReplacements().size

    override fun solve2(): Any = shortestPath

    private val replacements by lazy { input.dropLast(1) }
    private val medicine by lazy { input.last() }

    private val shortestPath by lazy {
        medicine.toCharArray().count { Character.isUpperCase(it) } -
                StringUtils.countMatches(medicine, "Rn") -
                StringUtils.countMatches(medicine, "Ar") -
                2 * StringUtils.countMatches(medicine, "Y") - 1
    }

    private fun applyAllReplacements(): Set<String> =
            replacementMap.entries.fold(setOf()) { molecules, entry ->
                molecules + applyReplacement(medicine, entry)
            }

    private fun applyReplacement(chain: String, replacement: Map.Entry<String, List<String>>): Set<String> {
        val unique = mutableSetOf<String>()
        for (repl in replacement.value) {
            var start = 0
            var index: Int?
            while (run {
                        index = chain.indexOf(replacement.key, start)
                        index
                    }!! >= 0) {

                val variation = chain.substring(0, index!!) + repl +
                        chain.substring(index!! + replacement.key.length)
                unique.add(variation)
                start = index!! + replacement.key.length
            }
        }
        return unique
    }

    private val replacementMap by lazy {
        replacements.filter { StringUtils.isNotBlank(it) }
                .map { it.split("\\s".toRegex()) }
                .fold(mutableMapOf<String, List<String>>()) { map, tokens ->
                    map[tokens[0]] = (map[tokens[0]] ?: listOf()) + listOf(tokens[2])
                    map
                }.mapValues { it.value.toList() }
    }
}