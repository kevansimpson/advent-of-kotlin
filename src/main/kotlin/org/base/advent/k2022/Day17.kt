package org.base.advent.k2022

import org.base.advent.PuzzleSolver
import org.base.advent.TimeSaver
import org.base.advent.util.Point
import java.util.function.Function
import kotlin.math.max

/**
 * <a href="https://adventofcode.com/2022/day/17">Day 17</a>
 */
class Day17 : PuzzleSolver<String>, TimeSaver {
    override fun solve1(input: String): Any = fastOrFull(3124L) { dropRocks(Cavern(input), 2022L) }

    override fun solve2(input: String): Any = fastOrFull(1561176470569L) { dropRocks(Cavern(input), 1000000000000L) }

    private fun dropRocks(cavern: Cavern, rockCount: Long): Long {
        val history = RockHistory(cavern)
        while (cavern.rockIndex < rockCount) {
            val state = cavern.dropRock()
            if (history.containsKey(state))
                return history.expectedHeight(rockCount, cavern.rockIndex - 1L, history[state]!!)
            else
                history[state] = RockPattern(cavern.rockIndex - 1L, cavern.height)
        }

        return cavern.height
    }

    data class Cavern(val winds: String) {
        private val rocks = mutableSetOf<Point>()
        private val len = winds.length
        private var windex = 0
        private var numRocks = -1L
        internal var height = 0L
        internal var rockIndex = 0L

        fun dropRock(): CavernState {
            val rix = rockIndex++ % 5
            val highest = Point(2, 3 + height)
            val rock = RockBuilder.values()[rix.toInt()].apply(highest)
            drop(rock)
            numRocks = rockIndex

            return getStateAt(rix)
        }

        private fun drop(rock: Rock) {
            var falling = rock
            while (true) {
                val wind = nextWind()
                val blown = falling.blown(wind)
                if (blown.leftEdge >= 0 && blown.rightEdge < 7 && rocks.intersect(blown.points).isEmpty())
                    falling = blown

                val drop = falling.drop()
                if (drop.bottom >= 0 && rocks.intersect(drop.points).isEmpty())
                    falling = drop
                else {
                    rocks.addAll(falling.points)
                    height = max(height, falling.top + 1)
                    return
                }
            }
        }

        private fun getStateAt(rockIndex: Long): CavernState {
            val map = rocks.groupBy { it.x }.toMutableMap()
            if (map.size < 7)
                (0L until 7L).forEach { map.computeIfAbsent(it) { x -> listOf(Point(x, -1L)) } }

            val list = map.values.map { it.maxOf { pt -> pt.y } }.map { it - height }
            return CavernState(list, windex % len, rockIndex + 1)
        }

        private fun nextWind(): Char = winds[(windex++ % len)]
    }

    data class CavernState(val top: List<Long>, val windex: Int, val rockShape: Long)

    data class Rock(val points: Set<Point>) {
        private val exes = points.map { it.x }
        private val whys = points.map { it.y }
        val leftEdge = exes.min()
        val rightEdge = exes.max()
        val top = whys.max()
        val bottom = whys.min()

        fun blown(dir: Char): Rock = Rock(points.map { it.move(dir) }.toSet())

        fun drop(): Rock = blown('v')
    }

    enum class RockBuilder : Function<Point, Rock> {
        Horizontal {
            override fun apply(start: Point): Rock = with (start) {
                Rock(setOf(this, move(1, 0), move(2, 0), move(3, 0)))
            }
        },
        Cross {
            override fun apply(start: Point): Rock = with (start) {
                Rock(setOf(move(1, 0), move(1, 2), // bottom + top
                    move(0, 1), move(1, 1), move(2, 1)))
            }
        },
        Reversal {  // "reverse-L"
            override fun apply(start: Point): Rock = with (start) {
                Rock(setOf(this, move(1, 0), move(2, 0), move(2, 1), move(2, 2)))
            }
        },
        Vertical {
            override fun apply(start: Point): Rock = with (start) {
                Rock(setOf(this, move(0, 1), move(0, 2), move( 0, 3)))
            }
        },
        Square {
            override fun apply(start: Point): Rock = with (start) {
                Rock(setOf(this, move(1, 0), move(0, 1), move(1, 1)))
            }
        }
    }

    data class RockHistory(val cache: MutableMap<CavernState, RockPattern>,
                           val cavern: Cavern) : MutableMap<CavernState, RockPattern> by cache {
        constructor(c: Cavern) : this(mutableMapOf(), c)

        fun expectedHeight(rockCount: Long, r: Long, pattern: RockPattern): Long {
            val perLoop = r - pattern.rocksFallen
            val loopHeight = cavern.height - pattern.cavernHeight
            val loopsLeft = (rockCount - r) / perLoop
            val rocksLeft = (rockCount - r) % perLoop
            val heightLeft = cache.values
                .first { it.rocksFallen == (pattern.rocksFallen + rocksLeft - 1) }.cavernHeight - pattern.cavernHeight
            return cavern.height + loopsLeft * loopHeight + heightLeft
        }
    }

    data class RockPattern(val rocksFallen: Long, val cavernHeight: Long)
}