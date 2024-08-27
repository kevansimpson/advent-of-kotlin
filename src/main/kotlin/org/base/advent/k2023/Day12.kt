package org.base.advent.k2023

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2023/day/12">Day 12</a>
 */
class Day12 : PuzzleFunction<List<String>, Pair<Long, Long>> {

    data class Record(val springs: String, val groups: List<Int>) {
        fun unfold(): Record = Record("$springs?$springs?$springs?$springs?$springs",
            mutableListOf<Int>().apply { repeat(5) { this.addAll(groups)} })
    }

    override fun apply(input: List<String>): Pair<Long, Long> {
        val pairs = input.map {
            with (it.split(" ")) {
                this[0] to this[1].split(",").map(String::toInt)
            }
        }

        val map = mutableMapOf<Record, Long>()
        val recs = pairs.map { Record(it.first, it.second) }
        val part1 = recs.sumOf { count(it, map) }
        val part2 = recs.sumOf { count(it.unfold(), map) }
        return part1 to part2
    }

    private fun count(rec: Record, cache: MutableMap<Record, Long>): Long =
        with (rec) {
            when {
                cache.contains(this) -> cache[this]!!
                springs.isEmpty() -> if (groups.isEmpty()) 1 else 0
                groups.isEmpty() -> if (springs.contains("#")) 0 else 1
                groups[0] > springs.length -> 0
                else -> {
                    val rest = springs.substring(1)
                    val answer = when (springs[0]) {
                        '.' -> count(Record(rest, groups), cache)
                        '?' -> count(Record(".$rest", groups), cache) + count(Record("#$rest", groups), cache)
                        else -> {
                            val g = groups[0]
                            return if (springs.substring(0, g).contains("."))
                                0
                            else {
                                val next = springs.substring(g)
                                if (next.isNotEmpty()) {
                                    when (next[0]) {
                                        '#' -> 0
                                        else -> count(Record(next.substring(1), groups.slice(1 until groups.size)), cache)
                                    }
                                }
                                else
                                    count(Record("", groups.slice(1 until groups.size)), cache)
                            }
                        }
                    }
                    cache[this] = answer
                    return answer
                }
            }
        }
}