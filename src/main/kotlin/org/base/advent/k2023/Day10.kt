package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import org.base.advent.util.Node
import org.base.advent.util.Point
import org.base.advent.util.Square
import kotlin.RuntimeException

/**
 * <a href="https://adventofcode.com/2023/day/10">Day 10</a>
 */
class Day10 : PuzzleFunction<List<String>, Pair<Long, Int>> {

    override fun apply(input: List<String>): Pair<Long, Int> {
        val grid = Square(input.map { it.toCharArray() }.toTypedArray())
        val animal = grid.first('S')
        val loop = buildLoop(animal, grid)
        return loop.depth / 2 to 0
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