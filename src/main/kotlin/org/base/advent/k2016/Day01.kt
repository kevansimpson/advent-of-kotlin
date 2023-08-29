package org.base.advent.k2016

import org.base.advent.PuzzleSolver
import org.base.advent.util.Dir
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2016/day/1">Day 1</a>
 */
class Day01 : PuzzleSolver<List<String>> {

    override fun solve1(input: List<String>): Any = followDirections(input, false)

    override fun solve2(input: List<String>): Any = followDirections(input, true)

    private fun followDirections(directions: List<String>, stopOn2ndVisit: Boolean): Long {
        val path = mutableListOf(Point.ORIGIN)
        var heading = Dir.North
        for (dir in directions) {
            val turn = dir[0].toString()
            val dist = dir.substring(1).toLong()
            heading = heading.turn(turn)
            val leg = (1..dist).map { path.last().move(heading, it) }
            if (stopOn2ndVisit) {
                for (pt in leg) {
                    if (path.contains(pt))
                        return pt.manhattanDistance()
                    else
                        path.add(pt)
                }
            }
            else
                path.addAll(leg)
        }

        return path.last().manhattanDistance()
    }
}