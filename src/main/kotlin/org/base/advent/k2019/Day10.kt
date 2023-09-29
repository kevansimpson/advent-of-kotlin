package org.base.advent.k2019

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point
import java.lang.Math.toDegrees
import java.math.BigDecimal
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.hypot

/**
 * <a href="https://adventofcode.com/2019/day/10">Day 10</a>
 */
class Day10 : PuzzleFunction<List<String>, Pair<Int, Long>> {
    override fun apply(input: List<String>): Pair<Int, Long> {
        val scan = scanAsteroids(input)
        val asteroids = scan.associateWith { countVisibleAsteroids(it, scan).size }
        val twoHundredth = vaporize(asteroids.toMutableMap())
        return asteroids.values.max() to twoHundredth.x * 100L + twoHundredth.y
    }

    private fun vaporize(asteroids: MutableMap<Point, Int>): Point {
        val deathStar = asteroids.maxBy { it.value }.key
        val vaporized = mutableListOf<Point>()

        while (vaporized.size < 200) {
            val visible = countVisibleAsteroids(deathStar, asteroids.keys).toSortedMap()
            visible.forEach {
                vaporized.add(it.value)
                asteroids.remove(it.value)
            }
        }

        return vaporized[199]
    }

    private fun countVisibleAsteroids(asteroid: Point, space: Set<Point>): Map<Double, Point> =
        mutableMapOf<Double, Point>().apply {
            val graph = space.toMutableList()
            graph.remove(asteroid)
            graph.sortBy { bigDistance(it, asteroid) }

            graph.forEach { point ->
                val angle = angle(asteroid, point)
                if (!containsKey(angle))
                    put(angle, point)
            }
        }

    private fun angle(pt: Point, target: Point): Double {
        // starts pointing up when cartesian graph is upside down
        val dx = (target.x - pt.x).toDouble()
        val dy = (target.y - pt.y).toDouble()
        val angle = toDegrees(atan2(dy, dx)) + 90.0
        return if (angle < 0) angle + 360.0 else angle
    }

    private fun bigDistance(to: Point, from: Point): BigDecimal = BigDecimal.valueOf(
        hypot(abs(from.y - to.y).toDouble(), abs(from.x - to.x).toDouble())
    )

    private fun scanAsteroids(rows: List<String>): Set<Point> =
        mutableSetOf<Point>().apply {
            rows.forEachIndexed { y, row ->
                for (x in row.indices)
                    if (row[x] == '#')
                        add(Point(x, y))
            }
        }
}
