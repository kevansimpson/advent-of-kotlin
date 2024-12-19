package org.base.advent.k2024

import org.base.advent.ParallelSolution
import org.base.advent.TimeSaver
import org.base.advent.util.Point
import org.base.advent.util.Text.splitByBlankLine
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2024/day/15">Day 15</a>
 */
class Day15(pool: ExecutorService) : ParallelSolution<List<String>>(pool), TimeSaver {
    override fun solve1(input: List<String>): Any = solve(input, ::StandardWarehouse)

    override fun solve2(input: List<String>): Any = solve(input, ::WideWarehouse)

    private fun solve(input: List<String>, factory: (List<String>) ->  Warehouse): Any {
        val data = splitByBlankLine(input)
        val warehouse = factory.invoke(data[0])
        val movements = data[1].joinToString("")
        for (dir in movements)
            warehouse.moveGuard(dir)
        if (fullSolve)
            warehouse.display()
        return warehouse.sumGps()
    }
}

interface Warehouse {
    fun moveGuard(dir: Char)
    fun display()
    fun sumGps(): Int
}

abstract class BaseWarehouse(w: Int, h: Int) : Warehouse {
    val width: Int = w
    val height: Int = h
    val grid: Array<CharArray> = Array(height) { CharArray(width) }
    var guard: Point = Point.ORIGIN

    fun move(dir: Char, pos: Point, moving: Char): Boolean =
        when (val at = get(pos)) {
            '.' -> {
                set(pos, moving)
                true
            }
            'O', '[', ']' -> {
                if (move(dir, pos.move(dir), at)) {
                    set(pos, moving)
                    true
                }
                else
                    false
            }
            else -> false
        }

    override fun display() {
        println()
        for (r in (height - 1) downTo 0)
            println(grid[r].contentToString().replace(", ", ""))
        println()
    }

    fun sumGps(box: Char): Int {
        var gps = 0
        for (r in 0 until height)
            for (c in 0 until width)
                if (grid[r][c] == box)
                    gps += 100 * (height - r - 1) + c
        return gps
    }

    fun get(pos: Point): Char = grid[pos.iy][pos.ix]

    fun set(pos: Point, at: Char) {
        grid[pos.iy][pos.ix] = at
    }
}

class StandardWarehouse(input: List<String>) : BaseWarehouse(input.size, input.size) {
    init {
        for (r in (height - 1) downTo 0)
            for (c in 0 until width) {
                val at = input[r][c]
                grid[height - r - 1][c] = at
                if (at == '@')
                    guard = Point(c, height - r - 1)
            }
    }

    override fun moveGuard(dir: Char) {
        val next = guard.move(dir)
        if (move(dir, next, '@')) {
            set(guard, '.')
            guard = next
        }
    }

    override fun sumGps(): Int = sumGps('O')
}

class WideWarehouse(input: List<String>) : BaseWarehouse(input.size * 2, input.size) {
    init {
        for (r in (height - 1) downTo 0)
            for (c in 0 until height) {
                val h = height - r - 1
                val w = 2 * c
                when (val at = input[r][c]) {
                    '#', '.' -> {
                        grid[h][w] = at
                        grid[h][w + 1] = at
                    }
                    '@' -> {
                        guard = Point(w, h)
                        grid[h][w] = at
                        grid[h][w + 1] = '.'
                    }
                    'O' -> {
                        grid[h][w] = '['
                        grid[h][w + 1] = ']'
                    }
                }
            }
    }

    override fun moveGuard(dir: Char) {
        val next = guard.move(dir)
        when (dir) {
            '<','>' -> {
                if (move(dir, next, '@')) {
                    set(guard, '.')
                    guard = next
                }
            }
            '^','v' -> {
                val moves = moveVertically(dir, guard, '@')
                if (moves.isNotEmpty()) {
                    moves.forEach(this::set)
                    guard = next
                }
            }
        }
    }

    private fun moveVertically(dir: Char, pos: Point, moving: Char): Map<Point, Char> {
        val next = pos.move(dir)
        return when (val at = get(next)) {
            '.' -> mapOf(next to moving, pos to '.')
            '[' -> {
                val move1 = moveVertically(dir, next, at)
                val move2 = moveVertically(dir, next.move(1, 0), ']')
                if (move1.isNotEmpty() && move2.isNotEmpty()) {
                    val total = merge(move1, move2)
                    total[next] = moving
                    total[pos] = '.'
                    total
                }
                else
                    emptyMap()
            }
            ']' -> {
                val move1 = moveVertically(dir, next, at)
                val move2 = moveVertically(dir, next.move(-1, 0), '[')
                if (move1.isNotEmpty() && move2.isNotEmpty()) {
                    val total = merge(move1, move2)
                    total[next] = moving
                    total[pos] = '.'
                    total
                }
                else
                    emptyMap()
            }
            else -> emptyMap()
        }
    }

    private fun merge(m1: Map<Point, Char>, m2: Map<Point, Char>): MutableMap<Point, Char> =
        mutableMapOf<Point, Char>().apply {
            putAll(m1)
            m2.forEach { (k, v) ->
                if (!containsKey(k) || v != '.')
                    put(k, v)
            }
        }

    override fun sumGps(): Int = sumGps('[')
}