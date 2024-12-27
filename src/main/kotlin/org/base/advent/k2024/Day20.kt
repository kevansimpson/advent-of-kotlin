package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Dir
import org.base.advent.util.Node
import org.base.advent.util.Node.Companion.createRootNode
import org.base.advent.util.Point
import java.util.LinkedList
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2024/day/20">Day 20</a>
 */
class Day20(private val threshold: Int) : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val visited = mutableMapOf<Point, Long>()
        createCourse(input).findPath(visited)
        val list = visited.toList().sortedBy { (_, v) -> v }
        val cheats = intArrayOf(0, 0)
        for (p in list.indices) {
            val entry = list[p]
            for (i in (list.size - 1) downTo (p + 1)) {
                val last = list[i]
                val md = entry.first.manhattanDistance(last.first)
                if (md <= 20) {
                    if (abs((entry.second + md) - last.second) >= threshold) {
                        cheats[1]++
                        if (md == 2L)
                            cheats[0]++
                    }
                }
            }
        }

        return cheats[0] to cheats[1]
    }
}

class Course(val course: Array<CharArray>, val size: Int, val start: Point, val end: Point) {
    fun findPath(visited: MutableMap<Point, Long>) {
        val paths = LinkedList<Node<Point>>()
        paths.add(createRootNode(start))

        while (paths.isNotEmpty()) {
            val node = paths.removeFirst()
            val pos = node.data
            if (!visited.containsKey(pos)) {
                visited[pos] = node.depth
                if (pos == end)
                    return
                else {
                    for (dir in Dir.entries) {
                        val next = pos.move(dir)
                        if (!visited.containsKey(next) && get(next) != '#')
                            paths.add(node.addChild(next))
                    }
                }
            }
        }
    }

    private fun get(pos: Point): Char = course[pos.iy][pos.ix]
}

private fun createCourse(input: List<String>): Course {
    val size = input.size
    val course = Array(size) { CharArray(size) }
    var start = Point.ORIGIN
    var end = Point.ORIGIN

    for (r in (size - 1) downTo 0) {
        val row = size - r - 1
        for (c in 0 until size) {
            val at = input[row][c]
            course[row][c] = at
            when (at) {
                'S' -> start = Point(c, row)
                'E' -> end = Point(c, row)
            }
        }
    }

    return Course(course, size, start, end)
}