package org.base.advent.k2016

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2016/day/9">Day 9</a>
 */
class Day09 : PuzzleReader {

    private val input = readSingleLine("2016/input09.txt")

    override fun solve1(): Any = decompress(input)

    override fun solve2(): Any = decompress2(input)

    private fun decompress(data: String): Long {
        val map = mutableListOf<Int>()
        var start = 0
        var index = data.indexOf("(")
        while (index >= 0) {
            map.add(index - start)
            val close = data.indexOf(")", index)
            val marker = data.substring(index + 1, close).split("x").map { it.toInt() }
            map.add(marker[0] * marker[1]) // don't care what the repeated string is
            start = close + marker[0] + 1
            index = data.indexOf("(", start)
        }
        map.add(data.substring(start).length)

        return map.sumOf { it.toLong() }
    }

    private fun decompress2(data: String): Long {
        val map = mutableListOf<Long>()
        var start = 0
        var index = data.indexOf("(")
        while (index >= 0) {
            map.add((index - start).toLong())
            val close = data.indexOf(")", index)
            val marker = data.substring(index + 1, close).split("x").map { it.toLong() }
            start = close + 1
            val repeated = data.substring(start, start + marker[0].toInt())
            if (repeated.contains("("))
                map.add(marker[1] * decompress2(repeated))
            else
                map.add(marker[1] * marker[0])
            start += marker[0].toInt()
            index = data.indexOf("(", start)
        }
        map.add(data.substring(start).length.toLong())

        return map.sumOf { it }
    }
}