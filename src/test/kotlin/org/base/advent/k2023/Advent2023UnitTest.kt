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

    @Test
    fun day02() {
        val input = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
        with (Day02()) {
            val games = input.map { parse(it) }
            assertEquals(8, games.filter { it.reveals.all { r -> r.only12R13G14B() } }.sumOf { it.id })
        }
    }
}
