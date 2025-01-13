package org.base.advent.k2016

import org.base.advent.PuzzleFunction
import org.base.advent.PuzzleSolver
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid
import org.base.advent.util.ReusableDigest
import org.base.advent.util.ReusableDigest.hashHex
import org.base.advent.util.ReusableDigest.hexCharToInt

/**
 * <a href="https://adventofcode.com/2016/day/17">Day 17</a>
 */
class Day17 : PuzzleFunction<String, Pair<String, Int>> {
    override fun apply(input: String): Pair<String, Int> {
        val attempts = mutableListOf<VaultAttempt>()
        attempts.add(VaultAttempt(Point(1, 1), input))
        var shortest = ""
        var longest = ""

        while (attempts.isNotEmpty()) {
            val nextWave = attempts.toList()
            attempts.clear()

            for (attempt in nextWave) {
                val pt = attempt.pos
                if (pt == vaultInGrid) {
                    if (shortest.isEmpty() || attempt.path.length < shortest.length)
                        shortest = attempt.path

                    if (longest.isEmpty() || attempt.path.length > longest.length)
                        longest = attempt.path

                    continue
                }

                val udlr = hashHex(attempt.path).substring(0, 4)
                val moves = arrayOf(
                    pt.move(0, -2), pt.move(0, 2), pt.move(-2, 0), pt.move(2, 0))
                for (i in 0 until 4)
                    if (inGrid(moves[i], 8, 8) && hexCharToInt(udlr[i]) > 10) // open
                        attempts.add(VaultAttempt(moves[i], attempt.path + UDLR[i]))
            }
        }

        return shortest.substring(input.length) to longest.length - input.length
    }

    data class VaultAttempt(val pos: Point, val path: String)

    companion object {
        const val UDLR = "UDLR"
        val vaultInGrid = Point(7, 7)
    }
}