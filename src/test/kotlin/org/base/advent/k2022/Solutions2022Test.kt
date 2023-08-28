package org.base.advent.k2022

import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2022Test {

    @Test
    fun day01() {
        testSolutions(Day01(), 67633, 199628)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), 13005, 11373)
    }

    @Test
    fun day03() {
        testSolutions(Day03(), 8088, 2522)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), 498, 859)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), "VRWBSFZWM", "RBTWJWMCF")
    }

    @Test
    fun day06() {
        testSolutions(Day06(), 1275, 3605)
    }

    @Test
    fun day07() {
        testSolutions(Day07(), 1334506, 7421137)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), 1779, 172224)
    }

    @Test
    fun day09() {
        testSolutions(Day09(), 5981, 2352)
    }

    @Test
    fun day10() {
        testSolutions(Day10(), 15260, "PGHFGLUG")
    }

    @Test
    fun day11() {
        testSolutions(Day11(), 58056L, 15048718170L)
    }

    @Test
    fun day12() {
        testSolutions(Day12(), 383L, 377L)
    }

    @Test
    fun day13() {
        testSolutions(Day13(), 5555, 22852)
    }

    @Test
    fun day14() {
        testSolutions(Day14(), 737, 28145)
    }

    @Test
    fun day15() {
        testSolutions(Day15(), 5335787L, 13673971349056L)
    }

    @Test
    fun day16() {
        testSolutions(Day16(), 1923, 2594)
    }

    @Test
    fun day17() {
        testSolutions(Day17(), 3124L, 1561176470569L)
    }

    companion object {
        @BeforeAll @JvmStatic
        fun header() {
            banner(2022)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2022)
        }
    }
}
