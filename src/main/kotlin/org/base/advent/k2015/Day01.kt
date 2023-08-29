package org.base.advent.k2015

import org.base.advent.PuzzleSolver

/**
 * <a href="https://adventofcode.com/2015/day/1">Day 1</a>
 */
class Day01 : PuzzleSolver<String> {
    override fun solve1(input: String): Any =
        input.replace(")", "").length - input.replace("(", "").length

    override fun solve2(input: String): Any {
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