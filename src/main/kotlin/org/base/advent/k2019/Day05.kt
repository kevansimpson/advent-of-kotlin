package org.base.advent.k2019

import org.base.advent.PuzzleSolver
import org.base.advent.k2019.intCode.Program.Companion.runProgram

/**
 * <a href="https://adventofcode.com/2019/day/05">Day 05</a>
 */
class Day05 : PuzzleSolver<List<Int>> {
    override fun solve1(input: List<Int>): Any = runProgram(input, 1)

    override fun solve2(input: List<Int>): Any = runProgram(input, 5)
}