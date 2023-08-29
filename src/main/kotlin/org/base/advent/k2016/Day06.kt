package org.base.advent.k2016


import org.base.advent.PuzzleFunction
import org.base.advent.util.Text
import kotlin.collections.Map.Entry

/**
 * <a href="https://adventofcode.com/2016/day/6">Day 6</a>
 */
class Day06 : PuzzleFunction<List<String>, Pair<String, String>> {
    override fun apply(input: List<String>): Pair<String, String> {
        val columns = Text.columns(input)
        val columnCounts = Text.columnCounts(columns)
        val columnCountsDescending = columnCounts.map { it.entries.sortedByDescending { kv -> kv.value } }
        val columnCountsAscending = columnCounts.map { it.entries.sortedBy { kv -> kv.value } }

        return extract(columnCountsDescending) to extract(columnCountsAscending)
    }
    private fun extract(list: List<List<Entry<String, Int>>>): String =
            list.joinToString("") { it.first().key }
}