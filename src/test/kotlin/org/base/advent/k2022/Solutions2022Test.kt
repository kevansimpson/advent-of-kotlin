package org.base.advent.k2022

import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.base.advent.util.Point
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

    /*
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

    @Test
    fun day16() {
        testSolutions(Day16(), 927, 1725277876501L)
    }

    @Test
    fun day17() {
        testSolutions(Day17(), 5995, 3202)
    }

    @Test
    fun day18() {
        testSolutions(Day18(), 4145, 4855)
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
            banner(2022)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2022)
        }
    }
}
