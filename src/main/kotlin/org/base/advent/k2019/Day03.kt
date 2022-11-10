package org.base.advent.k2019

import org.base.advent.PuzzleReader
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2019/day/03">Day 03</a>
 */
class Day03 : PuzzleReader {

    private val input = readLines("2019/input03.txt").map { it.csv() }
    private val untangled by lazy { untangle(input) }

    override fun solve1(): Any = analyze(Point::manhattanDistance)

    override fun solve2(): Any = analyze(this::stepsAt)

    private fun analyze(fxn: (Point) -> Long): Long =
            untangled.first.path.toSet().intersect(untangled.second.path.toSet()).map(fxn).minOrNull()?.toLong() ?: -1L

    private fun stepsAt(point: Point): Long =
            untangled.toList().sumOf { it.steps.getOrDefault(point, 0) }.toLong()

    private fun untangle(jumbles: List<List<String>>): Pair<WirePath, WirePath> =
            with (jumbles.map { runWires(it) }) { this[0] to this[1] }

    private fun runWires(wire: List<String>): WirePath =
            wire.fold(Triple(0, Point.ORIGIN, WirePath())) { result, w ->
                var (count, pt, wp) = result
                val dir = w[0]
                val dist = w.substring(1).toInt()
                for (x in 1..dist) {
                    pt = pt.move(dir)
                    wp.path.add(pt)
                    count += 1
                    if (!wp.steps.containsKey(pt))
                        wp.steps[pt] = count
                }
                Triple(count, pt, wp)
            }.third
}

data class WirePath(val path: MutableList<Point> = mutableListOf(),
                    val steps: MutableMap<Point, Int> = mutableMapOf())
