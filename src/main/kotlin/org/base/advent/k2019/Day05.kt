package org.base.advent.k2019

import org.base.advent.PuzzleSolver
import org.base.advent.k2019.intCode.Program.Companion.boostProgram

/**
 * <a href="https://adventofcode.com/2019/day/05">Day 05</a>
 */
class Day05 : PuzzleSolver<List<Long>> {
    override fun solve1(input: List<Long>): Any = waitForDiagnostic(input, 1L)

    override fun solve2(input: List<Long>): Any = waitForDiagnostic(input, 5L)

    private fun waitForDiagnostic(codes: List<Long>, input: Long): Long {
        val out = boostProgram(codes, input)
        return out.first { it != 0L }
    }
}