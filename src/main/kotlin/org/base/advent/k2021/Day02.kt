package org.base.advent.k2021

import org.base.advent.PuzzleReader
import org.base.advent.util.Point
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2021/day/2">Day 2</a>
 */
class Day02 : PuzzleReader {

    private val input = readLines("2021/input02.txt")

    override fun solve1(): Any = abs(submarine.x * submarine.y)

    override fun solve2(): Any = abs(submarineWithManual.x * submarineWithManual.y)

    private val directions by lazy {
        input.map { REGEX.matchEntire(it) }
                .map {
                    val (vector, distance) = it!!.destructured
                    vector to distance.toLong()
                }
    }

    private val submarine by lazy {
        directions.fold(Point.ORIGIN) { pt, dir ->
            when (dir.first) {
                "forward" -> pt.move(dir.second, 0)
                "down" -> pt.move(0, -dir.second)
                "up" -> pt.move(0, dir.second)
                else -> throw IllegalStateException(dir.first)
            }
        }
    }

    private val submarineWithManual by lazy {
        directions.fold(Pair(Point.ORIGIN, 0L)) { pair, dir ->
            val (pt, aim) = pair
            when (dir.first) {
                "forward" -> Pair(pt.move(dir.second, abs(aim) * abs(dir.second)), aim)
                "down" -> Pair(pt, aim + dir.second)
                "up" -> Pair(pt, aim - dir.second)
                else -> throw IllegalStateException(dir.first)
            }
        }.first
    }

    companion object {
        private val REGEX = "(\\w+) (\\d+)".toRegex()
    }
}