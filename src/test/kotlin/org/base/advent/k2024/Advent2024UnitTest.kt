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
}
