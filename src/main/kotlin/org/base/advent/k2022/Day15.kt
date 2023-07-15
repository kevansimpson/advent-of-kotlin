package org.base.advent.k2022

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import org.base.advent.util.Extensions.extractInt
import org.base.advent.util.Extensions.merge
import org.base.advent.util.Extensions.size
import org.base.advent.util.Point
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2022/day/15">Day 15</a>
 */
class Day15(qualifier: String = "", private val testRow: Long = 2000000L) : PuzzleReader, TimeSaver {

    private val input = readLines("2022/input15$qualifier.txt")
    private val sensors = input
        .map { it.extractInt() }
        .map { Sensor(Point(it[0], it[1]), Point(it[2], it[3])) }
    private val beacons = sensors.map { it.beacon }.toSet()

    override fun solve1(): Any = overlap(sensors, testRow)

    override fun solve2(): Any =
        try {
            (fullOrFast(0L, 3349000L)..4000000L).forEach { overlap(sensors, it) }
        }
        catch (answer: Answer) {
            answer.result
        }

    private fun overlap(sensors: Collection<Sensor>, row: Long): Long =
        with (sensors) {
            val ranges = mapNotNull { it.inRange(row) }.sortedBy { it.first }
            val overlap = ranges
                .fold(LongRange.EMPTY) { fullRange, range ->
                    val pair = fullRange.merge(range)
                    pair.second?.let {
                        if (it.first - 2L == pair.first.last) // GAP!
                            throw Answer((it.first - 1L) * 4000000L + row)
                    }
                    pair.first
                }
            val remove = beacons.toSet().count { b -> (b.y == row) && overlap.contains(b.x) }
            overlap.size() - remove
        }
}

data class Sensor(val position: Point, val beacon: Point) {
    private val dist = position.manhattanDistance(beacon)

    fun inRange(row: Long): LongRange? =
        with (abs(row - position.y)) {
            if (this > dist)
                null
            else {
                val diff = abs(this - dist)
                (position.x - diff)..(position.x + diff)
            }
        }
}

class Answer(val result: Long) : RuntimeException()
