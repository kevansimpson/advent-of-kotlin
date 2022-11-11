package org.base.advent.k2019

import org.base.advent.PuzzleReader
import org.base.advent.k2019.intCode.Program.Companion.runProgram

/**
 * <a href="https://adventofcode.com/2019/day/05">Day 05</a>
 */
class Day05 : PuzzleReader {

    private val input = readSingleLine("2019/input05.txt").csvToInt()

    override fun solve1(): Any = runProgram(input, 1)

    override fun solve2(): Any = runProgram(input, 5)
}