package org.base.advent.k2015

import org.base.advent.PuzzleReader
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2015/day/6">Day 6</a>
 */
class Day06 : PuzzleReader {

    private val input = readLines("2015/input06.txt")

    override fun solve1(): Any = flipLights(lightGrid, lightsOn)

    override fun solve2(): Any = flipLights(lightGrid, totalBrightness)

    private fun flipLights(list: List<LightCmd>,
                           cmdMap: Map<String, (Int) -> Int>): Int {
        val grid: Array<IntArray> = Array(1000) { IntArray(1000) }
        list.forEach { cmd ->
            for (x in cmd.start.x..cmd.end.x)
                for (y in cmd.start.y..cmd.end.y) {
                    grid[y.toInt()][x.toInt()] = cmdMap[cmd.cmd]!!.invoke(grid[y.toInt()][x.toInt()])
                }
        }
        return grid.sumOf { it.sum() }
    }

    private val lightsOn = mapOf(
            Pair("toggle") { value: Int -> if (value == 0) 1 else 0 },
            Pair("turn on") { 1 },
            Pair("turn off") { 0 })

    private val totalBrightness = mapOf(
            Pair("toggle") { value: Int -> value + 2 },
            Pair("turn on") { value: Int -> value + 1 },
            Pair("turn off") { value: Int -> (value - 1).coerceAtLeast(0) })

    private val lightGrid by lazy {
        input.map { REGEX.matchEntire(it) }
                .map {
                    LightCmd(it?.groups?.get(1)?.value ?: "",
                            toPoint(it?.groups?.get(2)?.value ?: "-1,-1"),
                            toPoint(it?.groups?.get(3)?.value ?: "-1,-1"))
                }
    }

    private fun toPoint(str: String): Point {
        val pair = str.split(",")
        return Point(pair[0].toLong(), pair[1].toLong())
    }

    companion object {
        private val REGEX = "(toggle|turn on|turn off) ([\\d,]+) through ([\\d,]+)".toRegex()
    }
}

data class LightCmd(val cmd: String, val start: Point, val end: Point)
