package org.base.advent.k2021

import org.base.advent.PuzzleReader
import org.base.advent.util.Text
import java.util.*

/**
 * <a href="https://adventofcode.com/2021/day/3">Day 3</a>
 */
class Day03 : PuzzleReader {

    private val input = readLines("2021/input03.txt")

    override fun solve1(): Any = epsilon.toLong(2) * gamma.toLong(2)

    override fun solve2(): Any = oxygenGenerator.toLong(2) * co2scrubber.toLong(2)

    private val columns by lazy { Text.columns(input) }
    private val columnCounts by lazy { Text.columnCounts(columns) }

    private val columnCountsDescending by lazy {
        columnCounts.map { it.entries.sortedByDescending { kv -> kv.value } }
    }

    private val columnCountsAscending by lazy {
        columnCounts.map { it.entries.sortedBy { kv -> kv.value } }
    }

    private val gamma by lazy { extract(columnCountsAscending) }    // 101110111100

    private val epsilon by lazy { extract(columnCountsDescending) } // 010001000011

    private fun extract(list: List<List<Map.Entry<String, Int>>>): String =
            list.joinToString("") { it.first().key }

    private val oxygenGenerator by lazy { lifeSupport('1') { map ->
        map.toSortedMap(compareByDescending { map[it] }) } }

    private val co2scrubber by lazy { lifeSupport('0') { map ->
        map.toSortedMap(compareBy { map[it] }) } }

    private fun lifeSupport(bit: Char, fxn: (Map<String, Int>) -> SortedMap<String, Int>): String {
        var list = input
        run santa@ {
            input[0].indices.forEach { index ->
                val cols = Text.columns(list)
                val colCounts = Text.columnCounts(cols)[index]
                val sorted = fxn.invoke(colCounts)
                val zero = colCounts.getOrDefault("0", 0)
                val ones = colCounts.getOrDefault("1", 0)
                val compare = if (zero == ones) bit else sorted.firstKey()[0]
                list = list.filter { it[index] == compare }
                if (list.size == 1) return@santa
            }
        }

        return list[0]
    }
}
