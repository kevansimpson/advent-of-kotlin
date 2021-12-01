package org.base.advent.k2021

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2021/day/1">Day 1</a>
 */
class Day01 : PuzzleReader {

    private val input = readLines("2021/input01.txt").map { it.toInt() }

    override fun solve1(): Any = (0 until input.size - 1).count { input[it + 1] > input[it] }

    override fun solve2(): Any = (0 until input.size - 3).count {
        input[it + 1] + input[it + 2] + input[it + 3] > input[it] + input[it + 1] + input[it + 2]
    }
}