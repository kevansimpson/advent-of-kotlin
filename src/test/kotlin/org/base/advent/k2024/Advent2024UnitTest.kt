package org.base.advent.k2024

import org.base.advent.PuzzleReader
import org.base.advent.util.Extensions.extractDigits
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Advent2024UnitTest : PuzzleReader {
    @Test
    fun day01() {
        val input = listOf("3   4", "4   3", "2   5", "1   3", "3   9", "3   3")
        with (Day01()) {
            assertEquals(11 to 31, apply(input))
        }
    }

    @Test
    fun day02() {
        val input = listOf("7 6 4 2 1", "1 2 7 8 9", "9 7 6 2 1", "1 3 2 4 5", "8 6 4 4 1", "1 3 6 7 9")
        with (Day02()) {
            assertEquals(2 to 4, apply(input))
        }
    }

    @Test
    fun day03() {
        val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val enabled = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        with (Day03()) {
            assertEquals(161 to 161, apply(input))
            assertEquals(161 to 48, apply(enabled))
        }
    }
}
