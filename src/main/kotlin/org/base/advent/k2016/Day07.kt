package org.base.advent.k2016

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2016/day/7">Day 7</a>
 */
class Day07 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val ipv7List = input.map { it.split("[\\[\\]]".toRegex()) }
        return ipv7List.count { supportsTLS(it) } to ipv7List.count { supportsSSL(it) }
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
            val babSet = abaList.map { "${it.second}${it.first}${it.second}" }.toSet()
            return (ipv7.filterIndexed { index, _ -> (index % 2) != 0 }
                    .filter { babSet.any { bab -> it.contains(bab) } }).isNotEmpty()
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