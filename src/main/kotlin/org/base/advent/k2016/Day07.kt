package org.base.advent.k2016

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2016/day/7">Day 7</a>
 */
class Day07 : PuzzleReader {

    private val input = readLines("2016/input07.txt")

    override fun solve1(): Any = ipv7List.count { supportsTLS(it) }

    override fun solve2(): Any = ipv7List.count { supportsSSL(it) }

    private val ipv7List by lazy {
        input.map { it.split("]").flatMap { s -> s.split("[") } }
    }

    private fun supportsTLS(ipv7: List<String>): Boolean =
            (ipv7.filterIndexed { index, _ -> (index % 2) == 0 }.count { abba(it) }) > 0 &&
                    (ipv7.filterIndexed { index, _ -> (index % 2) > 0 }.count { abba(it) }) == 0

    private fun supportsSSL(ipv7: List<String>): Boolean {
        val abaList = ipv7.filterIndexed { index, _ -> (index % 2) == 0 }.map {
            val list = mutableListOf<Pair<String, String>>()
            var m = ABA_REGEX.find(it, 0)
            while (m != null) {
                val (a, b) = m.destructured
                if (a != b) list.add(a to b)
                m = ABA_REGEX.find(it, m.range.first + 1)
            }
            list
        }.filter { it.isNotEmpty() }.flatten()

        if (abaList.isNotEmpty()) {
            val babList = abaList.map { "${it.second}${it.first}${it.second}" }
            return (ipv7.filterIndexed { index, _ -> (index % 2) != 0 }
                    .filter { babList.any { bab -> it.contains(bab) } }).isNotEmpty()
        }
        return false
    }

    private fun abba(text: String): Boolean =
            when (val m = ABBA_REGEX.matchEntire(text)) {
                null -> false
                else -> {
                    val (a, b) = m.destructured
                    a != b
                }
            }

    companion object {
        private val ABA_REGEX = "(\\w)(\\w)\\1".toRegex()
        private val ABBA_REGEX = ".*(\\w)(\\w)\\2\\1.*".toRegex()
    }
}