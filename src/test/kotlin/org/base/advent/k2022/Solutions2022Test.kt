package org.base.advent.k2022

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2022Test : PuzzleReader {

    @Test
    fun day01() {
        testSolutions(Day01(), readLines("2022/input01.txt"), 67633, 199628)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), readLines("2022/input02.txt"), 13005, 11373)
    }

    @Test
    fun day03() {
        testSolutions(Day03(), readLines("2022/input03.txt"), 8088, 2522)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), readLines("2022/input04.txt"), 498, 859)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), readLines("2022/input05.txt"), "VRWBSFZWM", "RBTWJWMCF")
    }

    @Test
    fun day06() {
        testSolutions(Day06(), readSingleLine("2022/input06.txt"), 1275, 3605)
    }

    @Test
    fun day07() {
        testSolutions(Day07(), readLines("2022/input07.txt"), 1334506, 7421137)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), readLines("2022/input08.txt"), 1779, 172224)
    }

    @Test
    fun day09() {
        testSolutions(Day09(), readLines("2022/input09.txt"), 5981, 2352)
    }

    @Test
    fun day10() {
        testSolutions(Day10(), readLines("2022/input10.txt"), 15260, "PGHFGLUG")
    }

    @Test
    fun day11() {
        testSolutions(Day11(), readLines("2022/input11.txt"), 58056L, 15048718170L)
    }

    @Test
    fun day12() {
        testSolutions(Day12(), readLines("2022/input12.txt"), 383L, 377L)
    }

    @Test
    fun day13() {
        testSolutions(Day13(), readLines("2022/input13.txt"), 5555, 22852)
    }

    @Test
    fun day14() {
        testSolutions(Day14(), readLines("2022/input14.txt"), 737, 28145)
    }

    @Test
    fun day15() {
        testSolutions(Day15(), Day15.BeaconMap(readLines("2022/input15.txt")),
            5335787L, 13673971349056L)
    }

    @Test
    fun day16() {
        testSolutions(Day16(), readLines("2022/input16.txt"), 1923, 2594)
    }

    @Test
    fun day17() {
        testSolutions(Day17(), readSingleLine("2022/input17.txt"), 3124L, 1561176470569L)
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
