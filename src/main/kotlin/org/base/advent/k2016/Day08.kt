package org.base.advent.k2016

import org.base.advent.PuzzleReader
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2016/day/8">Day 8</a>
 */
class Day08 : PuzzleReader {

    private val input = readLines("2016/input08.txt")
//    private val input = listOf(
//        "rect 3x2",
//        "rotate column x=1 by 1",
//        "rotate row y=0 by 4",
//        "rotate column x=1 by 1"
//    )

    override fun solve1(): Any = grid.size

    override fun solve2(): Any {
        display(grid)
        return "UPOJFLBCEZ"
    }

    private val grid by lazy {
        input.fold(mutableSetOf<Point>()) { set, instruction ->
            when {
                instruction.startsWith("rect") -> rect(instruction, set)
                instruction.contains("row") -> row(instruction, set)
                instruction.contains("column") -> column(instruction, set)
            }
//            display(set)
            set
        }
    }

    private fun rect(line: String, grid: MutableSet<Point>) {
        val axb = line.split(" ")[1].split("x").map { it.toInt() }
        for (y in 0 until axb[1]) {
            for (x in 0 until axb[0]) {
                grid.add(Point(x, y))
            }
        }
    }

    private fun column(line: String, grid: MutableSet<Point>) {
        val xby = rotation(line, "x=")
        val col = grid.filter { it.x == xby.first }
        col.forEach { grid.remove(it) }
        col.forEach { grid.add(it.copy(y = ((xby.second + it.y) % HEIGHT))) }
    }

    private fun row(line: String, grid: MutableSet<Point>) {
        val yby = rotation(line, "y=")
        val row = grid.filter { it.y == yby.first }
        row.forEach { grid.remove(it) }
        row.forEach { grid.add(it.copy(x = ((yby.second + it.x) % WIDTH))) }
    }

    private fun rotation(line: String, prefix: String): Pair<Long, Long> {
        val raw = line.substringAfter(prefix).split(" ")
        return raw[0].toLong() to raw[2].toLong()
    }

    private fun display(grid: MutableSet<Point>) {
        println()
        for (y in 0 until HEIGHT) {
            println()
            for (x in 0 until WIDTH) {
                print(if (grid.contains(Point(x, y))) "#" else ".")
            }
        }
        println("\n---")

    }
    companion object {
        private const val WIDTH = 50
        private const val HEIGHT = 6
//        private const val WIDTH = 7
//        private const val HEIGHT = 3
    }
}