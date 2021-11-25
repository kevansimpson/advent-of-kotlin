package org.base.advent.k2015

import org.apache.commons.lang3.ObjectUtils
import org.base.advent.PuzzleReader
import java.util.*

/**
 * <a href="https://adventofcode.com/2015/day/16">Day 16</a>
 */
class Day16 : PuzzleReader {

    private val input = readLines("2015/input16.txt")

    override fun solve1(): Any = auntSueIndex

    override fun solve2(): Any = outdatedRetroencabulator

    private val tickerTape = mapOf("children" to 3, "cats" to 7, "samoyeds" to 2, "pomeranians" to 3, "akitas" to 0,
            "vizslas" to 0, "goldfish" to 5, "trees" to 3, "cars" to 2, "perfumes" to 1)

    private val auntSueList by lazy { input.map { parseSue(it) } }

    private val auntSueIndex by lazy {
        auntSueList.first { hasSameAttr(tickerTape, it) }.getOrDefault(INDEX, -1)
    }

    private val outdatedRetroencabulator by lazy {
        auntSueList.first { hasSameRanges(tickerTape, it) }.getOrDefault(INDEX, -1)
    }

    private fun hasSameRanges(attr1: Sue, attr2: Sue): Boolean =
            satisfiesTicker(attr1, attr2) && reverseTicker(attr1, attr2)

    private fun satisfiesTicker(tickerTape: Sue, attr: Sue): Boolean {
        run santa@{
            for (key in tickerTape.keys) {
                when (key) {
                    INDEX -> return@santa
                    "trees","cats" -> if (gte0(key, tickerTape, attr)) return false
                    "pomeranians","goldfish" -> if (lte0(key, tickerTape, attr)) return false
                    else ->
                        if (attr.containsKey(key) && !Objects.equals(tickerTape[key], attr[key])) return false
                }
            }
        }
        return true
    }

    private fun gte0(key: String, tickerTape: Sue, attr: Sue): Boolean =
            attr.containsKey(key) && ObjectUtils.compare(tickerTape[key] as Int, attr[key]) >= 0

    private fun lte0(key: String, tickerTape: Sue, attr: Sue): Boolean =
            attr.containsKey(key) && ObjectUtils.compare(tickerTape[key] as Int, attr[key]) <= 0

    private fun reverseTicker(tickerTape: Sue, attr: Sue): Boolean {
        run santa@{
            for (key in attr.keys) {
                when (key) {
                    INDEX,"trees","cats","pomeranians","goldfish" -> return@santa
                    else ->
                        if (tickerTape.containsKey(key) && !Objects.equals(attr[key], tickerTape[key]))
                            return false
                }
            }
        }
        return true
    }

    private fun hasSameAttr(sue1: Sue, sue2: Sue): Boolean =
            hasSameValues(sue1, sue2) && hasSameValues(sue2, sue1)

    private fun hasSameValues(sue1: Sue, sue2: Sue): Boolean {
        run santa@ {
            for (key in sue1.keys) {
                if (INDEX == key) return@santa
                if (sue2.containsKey(key) && !Objects.equals(sue1[key], sue2[key]))
                    return false
            }
        }
        return true
    }

    private fun parseSue(sue: String): Sue {
        val (index, a1, c1, a2, c2, a3, c3) = REGEX.matchEntire(sue)!!.destructured
        return mapOf(INDEX to index.toInt(), a1 to c1.toInt(), a2 to c2.toInt(), a3 to c3.toInt())
    }

    companion object {
        private const val INDEX = "INDEX"
        private val REGEX = "\\w+ (\\d+): (\\w+): (\\d+), (\\w+): (\\d+), (\\w+): (\\d+)".toRegex()
    }
}

typealias Sue = Map<String, Int>
