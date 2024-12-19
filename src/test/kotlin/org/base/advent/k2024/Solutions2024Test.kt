package org.base.advent.k2024

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testParallelSolutions
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

/**
 */
class Solutions2024Test : PuzzleReader {

    private val pool = Executors.newFixedThreadPool(5)

    @Test
    fun day01() {
        testSolutions(Day01(), readLines("2024/input01.txt"), 2057374, 23177084)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), readLines("2024/input02.txt"), 534, 577)
    }

    @Test
    fun day03() {
        testSolutions(Day03(), readSingleLine("2024/input03.txt"), 184122457, 107862689)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), readLines("2024/input04.txt"), 2530, 1921)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), readLines("2024/input05.txt"), 6612, 4944)
    }

    @Test
    fun day06() {
        testSolutions(Day06(), readLines("2024/input06.txt"), 4454, 1503)
    }

    @Test
    fun day07() {
        testSolutions(Day07(), readLines("2024/input07.txt"), 42283209483350L, 1026766857276279L)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), readLines("2024/input08.txt"), 327, 1233)
    }

    @Test
    fun day09() {
        testSolutions(Day09(), readSingleLine("2024/input09.txt"), 6334655979668L, 6349492251099L)
    }

    @Test
    fun day10() {
        testSolutions(Day10(pool), readLines("2024/input10.txt"), 733, 1514)
    }

    @Test
    fun day11() {
        testSolutions(Day11(), readSingleLine("2024/input11.txt"), 203609L, 240954878211138L)
    }

    @Test
    fun day12() {
        testSolutions(Day12(), readLines("2024/input12.txt"), 1304764, 811148)
    }

    @Test
    fun day13() {
        testSolutions(Day13(pool), readLines("2024/input13.txt"), 33209L, 83102355665474L)
    }

    @Test
    fun day14() {
        testSolutions(Day14(101, 103),
            readLines("2024/input14.txt"), 208437768, 7492)
    }

    @Test
    fun day15() {
        testParallelSolutions(Day15(pool),
            readLines("2024/input15.txt"), 1457740, 1467145)
    }

    companion object {
        @BeforeAll @JvmStatic
        fun header() {
            banner(2024)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2024)
        }
    }
}
