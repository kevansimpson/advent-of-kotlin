package org.base.advent.k2021

import org.base.advent.PuzzleReader
import org.base.advent.util.Point
import org.base.advent.util.toward

/**
 * <a href="https://adventofcode.com/2021/day/5">Day 5</a>
 */
class Day05 : PuzzleReader {

    private val input = readLines("2021/input05.txt")

    override fun solve1(): Any = avoidDanger(drawLines(horizVertLines))

    override fun solve2(): Any = avoidDanger(drawLines(ventLines))

    private val ventLines by lazy {
        input.map { REGEX.matchEntire(it) }.map {
            val (x1, y1, x2, y2) = it!!.destructured
            Point(x1.toLong(), y1.toLong()) to Point(x2.toLong(), y2.toLong())
        }
    }

    private val horizVertLines by lazy {
        ventLines.filter {
            it.first.x == it.second.x || it.first.y == it.second.y
        }
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