package org.base.advent.k2022

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import org.base.advent.util.Node.Companion.createRootNode
import org.base.advent.util.NodeDepthMap
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid

/**
 * <a href="https://adventofcode.com/2022/day/12">Day 12</a>
 */
class Day12 : PuzzleReader, TimeSaver {

    private val input = readLines("2022/input12.txt")
    private val localArea = hill(input).also {
        if (fullSolve)
            it.graph()
        debug("${it.start} --> ${it.peak}")
    }

    override fun solve1(): Any = climb(localArea)

    override fun solve2(): Any =
        localArea.heightmap.filterValues { it == 'a' }.keys.minOf { climb(hill(input).copy(start = it)) }

    private fun climb(hill: Hill): Long {
        val nodes = mutableListOf(createRootNode(hill.start))
        val depthMap = NodeDepthMap<Point>()

        while (nodes.isNotEmpty()) {
            val current = nodes.toList().also { nodes.clear() }
            val depth = depthMap.incrementCurrent()
            current.forEach { node ->
                val last = node.data
                if (!depthMap.visited(last)) {
                    depthMap.setDepth(last, depth)
                    if (last == hill.peak)
                        depthMap.minimum = depth
                    else {
                        val next = last.neighbors()
                            .filter {
                                depthMap.depthAt(it) >= depth &&
                                        !node.contains(it) &&
                                        inGrid(it, hill.width, hill.length) &&
                                        hill.isHigher(last, it)
                            }
                        nodes.addAll(next.map { node.addChild(it) })
                    }
                }
            }
        }

        return depthMap.minimum
    }

    private fun hill(heightmap: List<String>): Hill =
        Hill(heightmap.foldIndexed(mutableMapOf()) { y, hill, line ->
            hill.apply {
                line.forEachIndexed { x, ch ->
                    put(Point(x, y), ch)
                }
            }
        }, heightmap.size, heightmap.first().length)
}

data class Hill(val heightmap: Map<Point, Char>,
                val length: Int,
                val width: Int,
                val start: Point = heightmap.filterValues { it == 'S' }.keys.first(),
                val peak: Point = heightmap.filterValues { it == 'E' }.keys.first()) {

    private val numberMap = heightmap.mapValues { it.value.code }
        .toMutableMap().apply {
            this[start] = A
            this[peak] = PEAK
        }

    fun isHigher(here: Point, next: Point) = (A..(numberMap[here]!! + 1)).contains(numberMap[next]!!)

    fun graph() {
        (0 until length).forEach { y ->
            (0 until width).forEach { x ->
                print(heightmap[Point(x, y)])
            }
            println()
        }
    }

    companion object {
        const val A = 'a'.code
        private const val Z = 'z'.code
        const val PEAK = Z + 1
    }
}