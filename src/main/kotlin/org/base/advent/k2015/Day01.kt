package org.base.advent.k2015

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2015/day/1">Day 1</a>
 */
class Day01 : PuzzleReader {

    private val input = readSingleLine("2015/input01.txt")

    override fun solve1(): Any =
        input.replace(")", "").length - input.replace("(", "").length

    override fun solve2(): Any {
        var floor = 0
        var position = 0
        run santa@ {
            input.toCharArray().forEach { ch ->
                position += 1
                when (ch) {
                    ')' -> floor -= 1
                    '(' -> floor += 1
                }
                if (floor < 0) return@santa
            }
        }
        return position
    }
}