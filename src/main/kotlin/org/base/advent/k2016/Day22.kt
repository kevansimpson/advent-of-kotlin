package org.base.advent.k2016

import org.base.advent.ParallelSolution
import org.base.advent.TimeSaver
import org.base.advent.util.Extensions.extractInt
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid
import org.base.advent.util.Point.Companion.manhattanDistance
import java.util.concurrent.ExecutorService
import kotlin.math.max

/**
 * <a href="https://adventofcode.com/2016/day/22">Day 22</a>
 */
class Day22(pool: ExecutorService) : ParallelSolution<List<String>>(pool), TimeSaver {

    override fun solve1(input: List<String>): Any = countViable(toNodeList(input))

    override fun solve2(input: List<String>): Any {
        val nodeMap =  toNodeMap(toNodeList(input).associateBy { it.pos })
        if (fullSolve)
            nodeMap.display()

        return sneakPastWall(nodeMap)
    }

    /**
     * My data puts empty below and horizontal wall of immovable objects,
     * which extends from right side of grid. This function calculates the
     * path to the point left of the wall, then the path to left of the goal.
     * Once there, it counts loops as empty circles and moves goal to the left.
     */
    private fun sneakPastWall(nodeMap: NodeMap): Int {
        val leftOfWall = nodeMap.immovableRocks.minBy { it.ix }.move("<")
        val toWall = countSteps(nodeMap, nodeMap.empty, leftOfWall, 0, mutableSetOf())
        val toTarget = countSteps(nodeMap, leftOfWall, nodeMap.goal.move("<"), 0, mutableSetOf())
        // empty to goal for all X, except x=0|width, + one final empty to goal
        val moveGoal = (nodeMap.width - 2) * 5 + 1
        return toWall + toTarget + moveGoal
    }

    private fun countSteps(nodeMap: NodeMap, start: Point, target: Point, steps: Int, visited: MutableSet<Point>): Int {
        if (start == target)
            return steps
        else
            visited.add(start)

        val current = nodeMap.get(start)
        val moves = nodeMap.moves(start).sortedBy { manhattanDistance(it, target) }
        moves.forEach { next ->
            if (!visited.contains(next) && next != nodeMap.goal) {
                val node = nodeMap.get(next)
                current.move(node)
                val path = countSteps(nodeMap, next, target, steps + 1, visited)
                if (path > 0)
                    return path
                else {
                    current.reset()
                    node.reset()
                }
            }
        }

        visited.remove(start)
        return -1
    }

    private fun countViable(nodeList: List<StorageNode>): Int {
        var viable = 0
        nodeList.forEach { a ->
            nodeList.forEach { b ->
                if (isViable(a, b))
                    viable++
            }
        }
        return viable
    }

    private fun isViable(nodeA: StorageNode, nodeB: StorageNode): Boolean =
        nodeA != nodeB && nodeA.used > 0 && nodeA.used <= nodeB.available

    private fun toNodeList(input: List<String>): List<StorageNode> =
        input.drop(2).map { it.replace("-", "").extractInt() }.map { StorageNode(it) }

    companion object {
        fun toNodeMap(grid: Map<Point, StorageNode>): NodeMap {
            var h = 0
            var w = 0
            var e = Point.ORIGIN
            for (node in grid.values) {
                h = max(h, node.pos.iy)
                w = max(w, node.pos.ix)
                if (node.used == 0)
                    e = node.pos
            }

            return NodeMap(grid, e, Point(w, 0), h + 1, w + 1)
        }

    }

    class StorageNode(val pos: Point, val size: Int, var used: Int, var available: Int, private var moved: Int) {
        constructor(nums: List<Int>) : this(Point(nums[0], nums[1]), nums[2], nums[3], nums[4], 0)

        fun move(node: StorageNode) {
            moved = node.used
            used += moved
            available -= moved
            node.used -= moved
            node.available += moved
        }

        fun reset() {
            used += moved
            available -= moved
            moved = 0
        }
    }

    class NodeMap(val grid: Map<Point, StorageNode>,
                  val empty: Point, val goal: Point,
                  val height: Int, val width: Int) {
        val immovableRocks = grid.values.filter { tooBig(it) }.map { it.pos }

        fun get(pt: Point) = grid[pt]!!

        fun moves(from: Point): List<Point> =
            from.neighbors().filter {
                inGrid(it, width, height) && !immovableRocks.contains(it) && get(from).available >= get(it).used
            }

        fun display() {
            println()
            for (y in 0 until height) {
                println()
                for (x in 0 until width)
                    print(fromNode(grid[Point(x, y)]!!))
            }
            println()
        }

        private fun fromNode(node: StorageNode): String =
            when {
                node.used == 0 -> "_"
                node.pos == goal -> "G"
                immovableRocks.contains(node.pos) -> "#"
                else -> "."
            }

        private fun tooBig(node: StorageNode): Boolean =
            node.pos.neighbors().filter { inGrid(it, width, height) }.any { grid[it]!!.size < node.used }
    }
}