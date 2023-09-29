package org.base.advent.k2019

import org.base.advent.PuzzleSolver
import org.base.advent.k2019.intCode.Program.Companion.boostProgram

/**
 * <a href="https://adventofcode.com/2019/day/9">Day 9</a>
 */
class Day09 : PuzzleSolver<List<Long>> {
    override fun solve1(input: List<Long>): Any = boostProgram(input, 1L).peek()

    override fun solve2(input: List<Long>): Any = boostProgram(input, 2L).peek()
}
