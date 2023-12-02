package org.base.advent.k2023

import org.base.advent.PuzzleReader
import org.base.advent.util.Extensions.extractDigits
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Advent2023UnitTest : PuzzleReader {
    @Test
    fun day01() {
        val input = listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")
        val input2 = listOf("two1nine", "eightwothree", "abcone2threexyz", "xtwone3four",
            "4nineeightseven2", "zoneight234", "7pqrstsixteen")
        with (Day01()) {
            assertEquals(142, input.sumOf { combine(it.extractDigits()) })
            assertEquals(281, input2.sumOf { mapDigits(it) })
        }
    }
}
