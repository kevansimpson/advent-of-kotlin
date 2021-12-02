package org.base.advent.k2015

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import org.base.advent.util.Text.nextHash

/**
 * <a href="https://adventofcode.com/2015/day/4">Day 1</a>
 */
class Day04 : PuzzleReader, TimeSaver {

    private val input = "bgvyzdsv"

    override fun solve1(): Any = solveFor("00000", fullOrFast(1L, 254000L))

    override fun solve2(): Any = solveFor("000000", fullOrFast(1L, 1038000L))

    private fun solveFor(prefix: String, start: Long = 1L): Long = nextHash(input, prefix, start).second
}