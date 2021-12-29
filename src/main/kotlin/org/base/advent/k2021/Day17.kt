package org.base.advent.k2021

import org.base.advent.PuzzleReader
import org.base.advent.util.Point
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2021/day/17">Day 17</a>
 */
class Day17 : PuzzleReader {

    private val input = "target area: x=156..202, y=-110..-69"

    override fun solve1(): Any = probes.maxOf { it }

    override fun solve2(): Any = probes.count { it > Integer.MIN_VALUE }

    private val targetArea by lazy { // upperLeft to lowerRight
        "([-\\d]+)".toRegex().findAll(input).map { d -> d.value.toInt() }.toList()
                .let { Point(it[0], it[3]) to Point(it[1], it[2]) }
    }

    private val probes by lazy {
        val (_, lowerRight) = targetArea
        (5..lowerRight.ix).flatMap { x ->
            (lowerRight.iy..abs(lowerRight.iy)).map { y -> fireProbe(x to y) } }
    }

    private fun fireProbe(xy: Pair<Int, Int>): Int {
        val (_, lowerRight) = targetArea
        var (dx, dy) = xy
        var maxY = dy
        var pos = Point.ORIGIN
        while (pos.y >= lowerRight.y) {
            pos = pos.move(dx, dy)
            if (pos.y > maxY)
                maxY = pos.iy

            if (inTarget(pos))
                return maxY

            dx = drag(dx)
            dy -= 1
        }

        return Integer.MIN_VALUE
    }

    private fun drag(x: Int): Int = when {
        x > 0 -> x - 1
        x < 0 -> x + 1
        else -> 0
    }

    private fun inTarget(point: Point): Boolean =
            with (targetArea.first) {
                point.x >= x && point.y <= y &&
                        with (targetArea.second) { point.x <= x && point.y >= y }
            }

}