package org.base.advent.k2021

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2021/day/2">Day 2</a>
 */
class Day02 : PuzzleFunction<List<String>, Pair<Long, Long>> {
    override fun apply(input: List<String>): Pair<Long, Long> {
        val directions = input.map { REGEX.matchEntire(it) }
            .map {
                val (vector, distance) = it!!.destructured
                vector to distance.toLong()
            }
        val submarine = directions.fold(Point.ORIGIN) { pt, dir ->
            when (dir.first) {
                "forward" -> pt.move(dir.second, 0)
                "down" -> pt.move(0, -dir.second)
                "up" -> pt.move(0, dir.second)
                else -> throw IllegalStateException(dir.first)
            }
        }
        val submarineWithManual = directions.fold(Pair(Point.ORIGIN, 0L)) { pair, dir ->
            val (pt, aim) = pair
            when (dir.first) {
                "forward" -> Pair(pt.move(dir.second, abs(aim) * abs(dir.second)), aim)
                "down" -> Pair(pt, aim + dir.second)
                "up" -> Pair(pt, aim - dir.second)
                else -> throw IllegalStateException(dir.first)
            }
        }.first

        return abs(submarine.x * submarine.y) to abs(submarineWithManual.x * submarineWithManual.y)
    }

    companion object {
        private val REGEX = "(\\w+) (\\d+)".toRegex()
    }
}