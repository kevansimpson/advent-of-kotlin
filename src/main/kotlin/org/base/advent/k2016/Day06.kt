package org.base.advent.k2016


import org.base.advent.PuzzleReader
import org.base.advent.util.Text
import kotlin.collections.Map.Entry

/**
 * <a href="https://adventofcode.com/2016/day/6">Day 6</a>
 */
class Day06 : PuzzleReader {

    private val input = readLines("2016/input06.txt")

    override fun solve1(): Any = extract(columnCountsDescending)

    override fun solve2(): Any = extract(columnCountsAscending)

    private val columns by lazy { Text.columns(input) }
    private val columnCounts by lazy { Text.columnCounts(columns) }

    private val columnCountsDescending by lazy {
        columnCounts.map { it.entries.sortedByDescending { kv -> kv.value } }
    }

    private val columnCountsAscending by lazy {
        columnCounts.map { it.entries.sortedBy { kv -> kv.value } }
    }

    private fun extract(list: List<List<Entry<String, Int>>>): String =
            list.joinToString("") { it.first().key }
}