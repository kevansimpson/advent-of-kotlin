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
}
