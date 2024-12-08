package org.base.advent.k2024

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

/**
 */
class Solutions2024Test : PuzzleReader {

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
