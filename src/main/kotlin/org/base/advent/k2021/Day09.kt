package org.base.advent.k2021

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2021/day/9">Day 9</a>
 */
class Day09 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> =
        with (LavaTubes(input)) {
            lowPoints.sumOf { it.second + 1 } to scanBasins()
        }

    data class LavaTubes(val seismicData: List<String>) {
        private val caveHeight by lazy { seismicData.size }
        private val caveWidth by lazy { seismicData[0].length }
        private val lavaTubes by lazy { seismicData.map { it.toCharArray().map { h -> h.digitToInt() } } }
        val lowPoints by lazy {
            lavaTubes.flatMapIndexed { col, tube ->
                tube.mapIndexed { row, height -> Point(row, col) to height }
                    .filter { pt -> pt.first.neighbors().none { n -> inGrid(n) && getHeight(lavaTubes, n) <= pt.second } } }
        }

        internal fun scanBasins(): Int =
            lowPoints.map { it.first }.map { lowPoint -> mapBasin(lowPoint)}
                .sortedByDescending { it.size }.subList(0, 3).fold(1) { prod, b -> prod * b.size }

        private fun mapBasin(lowPoint: Point): Set<Point> {
            val set = mutableSetOf(lowPoint)
            run santa@ {
                while (true) {
                    val next = set.flatMap {
                        it.neighbors().filter { n -> inGrid(n) && getHeight(lavaTubes, n) < 9 && !set.contains(n) } }
                    if (next.isEmpty())
                        return@santa
                    else
                        set.addAll(next)
                }
            }
            return set.toSet()
        }

        private fun getHeight(lavaTubes: List<List<Int>>,
                              point: Point): Int = lavaTubes[point.y.toInt()][point.x.toInt()]

        private fun inGrid(point: Point): Boolean = Point.inGrid(point, caveWidth, caveHeight)
    }
}