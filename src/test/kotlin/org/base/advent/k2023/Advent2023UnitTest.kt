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

    @Test
    fun day03() {
        val input = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..")
        with (Day03()) {
            assertEquals(4361 to 0, apply(input))
        }
    }

    @Test
    fun day04() {
        val input = listOf(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")
        with (Day04()) {
            assertEquals(13 to 30, apply(input))
        }
    }

    @Test
    fun day05() {
        val input = readLines("2023/input05_example.txt")
        with (Day05()) {
            assertEquals(35L to 46L, apply(input))
        }
    }

    @Test
    fun day06() {
        val input = listOf(Race(7, 9), Race(15, 40), Race(30, 200))
        with (Day06()) {
            assertEquals(288L to 71503L, apply(input))
        }
    }
}
