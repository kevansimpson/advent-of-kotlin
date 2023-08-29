package org.base.advent.k2015

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2015/day/11">Day 11</a>
 */
class Day11 : PuzzleFunction<String, Pair<String, String>> {
    override fun apply(input: String): Pair<String, String> {
        val first = nextValidPassword(input)
        return first to nextValidPassword(first)
    }

    private fun nextValidPassword(str: String): String {
        var next = nextPassword(str)
        while (!isValid(next))
            next = nextPassword(next)

        return next
    }

    private fun nextPassword(str: String): String {
        val ltrs = str.toCharArray()
        run santa@ {
            for (i in str.length - 1 downTo 0) {
                val next = nextLetter(ltrs[i])
                ltrs[i] = next
                if (next != 'a') return@santa
            }
        }
        return String(ltrs)
    }

    private fun nextLetter(ltr: Char) =
        when (ltr) {
            'z' -> 'a'
            'h','k','n' -> ltr.inc().inc()
            else -> ltr.inc()
        }

    private fun isValid(str: String) =
            !isDisallowed(str) && hasNonOverlappingPairs(str) && hasSequence(str)

    private fun hasSequence(str: String): Boolean {
        var count = 1
        var current = 0
        val ltrs = str.toCharArray()
        for (ch in ltrs) {
            if (ch.code == (current + 1)) {
                count += 1
                if (count >= 3) return true
            }
            else
                count = 1

            current = ch.code
        }
        return false
    }

    private fun isDisallowed(str: String) = !DISALLOWED.matches(str)

    private fun hasNonOverlappingPairs(str: String) = "Q${str}Q".split(PAIRS).count() >= 3

    companion object {
        private val DISALLOWED = "[^iol]+".toRegex()
        private val PAIRS = "([a-z])\\1".toRegex()
    }
}