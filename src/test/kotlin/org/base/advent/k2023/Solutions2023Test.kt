package org.base.advent.k2023

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2023Test : PuzzleReader {

    @Test
    fun day01() {
        testSolutions(Day01(), readLines("2023/input01.txt"), 54667, 54203)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), readLines("2023/input02.txt"), 2593, 54699)
    }

    @Test
    fun day03() {
        testSolutions(Day03(), readLines("2023/input03.txt"), 549908, 0)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), readLines("2023/input04.txt"), 21088, 6874754)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), readLines("2023/input05.txt"), 424490994, 15290096)
    }

    @Test
    fun day06() {
        testSolutions(Day06(), listOf(
            Race(41, 249),
            Race(77, 1362),
            Race(70, 1127),
            Race(96, 1011)),
            771628, 27363861)
    }

    @Test
    fun day07() {
        testSolutions(Day07(), readLines("2023/input07.txt"), 249748283, 248029057)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), readLines("2023/input08.txt"), 20221, 14616363770447)
    }

    @Test
    fun day09() {
        testSolutions(Day09(), readLines("2023/input09.txt"), 1853145119L, 923L)
    }

    @Test
    fun day10() {
        testSolutions(Day10(), readLines("2023/input10.txt"), 7145L, 445)
    }

    @Test
    fun day11() {
        testSolutions(Day11(), readLines("2023/input11.txt"), 9522407L, 544723432977L)
    }

    @Test
    fun day12() {
        testSolutions(Day12(), readLines("2023/input12.txt"), 7402L, 3384337640277L)
    }

    companion object {
        @BeforeAll @JvmStatic
        fun header() {
            banner(2023)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2023)
        }
    }
}
