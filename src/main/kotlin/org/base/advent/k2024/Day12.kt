package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid

/**
 * <a href="https://adventofcode.com/2024/day/12">Day 12</a>
 */
class Day12 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    data class Region(val plant: Char, val plot: MutableSet<Point>)

    override fun apply(input: List<String>): Pair<Int, Int> {
        val size = input.size
        val visited = Array(input.size) { BooleanArray(input.size) }
        val costs = arrayOf(0, 0)
        for (r in (size - 1) downTo 0)
            for (c in input.indices)
                if (!visited[c][size - r - 1]) {
                    val region = mapRegion(input[r][c], Point(c, input.size - r - 1), visited, input, size)
                    costs[0] += regionCost(region, size)
                    costs[1] += sidesCost(region, size)
                    region.plot.forEach { visited[it.ix][it.iy] = true }
                }

        return costs[0] to costs[1]
    }

    private fun regionCost(region: Region, size: Int): Int =
        region.plot.fold(0) { perimeter, pt ->
            perimeter + pt.neighbors().count { !inGrid(it, size, size) || !region.plot.contains(it) }
        } * region.plot.size

    private fun sidesCost(region: Region, size: Int): Int {
        var sides = 0
        var vertical = mutableSetOf<Int>()
        for (i in 0 until size) {
            val vert1 = mutableSetOf<Int>()
            region.plot.forEach {
                if (it.ix == i) {
                    vert1.add(-(it.iy + 1))
                    vert1.add(it.iy + 2)
                }
            }

            val vert2 = mutableSetOf<Int>()
            vert1.filter { !vert1.contains(-it) }.forEach(vert2::add)

            sides += vert2.count { !vertical.contains(it) }
            vertical = vert2
        }

        var horizontal = mutableSetOf<Int>()
        for (i in 0 until size) {
            val horiz1 = mutableSetOf<Int>()
            region.plot.forEach {
                if (it.ix == i) {
                    horiz1.add(-(it.iy + 1))
                    horiz1.add(it.iy + 2)
                }
            }

            val horiz2 = mutableSetOf<Int>()
            horiz1.filter { !horiz1.contains(-it) }.forEach(horiz2::add)

            sides += horiz2.count { !horizontal.contains(it) }
            horizontal = horiz2
        }

        return sides * region.plot.size
    }

    private fun mapRegion(plant: Char, start: Point, visited: Array<BooleanArray>,
                          input: List<String>, size: Int): Region {
        val region = Region(plant, mutableSetOf())
        var plot = mutableListOf(start)
        while (plot.isNotEmpty()) {
            region.plot.addAll(plot)
            plot.forEach { visited[it.ix][it.iy] = true }
            val next = mutableListOf<Point>()
            plot.forEach {
                it.neighbors().forEach { n ->
                    if (inGrid(n, size, size) && plant == input[size - n.iy - 1][n.ix] &&
                        !visited[n.ix][n.iy] && !next.contains(n))
                        next.add(n)
                }
            }
            plot = next
        }

        return region
    }
}