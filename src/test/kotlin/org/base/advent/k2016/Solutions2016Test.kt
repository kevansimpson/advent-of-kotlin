package org.base.advent.k2016

import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2016Test {

    @Test
    fun day01() {
        testSolutions(Day01(), 288L, 111L)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), "76792", "A7AC3")
    }

    @Test
    fun day03() {
        testSolutions(Day03(), 982, 1826)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), 137896, 501)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), "1a3099aa", "694190cd")
    }

    @Test
    fun day06() {
        testSolutions(Day06(), "umcvzsmw", "rwqoacfz")
    }

    @Test
    fun day07() {
        testSolutions(Day07(), 118, 260)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), 116, "UPOJFLBCEZ")
    }
/*
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

    @Test
    fun day16() {
        testSolutions(Day16(), 40, 241)
    }

    @Test
    fun day17() {
        testSolutions(Day17(), 1304, 18)
    }

    @Test
    fun day18() {
        testSolutions(Day18(), 821, 886)
    }

    @Test
    fun day19() {
        testSolutions(Day19(), 509, 195)
    }

    @Test
    fun day20() {
        testSolutions(Day20(), 786240, 831600)
    }

    @Test
    fun day21() {
        testSolutions(Day21(), 111, 188)
    }

    @Test
    fun day22() {
        testSolutions(Day22(), 1824, 1937)
    }

    @Test
    fun day23() {
        testSolutions(Day23(), 307, 160)
    }

    @Test
    fun day24() {
        testSolutions(Day24(), 11846773891L, 80393059L)
    }
*/
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