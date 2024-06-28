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

    @Test
    fun day07() {
        val input = listOf("32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483")
        with (Day07()) {
            assertEquals(6440L to 5905L, apply(input))
        }
    }

    @Test
    fun day08() {
        with (Day08()) {
            val input1 = toNodeMap(listOf("RL", "",
                "AAA = (BBB, CCC)",
                "BBB = (DDD, EEE)",
                "CCC = (ZZZ, GGG)",
                "DDD = (DDD, DDD)",
                "EEE = (EEE, EEE)",
                "GGG = (GGG, GGG)",
                "ZZZ = (ZZZ, ZZZ))"))
            val input2 = toNodeMap(listOf("LLR", "", "AAA = (BBB, BBB)", "BBB = (AAA, ZZZ)", "ZZZ = (ZZZ, ZZZ)"))
            val input3 = toNodeMap(listOf("LR", "",
                "11A = (11B, XXX)",
                "11B = (XXX, 11Z)",
                "11Z = (11B, XXX)",
                "22A = (22B, XXX)",
                "22B = (22C, 22C)",
                "22C = (22Z, 22Z)",
                "22Z = (22B, 22B)",
                "XXX = (XXX, XXX)"))
            assertEquals(2, input1.findPath("AAA"))
            assertEquals(6, input2.findPath("AAA"))
            assertEquals(2, input3.findPath("11A"))
            assertEquals(3, input3.findPath("22A"))
            assertEquals(6, input3.findAllPaths())
        }
    }

    @Test
    fun day09() {
        val input = listOf("0 3 6 9 12 15", "1 3 6 10 15 21", "10 13 16 21 30 45")
        with (Day09()) {
            assertEquals(114L to 2L, apply(input))
        }
    }

    @Test
    fun day10() {
        val input1 = listOf(
            "-L|F7",
            "7S-7|", // F-7     012
            "L|7||", // | |     1 3
            "-L-J|", // L-J     234
            "L|-JF")
        val input2 = listOf(
            "7-F7-", //   F7       45
            ".FJ|7", //  FJ|      236
            "SJLL7", // FJ L7    01 78
            "|F--J", // |F--J    14567
            "LJ.LJ") // LJ       23
        with (Day10()) {
            assertEquals(4L to 1, apply(input1))
            assertEquals(8L to 1, apply(input2))
        }
    }
}
