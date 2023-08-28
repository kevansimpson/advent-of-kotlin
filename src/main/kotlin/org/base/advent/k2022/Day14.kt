package org.base.advent.k2022

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import org.base.advent.k2022.Day14.Companion.AIR
import org.base.advent.k2022.Day14.Companion.ROCK
import org.base.advent.k2022.Day14.Companion.SAND
import org.base.advent.util.Point
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * <a href="https://adventofcode.com/2022/day/14">Day 14</a>
 */
class Day14(altInput: List<String>? = null) : PuzzleReader, TimeSaver {

    private val input = altInput ?: readLines("2022/input14.txt")
    private val rocks = input.map { scanRocks(it) }
    private val cave1 = buildCave(rocks)
    private val cave2 = cave1.addFloor()

    override fun solve1(): Any = pourSand(cave1)

    override fun solve2(): Any = pourSand(cave2)

    private fun pourSand(cave: Cave): Int {
        var sand = dropSand(cave)
        while (sand != null) {
            cave.drop(sand)
            sand = if (sand == DROP) null else dropSand(cave)
        }
        if (fullSolve)
            cave.display()
        return cave.countSand()
    }

    private fun dropSand(cave: Cave, drop: Point = DROP): Point? {
        val below = listOf(drop.move(-1, 1), drop.move(0, 1), drop.move(1, 1))
        val space = below.joinToString("") { cave.at(it) }
        return when {
            drop.y >= cave.floor -> null
            cave.at(below[1]) == AIR -> dropSand(cave, below[1]) // down
            cave.at(below[0]) == AIR -> dropSand(cave, below[0]) // left
            cave.at(below[2]) == AIR -> dropSand(cave, below[2]) // right
            space.trim().length == 3 -> drop
            else -> null
        }
    }

    companion object {
        const val ROCK = "#"
        const val SAND = "o"
        const val AIR = " "
        private const val SRC = "+"
        val DROP = Point(500, 0)

        fun buildCave(paths: List<List<Point>>): Cave =
            Cave(mutableMapOf<Point, String>()
                .apply {
                    put(DROP, SRC)
                    paths.forEach { path ->
                        for (i in 0 until path.indices.max()) {
                            putAll(rockPath(path[i], path[i + 1]).map { it to ROCK })
                        }
                    }
                })

        private fun rockPath(start: Point, end: Point): List<Point> =
            mutableListOf<Point>()
                .apply {
                    val dx = abs(start.ix - end.ix)
                    val dy = abs(start.iy - end.iy)
                    if (dx == 0) {
                        val minY = min(start.iy, end.iy)
                        val maxY = max(start.iy, end.iy)
                        for (y in minY..maxY)
                            add(Point(start.ix, y))
                    }
                    else if (dy == 0) {
                        val minX = min(start.ix, end.ix)
                        val maxX = max(start.ix, end.ix)
                        for (x in minX..maxX)
                            add(Point(x, start.iy))
                    }
                }

        fun scanRocks(str: String): List<Point> = str.split(" -> ").map { Point.fromString(it) }
    }
}

data class Cave(val rocks: MutableMap<Point, String>) {
    private val leftRock = rocks.keys.minOf { it.x }
    private val rightRock = rocks.keys.maxOf { it.x }
    private val width = (rightRock - leftRock) * 3
    private val left = leftRock - width
    private val right = rightRock + width
    private val top = min(rocks.keys.minOf { it.y }, 0)
    private val bottom = rocks.keys.maxOf { it.y }
    val floor = 2 + bottom

    fun at(point: Point): String = rocks[point] ?: AIR

    fun countSand(): Int = rocks.values.count { it == SAND }

    fun drop(sand: Point) {
        rocks[sand] = SAND
    }

    fun addFloor() = copy(rocks = this.rocks.toMutableMap()
        .apply {
            for (x in left..right)
                this[Point(x, floor)] = ROCK
        })

    fun display() {
        for (y in top..floor) {
            println()
            for (x in left..right)
                print(rocks.getOrDefault(Point(x, y), AIR))
        }
        println()
    }
}
