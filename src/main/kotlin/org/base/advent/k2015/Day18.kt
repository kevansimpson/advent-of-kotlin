package org.base.advent.k2015

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver

/**
 * <a href="https://adventofcode.com/2015/day/18">Day 18</a>
 */
class Day18 : PuzzleReader, TimeSaver {

    private val input = readLines("2015/input18.txt")

    override fun solve1(): Any = totalLights(false)

    override fun solve2(): Any = totalLights(true)

    private fun totalLights(breakCornerLights: Boolean): Int {
        val grid = loadGrid()
        if (fullSolve) display(grid)
        if (breakCornerLights)
            breakCornerLights(grid)

        return countLights((0 until 100).fold(grid) { current, _ -> click(current, breakCornerLights) })
    }

    private fun click(grid: Array<BooleanArray>, breakCornerLights: Boolean): Array<BooleanArray> {
        val size = grid.size
        val next = Array(size) { BooleanArray(size) }

        for (i in 1..size - 2)
            for (j in 1..size - 2) {
                val on = grid[i][j]
                val neighbors = countNeighbors(grid, i, j)
                next[i][j] = if (on) neighbors == 2 || neighbors == 3 else neighbors == 3
            }

        if (breakCornerLights)
            breakCornerLights(next)

        return next
    }

    private fun countLights(grid: Array<BooleanArray>): Int = grid.sumOf { row -> row.count { it } }

    private fun breakCornerLights(grid: Array<BooleanArray>) {
        val size = grid.size
        grid[1][1] = true
        grid[1][size - 2] = true
        grid[size - 2][1] = true
        grid[size - 2][size - 2] = true
    }

    private fun countNeighbors(grid: Array<BooleanArray>, i: Int, j: Int): Int =
        listOf(grid[i - 1][j - 1], grid[i][j - 1], grid[i + 1][j - 1],
                grid[i - 1][j], grid[i + 1][j],
                grid[i - 1][j + 1], grid[i][j + 1], grid[i + 1][j + 1]).count { it }

    private fun loadGrid(): Array<BooleanArray> {
        val size = input.size
        val grid = Array(size + 2) { BooleanArray(size + 2) }
        for (i in 0 until size) {
            val bits = input[i].toCharArray()
            for (j in 0 until size)
                grid[i+1][j + 1] = (bits[j] == '#')
        }
        return grid
    }

    private fun display(grid: Array<BooleanArray>) {
        for (i in 1..grid.size - 2) {
            for (j in 1..grid.size - 2) {
                print(if (grid[i][j]) "#" else ".")
            }
            println()
        }
    }
}
