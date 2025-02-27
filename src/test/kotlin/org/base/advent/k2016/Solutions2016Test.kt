package org.base.advent.k2016

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testParallelSolutions
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

class Solutions2016Test : PuzzleReader {

    private val pool = Executors.newFixedThreadPool(5)

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

    @Test
    fun day11() {
        testParallelSolutions(Day11(pool), readLines("2016/input11.txt"), 37L, 61L)
    }

    @Test
    fun day12() {
        testParallelSolutions(Day12(pool), readLines("2016/input12.txt"), 318020, 9227674)
    }

    @Test
    fun day13() {
        testSolutions(Day13(), 1362, 82, 138)
    }

    @Test
    fun day15() {
        testParallelSolutions(Day15(pool), readLines("2016/input15.txt"), 121834, 3208099)
    }

    @Test
    fun day16() {
        testParallelSolutions(Day16(pool), "11110010111001001",
            "01110011101111011", "11001111011000111")
    }

    @Test
    fun day17() {
        testSolutions(Day17(), "awrkjxxr", "RDURRDDLRD", 526)
    }

    @Test
    fun day18() {
        testSolutions(Day18(), readSingleLine("2016/input18.txt"), 2005, 20008491)
    }

    @Test
    fun day19() {
        testParallelSolutions(Day19(pool), 3014603, 1834903, 1420280)
    }

    @Test
    fun day20() {
        testSolutions(Day20(), readLines("2016/input20.txt"), 17348574L, 104L)
    }

    @Test
    fun day21() {
        testParallelSolutions(Day21(pool), readLines("2016/input21.txt"),
            "dgfaehcb", "fdhgacbe")
    }

    @Test
    fun day22() {
        testParallelSolutions(Day22(pool), readLines("2016/input22.txt"), 1038, 252)
    }

    @Test
    fun day23() {
        testSolutions(Day23(), readLines("2016/input23.txt"), 11424, 479007984)
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