package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import java.util.*
import kotlin.math.pow

/**
 * <a href="https://adventofcode.com/2023/day/4">Day 04</a>
 */
class Day04 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val cards = readCards(input)
        var sum = 0
        val count = Array(cards.size) { 1 }
        cards.forEachIndexed { index, pair ->
            val winners = pair.second.count { pair.first.contains(it) }
            if (winners > 0) {
                sum += 2.0.pow(winners - 1.0).toInt()
                for (i in (index+1)..(index+winners))
                    count[i] += count[index]
            }
        }
        return sum to count.sum()
    }

    private fun readCards(input: List<String>): List<Pair<TreeSet<Int>, List<Int>>> =
        input.map {
            val start = it.indexOf(": ") + 2
            val mid = it.indexOf(" | ", start)
            toSet(it.substring(start, mid)) to toList(it.substring(mid + 3).trim())
        }

    private fun toList(str: String): List<Int> = mutableListOf<Int>().apply { splitToInt(str, this) }
    private fun toSet(str: String): TreeSet<Int> = TreeSet<Int>().apply { splitToInt(str, this) }

    private fun <T : MutableCollection<Int>> splitToInt(str: String, collection: T): T =
        collection.apply {
            str.trim().split("\\s+".toRegex()).forEach { num -> collection.add(num.toInt()) }
        }
}