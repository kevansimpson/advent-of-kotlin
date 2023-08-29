package org.base.advent.k2015

import org.base.advent.PuzzleSolver
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2015/day/25">Day 25</a>
 */
class Day25 : PuzzleSolver<Point> {
    override fun solve1(input: Point): Any = readMachineConsole(input)

    override fun solve2(input: Point): Any = 1138

    private fun readMachineConsole(pt: Point): Long {
        var index = pt.y - 1L
        for (i in 0 until (pt.x + pt.y - 1L))
            index += i

        var next = 20151125L
        for (i in 0 until index)
            next = next(next)

        return next
    }

    fun next(start: Long): Long = (start * 252533L) % 33554393L
}