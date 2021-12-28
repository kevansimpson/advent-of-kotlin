package org.base.advent.k2021

import org.base.advent.PuzzleReader
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2021/day/9">Day 9</a>
 */
class Day09 : PuzzleReader {

    private val input = readLines("2021/input09.txt")

    override fun solve1(): Any = lowPoints.sumOf { it.second + 1 }

    override fun solve2(): Any = scanBasins()

    private val caveHeight by lazy { input.size }
    private val caveWidth by lazy { input[0].length }

    private val lavaTubes by lazy { input.map { it.toCharArray().map { h -> h.digitToInt() } } }

    private val lowPoints by lazy {
        lavaTubes.flatMapIndexed { col, tube ->
            tube.mapIndexed { row, height -> Point(row, col) to height }
                    .filter { pt -> pt.first.neighbors().none { n -> inGrid(n) && getHeight(n) <= pt.second } } }
    }

    private fun scanBasins(): Int =
            lowPoints.map { it.first }.map { lowPoint -> mapBasin(lowPoint)}
                    .sortedByDescending { it.size }.subList(0, 3).fold(1) { prod, b -> prod * b.size }

    private fun mapBasin(lowPoint: Point): Set<Point> {
        val set = mutableSetOf(lowPoint)
        run santa@ {
            while (true) {
                val next = set.flatMap {
                    it.neighbors().filter { n -> inGrid(n) && getHeight(n) < 9 && !set.contains(n) } }
                if (next.isEmpty())
                    return@santa
                else
                    set.addAll(next)
            }
        }
        return set.toSet()
    }

    private fun getHeight(point: Point): Int = lavaTubes[point.y.toInt()][point.x.toInt()]

    private fun inGrid(point: Point): Boolean = Point.inGrid(point, caveWidth, caveHeight)
}