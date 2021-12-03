package org.base.advent.k2016

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleReader
import kotlin.collections.Map.Entry

/**
 * <a href="https://adventofcode.com/2016/day/6">Day 6</a>
 */
class Day06 : PuzzleReader {

    private val input = readLines("2016/input06.txt")

    override fun solve1(): Any = extract(columnCountsDescending)

    override fun solve2(): Any = extract(columnCountsAscending)

    private val columns by lazy {
        input.fold(Array(8) { "" }) { list, row ->
            (0..7).forEach { list[it] = list[it] + row[it] }
            list
        }
    }

    private val columnCounts by lazy {
        columns.map { it.toCharArray().map { ch -> ch.toString() to StringUtils.countMatches(it, ch) }
                .distinct().toMap() }
    }

    private val columnCountsDescending by lazy {
        columnCounts.map { it.entries.sortedByDescending { kv -> kv.value } }
    }

    private val columnCountsAscending by lazy {
        columnCounts.map { it.entries.sortedBy { kv -> kv.value } }
    }

    private fun extract(list: List<List<Entry<String, Int>>>): String =
            list.joinToString("") { it.first().key }
}