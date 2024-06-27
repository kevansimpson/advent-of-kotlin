package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractLetters
import org.base.advent.util.MathUtil.lowestCommonMultiple

/**
 * <a href="https://adventofcode.com/2023/day/8">Day 08</a>
 */
class Day08 : PuzzleFunction<List<String>, Pair<Int, Long>> {

    override fun apply(input: List<String>): Pair<Int, Long> =
        with (toNodeMap(input)) {
            findPath("AAA") to findAllPaths()
        }

    internal fun toNodeMap(input: List<String>): NodeMap =
        NodeMap(input[0],
            input.subList(2, input.size).map { it.extractLetters() }.associate { it[0] to Node(it[1], it[2]) })

    data class Node(val left: String, val right: String)

    data class NodeMap(val directions: String, val nodes: Map<String, Node>) {
        fun findPath(start: String): Int {
            var current = start
            var index = 0
            while (!current.endsWith("Z")) {
                val n = nodes[current]!!
                val dir = directions[index++ % directions.length]
                current = if (dir == 'L') n.left else n.right
            }
            return index
        }

        fun findAllPaths(): Long {
            val startList = nodes.keys.filter { it.endsWith("A") }
            return lowestCommonMultiple(startList.map { findPath(it).toLong() }.toLongArray())
        }
    }
}