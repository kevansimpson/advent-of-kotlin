package org.base.advent.k2021

import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2021Test {

    @Test
    fun day01() {
        testSolutions(Day01(), 1167, 1130)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), 2039912L, 1942068080L)
    }

    @Test
    fun day03() {
        testSolutions(Day03(), 3277364L, 5736383L)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), 69579, 14877)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), 6461, 18065)
    }

    @Test
    fun day06() {
        testSolutions(Day06(), 360761L, 1632779838045L)
    }

    @Test
    fun day07() {
        testSolutions(Day07(), 335330, 92439766)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), 272, 1007675)
    }

    @Test
    fun day09() {
        testSolutions(Day09(), 508, 1564640)
    }

    @Test
    fun day10() {
        testSolutions(Day10(), 436497, 2377613374L)
    }

    @Test
    fun day11() {
        testSolutions(Day11(), 1793, 247)
    }

    @Test
    fun day12() {
        testSolutions(Day12(), 3779, 96988)
    }

    @Test
    fun day13() {
        testSolutions(Day13(), 731, "ZKAUCFUC")
    }

    @Test
    fun day14() {
        testSolutions(Day14(), 3247L, 4110568157153L)
    }

    @Test
    fun day15() {
        testSolutions(Day15(), 553, 2858)
    }
/*
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