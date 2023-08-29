package org.base.advent.k2022

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point
import org.base.advent.util.Text.columns

/**
 * <a href="https://adventofcode.com/2022/day/08">Day 08</a>
 */
class Day08 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val rows = input.map { it.toCharArray().map { i -> i.digitToInt() } }
        val columns = columns(input).map { it.toCharArray().map { i -> i.digitToInt() } }
        val size = input.size
        val grid = input.flatMapIndexed { row, line ->
            line.toCharArray().mapIndexed { col, ch -> Point(col, row) to ch.digitToInt() }
        }.toMap()
        val inside = grid.entries.filter { (pt, _) ->
            with(size - 1) { pt.x > 0 && pt.x < this && pt.y > 0 && pt.y < this }
        }

        val visibleTreesFromOutside = (4 * size - 4) +
                inside.count { (pt, height) ->
                    when {
                        height > rows[pt.iy].take(pt.ix).maxOf { it } -> true
                        height > rows[pt.iy].drop(pt.ix + 1).maxOf { it } -> true
                        height > columns[pt.ix].take(pt.iy).maxOf { it } -> true
                        height > columns[pt.ix].drop(pt.iy + 1).maxOf { it } -> true
                        else -> false
                    }
                }
        val highestScenicScore = inside.maxOf { (pt, height) ->
            look(rows[pt.iy].take(pt.ix).reversed(), height) * look(rows[pt.iy].drop(pt.ix + 1), height) *
                    look(columns[pt.ix].take(pt.iy).reversed(), height) * look(columns[pt.ix].drop(pt.iy + 1), height)
        }

        return visibleTreesFromOutside to highestScenicScore
    }
    private fun look(list: List<Int>, height: Int): Int =
            with (list.takeWhile { tree -> height > tree }) {
                if (list.size == size) size else size + 1
            }
}