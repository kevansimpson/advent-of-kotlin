package org.base.advent.k2015

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2015/day/6">Day 6</a>
 */
class Day06 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val lightGrid = input.map { REGEX.matchEntire(it) }
            .map {
                val (cmd, pt1, pt2) = it!!.destructured
                LightCmd(cmd, toPoint(pt1), toPoint(pt2))
            }
        return flipLights(lightGrid, lightsOn) to flipLights(lightGrid, totalBrightness)
    }

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

    private fun toPoint(str: String): Point {
        val pair = str.split(",")
        return Point(pair[0].toLong(), pair[1].toLong())
    }

    companion object {
        private val REGEX = "(toggle|turn on|turn off) ([\\d,]+) through ([\\d,]+)".toRegex()
    }
}

data class LightCmd(val cmd: String, val start: Point, val end: Point)
