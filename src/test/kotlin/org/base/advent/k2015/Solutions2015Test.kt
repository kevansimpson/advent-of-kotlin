package org.base.advent.k2015

import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2015Test {

    @Test
    fun day01() {
        testSolutions(Day01(), 74, 1795)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), 1588178, 3783758)
    }

    @Test
    fun day03() {
        testSolutions(Day03(), 2081, 2341)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), 254575L, 1038736L)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), 258, 53)
    }

    @Test
    fun day06() {
        testSolutions(Day06(), 543903, 14687245)
    }

    @Test
    fun day07() {
        testSolutions(Day07(), 46065, 14134)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), 1333, 2046)
    }

    @Test
    fun day09() {
        testSolutions(Day09(), 207, 804)
    }

    @Test
    fun day10() {
        testSolutions(Day10(), 492982, 6989950)
    }

    @Test
    fun day11() {
        testSolutions(Day11(), "vzbxxyzz", "vzcaabcc")
    }

    @Test
    fun day12() {
        testSolutions(Day12(), 111754, 65402)
    }

    @Test
    fun day13() {
        testSolutions(Day13(), 733, 725)
    }

    @Test
    fun day14() {
        testSolutions(Day14(), 2696, 1084)
    }

    @Test
    fun day15() {
        testSolutions(Day15(), 18965440, 15862900)
    }

    companion object {
        @BeforeAll @JvmStatic
        fun header() {
            banner(2015)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2015)
        }
    }
}