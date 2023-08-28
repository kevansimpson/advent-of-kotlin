package org.base.advent.k2015

import org.base.advent.PuzzleReader
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2015/day/25">Day 25</a>
 */
class Day25 : PuzzleReader {
    private val target = Point(2978L, 3083L)

    override fun solve1(): Any = readMachineConsole(target);

    override fun solve2(): Any = 1138

    fun readMachineConsole(pt: Point): Long {
        var index = pt.y - 1L
        for (i in 0 until (pt.x + pt.y - 1L))
            index += i

        var next = 20151125L;
        for (i in 0 until index)
            next = next(next)

        return next
    }

    fun next(start: Long): Long = (start * 252533L) % 33554393L
}