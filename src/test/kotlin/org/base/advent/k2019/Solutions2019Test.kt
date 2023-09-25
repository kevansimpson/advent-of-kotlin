package org.base.advent.k2019

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2019Test : PuzzleReader {
    @Test
    fun day01() {
        testSolutions(Day01(), readIntLines("2019/input01.txt"), 3266288, 4896582)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), readSingleLine("2019/input02.txt").csvToLong(), 4570637L, 5485L)
    }

    @Test
    fun day03() {
        testSolutions(Day03(), readLines("2019/input03.txt"), 352L, 43848L)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), 235741..706948, 1178, 763)
    }

    @Test
    fun day05() {
        testSolutions(Day05(),
            readSingleLine("2019/input05.txt").csvToLong(), 13285749L, 5000972L)
    }

    @Test
    fun day06() {
        testSolutions(Day06(), readLines("2019/input06.txt"), 249308, 349)
    }

    @Test
    fun day07() {
        testSolutions(Day07(),
            readSingleLine("2019/input07.txt").csvToLong(), 75228L, 79846026L)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), readSingleLine("2019/input08.txt"), 1935,
            "011001111010000100101000010010100001000010010100001000011100100001001010000100001000010000100101000010010100001000010010100000110010000111100110011110")
    }

    companion object {
        @BeforeAll @JvmStatic
        fun header() {
            banner(2019)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2019)
        }
    }
}