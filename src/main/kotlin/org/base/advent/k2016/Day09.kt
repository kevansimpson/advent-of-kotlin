package org.base.advent.k2016

import org.base.advent.PuzzleSolver

/**
 * <a href="https://adventofcode.com/2016/day/9">Day 9</a>
 */
class Day09 : PuzzleSolver<String> {

    override fun solve1(input: String): Any = decompress(input)

    override fun solve2(input: String): Any = decompress2(input)

    private fun decompress(data: String): Long {
        var length = 0L
        var start = 0
        var index = data.indexOf("(")
        while (index >= 0) {
            length += index - start
            val close = data.indexOf(")", index)
            val marker = data.substring(index + 1, close).split("x").map { it.toInt() }
            length += marker[0] * marker[1]
            start = close + marker[0] + 1
            index = data.indexOf("(", start)
        }

        return length + data.substring(start).length
    }

    private fun decompress2(data: String): Long {
        var length = 0L
        var start = 0
        var index = data.indexOf("(")
        while (index >= 0) {
            length += (index - start).toLong()
            val close = data.indexOf(")", index)
            val marker = data.substring(index + 1, close).split("x").map { it.toLong() }
            start = close + 1
            val repeated = data.substring(start, start + marker[0].toInt())
            length += if (repeated.contains("("))
                marker[1] * decompress2(repeated)
            else
                marker[1] * marker[0]

            start += marker[0].toInt()
            index = data.indexOf("(", start)
        }

        return length + data.substring(start).length.toLong()
    }
}