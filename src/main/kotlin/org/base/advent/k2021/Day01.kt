package org.base.advent.k2021

import org.base.advent.PuzzleSolver

/**
 * <a href="https://adventofcode.com/2021/day/1">Day 1</a>
 */
class Day01 : PuzzleSolver<List<Int>> {
    override fun solve1(input: List<Int>): Any = (0 until input.size - 1).count { input[it + 1] > input[it] }

    override fun solve2(input: List<Int>): Any = (0 until input.size - 3).count {
        input[it + 1] + input[it + 2] + input[it + 3] > input[it] + input[it + 1] + input[it + 2]
    }
}