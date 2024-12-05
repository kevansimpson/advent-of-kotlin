package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid

/**
 * <a href="https://adventofcode.com/2024/day/4">Day 4</a>
 */
class Day04 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        var count = 0
        val xCandidates = mutableListOf<Point>()
        val mCandidates = mutableListOf<Point>()
        for (r in input.indices)
            for (c in input.indices) {
                if (input[r][c] == 'X')
                    xCandidates.add(Point(c, r))
                if (input[r][c] == 'M')
                    mCandidates.add(Point(c, r))
            }

        val gridX = XmasGrid(input)
        xCandidates.forEach { pt ->
            for (i in 0 until 8)
                if (gridX.isXmas(pt, 0, DX[i], DY[i]))
                    count++
        }

        val gridMas = XmasGrid(input)
        mCandidates.forEach { pt ->
            for (i in 1 until 8 step 2)
                gridMas.isXmas(pt, 1, DX[i], DY[i])
        }

        return count to gridMas.crossCount
    }

    data class XmasGrid(val grid: List<String>) {
        private val size = grid.size
        private val setA = mutableSetOf<Point>()
        var crossCount = 0

        fun isXmas(pt: Point, index: Int, dx: Int, dy: Int): Boolean {
            if (inGrid(pt, size, size) && index < 4) {
                if (grid[pt.iy][pt.ix] == XMAS[index]) {
                    if (index == 3)
                        return true
                    else {
                        val found = isXmas(pt.move(dx, dy), index + 1, dx, dy)
                        if (found && index == 2) { // A
                            if (setA.contains(pt))
                                crossCount++
                            else
                                setA.add(pt)
                        }
                        return found
                    }
                }
            }
            return false
        }
    }

    companion object {
        const val XMAS = "XMAS"
        val DX = arrayOf(0, 1, 1, 1, 0, -1, -1, -1)
        val DY = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
    }
}