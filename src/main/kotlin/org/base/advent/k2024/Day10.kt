package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.safeGet
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletableFuture.supplyAsync
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2024/day/10">Day 10</a>
 */
class Day10(private val pool: ExecutorService) : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val trails = mutableListOf<CompletableFuture<Pair<Int, Int>>>()
        for (r in (input.size - 1) downTo 0)
            for (c in input.indices) {
                if (input[r][c] == '0') {
                    val head = Point(c, input.size - r - 1)
                    trails.add(supplyAsync({ ascendTrail(head, input) }, pool))
                }
            }

        val sums = arrayOf(0, 0)
        for (cf in trails) {
            val t = cf.safeGet()
            sums[0] += t.first
            sums[1] += t.second
        }

        return sums[0] to sums[1]
    }

    private fun ascendTrail(head: Point, allTrails: List<String>, size: Int = allTrails.size): Pair<Int, Int> {
        var height = 0
        var steps = mutableListOf(head)
        while (steps.isNotEmpty() && height < 9) {
            val h = (++height).digitToChar()
            val next = mutableListOf<Point>()
            for (step in steps) {
                step.neighbors()
                    .filter { inGrid(it, size, size) && allTrails[size - it.iy - 1][it.ix] == h }
                    .forEach { next.add(it) }
            }
            steps = next
        }

        return steps.toSet().size to steps.size
    }
}