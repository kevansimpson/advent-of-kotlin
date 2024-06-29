package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import org.base.advent.util.Node
import org.base.advent.util.Point
import org.base.advent.util.Square
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.RuntimeException
import kotlin.math.max
import kotlin.math.min

/**
 * <a href="https://adventofcode.com/2023/day/10">Day 10</a>
 */
class Day10 : PuzzleFunction<List<String>, Pair<Long, Int>> {

    override fun apply(input: List<String>): Pair<Long, Int> {
        val grid = Square(input.map { it.toCharArray() }.toTypedArray())
        val animal = grid.first('S')
        val loop = buildLoop(animal, grid)
        return loop.depth / 2 to insidePolygon(loop, grid)
    }

    private fun insidePolygon(loop: Node<Point>, grid: Square): Int {
        var minX = grid.size + 1
        var maxX = -1
        var minY = minX
        var maxY = -1
        var node: Node<Point>? = loop
        val pipes = mutableListOf<Point>()
        while (node != null) {
            with (node.data) {
                pipes.add(this)
                minX = min(minX, ix)
                maxX = max(maxX, ix)
                minY = min(minY, iy)
                maxY = max(maxY, iy)
            }
            node = node.parent
        }
        pipes.removeAt(0)

        val area = AtomicInteger(0)
        val pool = Executors.newFixedThreadPool(4)
        pool.use {
            for (row in minY..maxY) {
                for (col in minX..maxX)
                    it.submit { insidePolygon(row, col, pipes, area) }
            }
        }
        return area.get()
    }

    // h/t https://www.naukri.com/code360/library/check-if-a-point-lies-in-the-interior-of-a-polygon
    private fun insidePolygon(x: Int, y: Int, loop: List<Point>, area: AtomicInteger) {
        if (loop.contains(Point(x, y))) return

        val vertices = loop.size
        var p1 = loop[0]
        var inside = false
        for (i in 1..vertices) {
            val p2 = loop[i % vertices]
            if (y > min(p1.iy, p2.iy) && y <= max(p1.iy, p2.iy) && x <= max(p1.ix, p2.ix))
                if (p1.ix == p2.ix || x <= ((y - p1.iy) * (p2.ix - p1.ix) / (p2.iy - p1.iy) + p1.ix))
                    inside = !inside
            p1 = p2
        }
        if (inside)
            area.incrementAndGet()
    }

    private fun buildLoop(animal: Point, grid: Square): Node<Point> {
        var list = mutableListOf(Node(animal))
        while (list.isNotEmpty()) {
            val next = mutableListOf<Node<Point>>()
            for (node in list) {
                val last = node.data
                if ("|LJS".contains(grid[last]))
                    north(last, grid)?.let { next.add(node.addChild(it)) }
                if ("|7FS".contains(grid[last]))
                    south(last, grid)?.let { next.add(node.addChild(it)) }
                if ("-LFS".contains(grid[last]))
                    east(last, grid)?.let { next.add(node.addChild(it)) }
                if ("-7JS".contains(grid[last]))
                    west(last, grid)?.let { next.add(node.addChild(it)) }
            }

            next.firstOrNull { it.depth > 3 && it.data == animal }?.let { return it } // loop! NOT
            list = next.filter { it.parent?.parent?.data != it.data }.toMutableList()
        }
        throw RuntimeException("no loop")
    }

    private fun north(pt: Point, grid: Square): Point? = check(pt.move(0, -1), "|7FS", grid)
    private fun south(pt: Point, grid: Square): Point? = check(pt.move(0, 1), "|LJS", grid)
    private fun east(pt: Point, grid: Square): Point? = check(pt.move(1, 0), "-7JS", grid)
    private fun west(pt: Point, grid: Square): Point? = check(pt.move(-1, 0), "-LFS", grid)

    private fun check(pt: Point, allowed: String, grid: Square): Point? =
        if (grid.contains(pt) && allowed.contains(grid[pt])) pt else null
}