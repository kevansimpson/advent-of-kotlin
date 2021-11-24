package org.base.advent.k2015

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver

/**
 * <a href="https://adventofcode.com/2015/day/14">Day 14</a>
 */
class Day14 : PuzzleReader, TimeSaver {

    private val input = readLines("2015/input14.txt")

    override fun solve1(): Any = distanceTraveled

    override fun solve2(): Any = winningPoints()

    private val speedMap by lazy {
        input.map { REGEX.matchEntire(it) }.associate {
            val (name, kmPerSec, goTime, restTime) = it!!.destructured
            Pair(name, Reindeer(kmPerSec.toInt(), goTime.toInt(), restTime.toInt()))
        }
    }

    private val distanceTraveled: Int by lazy {
        buildSnapshot(speedMap, 2503).toSortedMap().lastKey()
    }

    private fun winningPoints(): Int {
        val pointMap = mutableMapOf<String, Int>()
        (1 until 2504).map { identifyWinner(speedMap, it) }.forEach { winners ->
            winners.forEach { w -> pointMap[w] = (pointMap[w] ?: 0) + 1 }
        }

        val max = pointMap.maxByOrNull { it.value }
        debug("winner = ${max?.key}")
        return max?.value ?: -1
    }

    private fun identifyWinner(reindeerMap: Map<String, Reindeer>, seconds: Int): List<String> {
        val sorted = buildSnapshot(reindeerMap, seconds).toSortedMap()
        return sorted[sorted.lastKey()] ?: listOf()
    }

    private fun buildSnapshot(reindeerMap: Map<String, Reindeer>, seconds: Int): Map<Int, List<String>> =
        reindeerMap.keys.fold(mutableMapOf()) { map, name ->
            val dist = calculateDistance(reindeerMap[name], seconds)
            map[dist] = map[dist]?.plus(name) ?: listOf(name)
            map
        }

    private fun calculateDistance(reindeer: Reindeer?, seconds: Int): Int =
            if (reindeer == null) throw IllegalStateException("Missing Reindeer!")
            else
                with (reindeer) {
                    ((seconds / totalTime) * goTime * kmPerSec +
                            ((seconds % totalTime).coerceAtMost(goTime) * kmPerSec))
                }

    companion object {
        private val REGEX = "(.+) can.+ (\\d+) km.+ (\\d+) seconds.* (\\d+) .+".toRegex()
    }
}

data class Reindeer(val kmPerSec: Int, val goTime: Int, val restTime: Int) {
    val totalTime: Int by lazy { goTime + restTime }
}
