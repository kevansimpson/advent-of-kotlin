package org.base.advent.k2021

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2021/day/17">Day 17</a>
 */
class Day17 : PuzzleFunction<String, Pair<Int, Int>> {
    override fun apply(input: String): Pair<Int, Int> {
        // upperLeft to lowerRight
        val targetArea = "([-\\d]+)".toRegex().findAll(input)
            .map { d -> d.value.toInt() }.toList().let { Point(it[0], it[3]) to Point(it[1], it[2]) }

        val probes by lazy {
            val (_, lowerRight) = targetArea
            (5..lowerRight.ix).flatMap { x ->
                (lowerRight.iy..abs(lowerRight.iy)).map { y -> fireProbe(targetArea, x to y) }
            }
        }
        return probes.maxOf { it } to probes.count { it > Integer.MIN_VALUE }
    }

    private fun fireProbe(targetArea: Pair<Point, Point>, xy: Pair<Int, Int>): Int {
        val (_, lowerRight) = targetArea
        var (dx, dy) = xy
        var maxY = dy
        var pos = Point.ORIGIN
        while (pos.y >= lowerRight.y) {
            pos = pos.move(dx, dy)
            if (pos.y > maxY)
                maxY = pos.iy

            if (inTarget(targetArea, pos))
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

    private fun inTarget(targetArea: Pair<Point, Point>, point: Point): Boolean =
            with (targetArea.first) {
                point.x >= x && point.y <= y &&
                        with (targetArea.second) { point.x <= x && point.y >= y }
            }

}