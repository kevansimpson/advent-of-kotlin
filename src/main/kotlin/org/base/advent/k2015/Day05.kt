package org.base.advent.k2015

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2015/day/5">Day 5</a>
 */
class Day05 : PuzzleReader {

    private val input = readLines("2015/input05.txt")

    override fun solve1(): Any =
        input.count { VOWELS.findAll(it).count() >= 3 &&
                      DOUBLE_LETTERS.findAll(it).count() >= 1 &&
                      BAD_STRINGS.findAll(it).count() <= 0 }

    override fun solve2(): Any =
            input.count { NON_OVERLAPPING_LETTER_PAIRS.findAll(it).count() >= 1 &&
                          SANDWICH_LETTERS.findAll(it).count() >= 1 }

    companion object {
        private val VOWELS                          = "[aeiou]".toRegex()
        private val DOUBLE_LETTERS                  = "([a-z])\\1+".toRegex()
        private val BAD_STRINGS                     = "(ab|cd|pq|xy)".toRegex()
        private val NON_OVERLAPPING_LETTER_PAIRS    = "([a-z]{2}).*\\1+".toRegex()
        private val SANDWICH_LETTERS                = "([a-z]).\\1+".toRegex()
    }
}