package org.base.advent.k2015

import org.base.advent.PuzzleSolver
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.ORIGIN

/**
 * <a href="https://adventofcode.com/2015/day/3">Day 3</a>
 */
class Day03 : PuzzleSolver<String> {
    override fun solve1(input: String): Any = HashSet<Point>().follow(input, 0, 1)

    override fun solve2(input: String): Any {
        val set = HashSet<Point>()
        set.follow(input, 0, 2)
        return set.follow(input, 1, 2)
    }

    private fun HashSet<Point>.follow(directions: String, start: Int, increment: Int = 1): Int {
        var location = ORIGIN
        add(ORIGIN)
        for (dir in start until directions.length step increment) {
            location = location.move(directions[dir])
            add(location)
        }
        return size
    }
}

