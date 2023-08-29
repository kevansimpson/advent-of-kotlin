package org.base.advent.k2021

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2021Test : PuzzleReader {

    @Test
    fun day01() {
        testSolutions(Day01(), readIntLines("2021/input01.txt"), 1167, 1130)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), readLines("2021/input02.txt"), 2039912L, 1942068080L)
    }

    @Test
    fun day03() {
        testSolutions(Day03(), readLines("2021/input03.txt"), 3277364L, 5736383L)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), readLines("2021/input04.txt"), 69579, 14877)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), readLines("2021/input05.txt"), 6461, 18065)
    }

    @Test
    fun day06() {
        testSolutions(Day06(), readSingleLine("2021/input06.txt"), 360761L, 1632779838045L)
    }

    @Test
    fun day07() {
        testSolutions(Day07(), readSingleLine("2021/input07.txt").csvToInt(), 335330, 92439766)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), readLines("2021/input08.txt"), 272, 1007675)
    }

    @Test
    fun day09() {
        testSolutions(Day09(), readLines("2021/input09.txt"), 508, 1564640)
    }

    @Test
    fun day10() {
        testSolutions(Day10(), readLines("2021/input10.txt"), 436497, 2377613374L)
    }

    @Test
    fun day11() {
        testSolutions(Day11(), readLines("2021/input11.txt"), 1793, 247)
    }

    @Test
    fun day12() {
        testSolutions(Day12(), readLines("2021/input12.txt"), 3779, 96988)
    }

    @Test
    fun day13() {
        testSolutions(Day13(), readLines("2021/input13.txt"), 731, "ZKAUCFUC")
    }

    @Test
    fun day14() {
        testSolutions(Day14(), readLines("2021/input14.txt"), 3247L, 4110568157153L)
    }

    @Test
    fun day15() {
        testSolutions(Day15(), readLines("2021/input15.txt"), 553, 2858)
    }

    @Test
    fun day16() {
        testSolutions(Day16(), readSingleLine("2021/input16.txt"), 927, 1725277876501L)
    }

    @Test
    fun day17() {
        testSolutions(Day17(), "target area: x=156..202, y=-110..-69", 5995, 3202)
    }

    @Test
    fun day18() {
        testSolutions(Day18(), readLines("2021/input18.txt"), 4145, 4855)
    }

    companion object {
        @BeforeAll @JvmStatic
        fun header() {
            banner(2021)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2021)
        }
    }
}