package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Text.splitByBlankLine

/**
 * <a href="https://adventofcode.com/2024/day/25">Day 25</a>
 */
class Day25 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val data = splitByBlankLine(input)
        val keys = mutableListOf<IntArray>()
        val locks = mutableListOf<IntArray>()
        data.forEach { schematic ->
            if (schematic[0] == "#####")
                locks.add(toHeights(schematic))
            else
                keys.add(toHeights(schematic))
        }

        return keys.sumOf { k -> locks.count { l -> fits(k, l) } } to 0
    }

    private fun fits(key: IntArray, lock: IntArray): Boolean {
        for (i in key.indices)
            if (key[i] + lock[i] > 5)
                return false
        return true
    }

    private fun toHeights(keyOrLock: List<String>): IntArray {
        val heights = IntArray(5) { -1 }
        keyOrLock.forEach { kl ->
            for (i in heights.indices)
                if (kl[i] == '#')
                    heights[i]++
        }
        return heights
    }
}