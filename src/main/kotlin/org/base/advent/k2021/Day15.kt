package org.base.advent.k2021

import org.base.advent.PuzzleSolver
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid
import java.util.*

/**
 * <a href="https://adventofcode.com/2021/day/15">Day 15</a>
 *
 * h/t <a href="https://github.com/PhenixFine/advent-of-code-kotlin-2021/blob/main/src/Day15.kt"/>
 */
class Day15 : PuzzleSolver<List<String>> {
    override fun solve1(input: List<String>): Any {
        val sampleCave = input
            .mapIndexed { x, line -> line.map { it.digitToInt() }.mapIndexed { y, num -> Risk(Point(x, y), num) } }
        return safestPath(sampleCave)
    }

    override fun solve2(input: List<String>): Any {
        val fullCave by lazy {
            val list = input.map { expand(it) }.toMutableList()
            val size = list.size
            repeat(4) {
                val next = if (list.size > size) list.drop(list.size - size) else list.toList()
                next.forEach { list.add(it.map { num -> transform(num) }) }
            }
            list.mapIndexed { x, line -> line.mapIndexed { y, num -> Risk(Point(x, y), num) } }
        }
        return safestPath(fullCave)
    }

    private fun safestPath(cave: List<List<Risk>>): Int {
        val start = cave.first().first().also { it.distance = 0 }
        val target = cave.last().last()
        val width = target.point.ix + 1
        val height = target.point.iy + 1
        val paths = PriorityQueue<Risk>(compareBy { it.distance }).also { it.add(start) }

        while (paths.isNotEmpty() && !target.processed) {
            paths.remove().let { risk ->
                if (!risk.processed) {
                    val neighbors = risk.point.neighbors().filter { inGrid(it, width, height) }
                            .map { n -> cave[n.ix][n.iy] }
                    val min = neighbors.minOf { it.distance } + risk.weight
                    if (risk.distance > min) risk.distance = min

                    neighbors.filterNot { it.processed }.let { un ->
                        un.forEach { n ->
                            val weight = risk.distance + n.weight
                            if (n.distance > weight) n.distance = weight
                        }
                        paths.addAll(un)
                    }
                    risk.processed = true
                }
            }
        }
        return target.distance
    }

//    private fun sumPath(path: Set<Point>): Long = path.sumOf { grid[it.ix][it.iy].toLong() }

    private fun expand(line: String): List<Int> {
        val list = mutableListOf(line.map { it.digitToInt() })
        repeat(4) { list.add(list.last().map { transform(it) }) }
        return list.flatten()
    }

    private fun transform(num: Int) = (num + 1).let { if (it == 10) 1 else it }
}

data class Risk(val point: Point, val weight: Int,
                var distance: Int = Int.MAX_VALUE - 10, var processed: Boolean = false)
