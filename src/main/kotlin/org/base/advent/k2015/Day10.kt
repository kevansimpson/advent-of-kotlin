package org.base.advent.k2015

import org.base.advent.PuzzleReader
import java.lang.StringBuilder

/**
 * <a href="https://adventofcode.com/2015/day/10">Day 10</a>
 */
class Day10 : PuzzleReader {

    private val input = "1321131112"

    override fun solve1(): Any = lookSay(input, 40).length

    override fun solve2(): Any = lookSay(input, 50).length

    private fun lookSay(lookSay: String, count: Int) =
            (0 until count).fold(lookSay) { said, _ -> lookSay(said)}

    private fun lookSay(lookSay: String): String {
        var count = 0
        var digit = lookSay[0]
        val bldr = StringBuilder()
        for (ch in lookSay.toCharArray()) {
            if (digit == ch)
                count += 1
            else {
                bldr.append(count).append(digit)
                digit = ch
                count = 1
            }
        }

        return bldr.append(count).append(digit).toString()
    }
}