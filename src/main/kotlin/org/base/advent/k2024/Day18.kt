package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Node
import org.base.advent.util.Node.Companion.createRootNode
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid
import java.util.*

/**
 * <a href="https://adventofcode.com/2024/day/18">Day 18</a>
 */
class Day18(private val width: Int, private val height: Int) : PuzzleFunction<List<String>, Pair<Long, String>> {

    override fun apply(input: List<String>): Pair<Long, String> {
        val unsafe = LinkedList<Point>()
        input.forEach { coord ->
            val arr = coord.split(",")
            unsafe.add(Point(arr[1].toInt(), arr[0].toInt()))
        }

        val memory = corruptMemory(unsafe)
        val part1 = findPath(memory)
        var firstBlocker: Point
        do {
            firstBlocker = unsafe.removeFirst()
            memory[firstBlocker.iy][firstBlocker.ix] = true
        } while (findPath(memory) > 0)

        return part1 to "${firstBlocker.iy},${firstBlocker.ix}"
    }

    private fun findPath(memory: Array<BooleanArray>): Long {
        var found = false
        val visited = mutableMapOf<Point, Long>()
        val target = Point(width - 1, height - 1)
        var minSteps = Long.MAX_VALUE
        val paths = LinkedList<Node<Point>>()
        paths.add(createRootNode(Point.ORIGIN))

        while (paths.isNotEmpty()) {
            val node = paths.removeFirst()
            val pos = node.data
            if (!visited.containsKey(pos)) {
                visited[pos] = node.depth

                if (pos == target) {
                    found = true
                    minSteps = node.depth
                }
                else
                    pos.neighbors().forEach { next ->
                        if (inGrid(next, width, height) &&
                            (!visited.containsKey(next)) &&
                            !memory[next.iy][next.ix])
                            paths.add(node.addChild(next))
                    }
            }
        }

        return if (found) minSteps else -1
    }

    private fun corruptMemory(unsafe: LinkedList<Point>): Array<BooleanArray> =
        with (unsafe) {
            val memory = Array(height) { BooleanArray(width) }
            for (i in 0 until 1024) {
                val corrupted = removeFirst()
                memory[corrupted.iy][corrupted.ix] = true
            }
            memory
        }
}