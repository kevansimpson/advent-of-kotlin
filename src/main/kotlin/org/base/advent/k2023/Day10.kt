package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point
import org.base.advent.util.Square
import kotlin.RuntimeException

/**
 * <a href="https://adventofcode.com/2023/day/10">Day 10</a>
 */
class Day10 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val grid = Square(input.map { it.toCharArray() }.toTypedArray()) // size=140x140
        val animal = grid.first('S') // 50,39
        println(animal)
//        grid.display()
        val loop = buildLoop(animal, grid)
        println("loop => ${loop.size}")
        return loop.size / 2 to 0
    }

    private fun buildLoop(animal: Point, grid: Square): List<Point> {
        var list = mutableListOf(listOf(animal))
        while (list.isNotEmpty()) {
            val next = mutableListOf<List<Point>>()
            for (path in list) {
                val last = path.last()
                if ("|LJS".contains(grid[last]))
                    north(last, grid)?.let { next.add(path + it) }
                if ("|7FS".contains(grid[last]))
                    south(last, grid)?.let { next.add(path + it) }
                if ("-LFS".contains(grid[last]))
                    east(last, grid)?.let { next.add(path + it) }
                if ("-7JS".contains(grid[last]))
                    west(last, grid)?.let { next.add(path + it) }
            }

//            println("----- next")
//            next.forEach { println(it) }
            next.firstOrNull { it.size > 3 && it.last() == animal }?.let { return it } // loop!
            list = next.filter {
                val tail = it.last()
                var allowed = true
                for (j in 0..it.size - 2)
                    if (it[j] == tail) {
                        allowed = false
                        break
                    }
                allowed
            }.toMutableList()
//            println("----- list")
//            list.forEach { println(it) }
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