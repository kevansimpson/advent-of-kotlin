package org.base.advent.k2016

import org.apache.commons.lang3.StringUtils.countMatches
import org.base.advent.PuzzleFunction
import org.base.advent.util.Point
import java.lang.Long.toBinaryString

/**
 * <a href="https://adventofcode.com/2016/day/13">Day 13</a>
 */
class Day13 : PuzzleFunction<Int, Pair<Int, Int>> {
    override fun apply(input: Int): Pair<Int, Int> {
        var depth = -1
        val depthMap = mutableMapOf<Point, Int>()
        val search = mutableListOf(Point(1, 1))

        maze@ while (search.isNotEmpty()) {
            depth++
            val current = search.toList()
            search.clear()

            for (pt in current) {
                if (!depthMap.containsKey(pt)) {
                    depthMap[pt] = if (isWall(pt, input)) WALL else depth

                    if (TARGET == pt)
                        break@maze

                    search.addAll(pt.neighbors().filter { isOpenSpace(it, input) })
                }
            }
        }

        return depth to depthMap.values.count { it <= 50 }
    }

    private fun isOpenSpace(pt: Point, input: Int) = pt.x >= 0 && pt.y >= 0 && !isWall(pt, input)

    private fun isWall(pt: Point, input: Int) = (countMatches(toBinary(pt, input), "1") % 2) != 0

    private fun toBinary(pt: Point, input: Int) =
        toBinaryString(pt.x * pt.x + 3 * pt.x + 2 * pt.x * pt.y + pt.y + pt.y * pt.y + input)

    companion object {
        const val WALL = -1
        val TARGET = Point(31, 39)
    }
}