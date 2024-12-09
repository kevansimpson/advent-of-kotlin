package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Permutations.combinations
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid

/**
 * <a href="https://adventofcode.com/2024/day/8">Day 8</a>
 */
class Day08 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val size = input.size
        val scan = scanAntennas(input)
        val nodes = mutableSetOf<Point>()
        val harmonics = mutableSetOf<Point>()
        for (antennas in scan.values) {
            val pairs = combinations(antennas, 2)
            for (pair in pairs) {
                val a = pair[0]
                val b = pair[1]
                harmonics.add(a)
                harmonics.add(b)
                val dx1 = a.x - b.x
                val dy1 = a.y - b.y
                val dx2 = b.x - a.x
                val dy2 = b.y - a.y
                var next1 = Point(a.x + dx1, a.y + dy1)
                var next2 = Point(b.x + dx2, b.y + dy2)
                nodes.add(next1)
                nodes.add(next2)
                while (inGrid(next1, size, size)) {
                    harmonics.add(next1)
                    next1 = next1.move(dx1, dy1)
                }
                while (inGrid(next2, size, size)) {
                    harmonics.add(next2)
                    next2 = next2.move(dx2, dy2)
                }
            }
        }

        return nodes.count { inGrid(it, size, size) } to harmonics.size
    }

    private fun scanAntennas(input: List<String>): Map<Char, List<Point>> {
        val scan =  mutableMapOf<Char, MutableList<Point>>()
        for (r in (input.size - 1) downTo 0) {
            for (c in input.indices) {
                val at = input[r][c]
                if (at != '.') {
                    if (!scan.containsKey(at))
                        scan[at] = mutableListOf()
                    scan[at]!!.add(Point(c, r))
                }
            }
        }
        return scan
    }
}