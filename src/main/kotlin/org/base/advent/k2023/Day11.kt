package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import org.base.advent.util.Permutations.combinations
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.manhattanDistance
import org.base.advent.util.Text.columns

/**
 * <a href="https://adventofcode.com/2023/day/11">Day 11</a>
 */
class Day11 : PuzzleFunction<List<String>, Pair<Long, Long>> {

    override fun apply(input: List<String>): Pair<Long, Long> {
        val cols = columns(input)
        return shortestPath(galaxyImage(input, cols)) to shortestPath(galaxyImage(input, cols, 999999))
    }

    private fun shortestPath(galaxies: List<Point>): Long =
        combinations(galaxies, 2).sumOf { shortestPath(it[0], it[1]) }

    private fun shortestPath(galaxy1: Point, galaxy2: Point): Long = manhattanDistance(galaxy1, galaxy2)

    private fun galaxyImage(input: List<String>, cols: List<String>, growth: Int = 1): List<Point> {
        val xGrowth = Array(cols.size) { 0 }
        var dx = 0
        for (i in cols.indices) {
            if (!cols[i].contains("#"))
                dx += growth
            xGrowth[i] = dx
        }

        val galaxyList = mutableListOf<Point>()
        var dy = 0
        for (y in input.indices) {
            val row = input[y]
            if (row.contains("#")) {
                for (i in row.indices)
                    if (row[i] == '#')
                        galaxyList.add(Point(y + dy, i + xGrowth[i]))
            }
            else
                dy += growth
        }

        return galaxyList
    }
}