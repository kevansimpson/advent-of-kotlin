package org.base.advent.k2021

import org.base.advent.PuzzleFunction
import org.base.advent.util.Text
import java.util.*

/**
 * <a href="https://adventofcode.com/2021/day/3">Day 3</a>
 */
class Day03 : PuzzleFunction<List<String>, Pair<Long, Long>> {
    override fun apply(input: List<String>): Pair<Long, Long> {
        val columns = Text.columns(input)
        val columnCounts = Text.columnCounts(columns)
        val columnCountsDescending = columnCounts.map { it.entries.sortedByDescending { kv -> kv.value } }
        val columnCountsAscending = columnCounts.map { it.entries.sortedBy { kv -> kv.value } }
        val gamma = extract(columnCountsAscending)    // 101110111100
        val epsilon = extract(columnCountsDescending) // 010001000011
        val oxygenGenerator = lifeSupport(input, '1') { map -> map.toSortedMap(compareByDescending { map[it] }) }
        val co2scrubber = lifeSupport(input, '0') { map -> map.toSortedMap(compareBy { map[it] }) }
        return (epsilon.toLong(2) * gamma.toLong(2)) to
                (oxygenGenerator.toLong(2) * co2scrubber.toLong(2))
    }
    private fun extract(list: List<List<Map.Entry<String, Int>>>): String =
            list.joinToString("") { it.first().key }


    private fun lifeSupport(input: List<String>, bit: Char, fxn: (Map<String, Int>) -> SortedMap<String, Int>): String {
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
