package org.base.advent.k2021

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.toward
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2021/day/5">Day 5</a>
 */
class Day05 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val ventLines = input.map { REGEX.matchEntire(it) }
            .map {
                val (x1, y1, x2, y2) = it!!.destructured
                Point(x1.toLong(), y1.toLong()) to Point(x2.toLong(), y2.toLong())
            }
        val horizVertLines = ventLines.filter { it.first.x == it.second.x || it.first.y == it.second.y }

        return avoidDanger(drawLines(horizVertLines)) to avoidDanger(drawLines(ventLines))
    }

    private fun drawLines(lines: List<Pair<Point, Point>>): Map<Point, Int> =
        lines.fold(mutableMapOf<Point, Int>()) { map, pair ->
            val xRange = iter(pair.first.x, pair.second.x)
            val yRange = iter(pair.first.y, pair.second.y)
            while (xRange.hasNext() && yRange.hasNext()) {
                val x = xRange.next()
                val y = yRange.next()
                val pt = Point(x, y)
                if (map.containsKey(pt)) map[pt] = map[pt]!! + 1 else map[pt] = 1
            }
            map
        }.toMap()

    private fun avoidDanger(points: Map<Point, Int>): Int = points.count { it.value >= 2 }

    private fun iter(pt1: Long, pt2: Long): Iterator<Long> =
            if (pt1 == pt2) FixedIterator(pt1) else (pt1 toward pt2).iterator()

    companion object {
        private val REGEX = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()
    }
}

data class FixedIterator(val next: Long) : Iterator<Long> {
    override fun hasNext(): Boolean = true

    override fun next(): Long = next
}