package org.base.advent.k2016

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2016Test : PuzzleReader {

    @Test
    fun day01() {
        testSolutions(Day01(), readSingleLine("2016/input01.txt").csv(", "), 288L, 111L)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), readLines("2016/input02.txt"), "76792", "A7AC3")
    }

    @Test
    fun day03() {
        testSolutions(Day03(), readLines("2016/input03.txt"), 982, 1826)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), readLines("2016/input04.txt"), 137896, 501)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), "uqwqemis", "1a3099aa", "694190cd")
    }

    @Test
    fun day06() {
        testSolutions(Day06(), readLines("2016/input06.txt"), "umcvzsmw", "rwqoacfz")
    }

    @Test
    fun day07() {
        testSolutions(Day07(), readLines("2016/input07.txt"), 118, 260)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), readLines("2016/input08.txt"), 116, "UPOJFLBCEZ")
    }

    @Test
    fun day09() {
        testSolutions(Day09(), readSingleLine("2016/input09.txt"), 123908L, 10755693147L)
    }

    @Test
    fun day10() {
        testSolutions(Day10(), readLines("2016/input10.txt"), 116, 23903)
    }
    companion object {
        @BeforeAll @JvmStatic
        fun header() {
            banner(2016)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2016)
        }
    }
}