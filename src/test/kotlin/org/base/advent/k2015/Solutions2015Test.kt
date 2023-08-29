package org.base.advent.k2015

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.base.advent.util.Point
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2015Test : PuzzleReader {

    @Test
    fun day01() {
        testSolutions(Day01(), readSingleLine("2015/input01.txt"),74, 1795)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), readLines("2015/input02.txt"), 1588178, 3783758)
    }

    @Test
    fun day03() {
        testSolutions(Day03(), readSingleLine("2015/input03.txt"), 2081, 2341)
    }

    @Test
    fun day04() {
        testSolutions(Day04(), "bgvyzdsv", 254575L, 1038736L)
    }

    @Test
    fun day05() {
        testSolutions(Day05(), readLines("2015/input05.txt"), 258, 53)
    }

    @Test
    fun day06() {
        testSolutions(Day06(), readLines("2015/input06.txt"), 543903, 14687245)
    }

    @Test
    fun day07() {
        testSolutions(Day07(), readLines("2015/input07.txt"), 46065, 14134)
    }

    @Test
    fun day08() {
        testSolutions(Day08(), readLines("2015/input08.txt"), 1333, 2046)
    }

    @Test
    fun day09() {
        testSolutions(Day09(), readLines("2015/input09.txt"), 207, 804)
    }

    @Test
    fun day10() {
        testSolutions(Day10(), "1321131112", 492982, 6989950)
    }

    @Test
    fun day11() {
        testSolutions(Day11(), "vzbxkghb", "vzbxxyzz", "vzcaabcc")
    }

    @Test
    fun day12() {
        testSolutions(Day12(), readSingleLine("2015/input12.txt"), 111754, 65402)
    }

    @Test
    fun day13() {
        testSolutions(Day13(), readLines("2015/input13.txt"), 733, 725)
    }

    @Test
    fun day14() {
        testSolutions(Day14(), readLines("2015/input14.txt"), 2696, 1084)
    }

    @Test
    fun day15() {
        testSolutions(Day15(), readLines("2015/input15.txt"), 18965440, 15862900)
    }

    @Test
    fun day16() {
        testSolutions(Day16(), readLines("2015/input16.txt"), 40, 241)
    }

    @Test
    fun day17() {
        testSolutions(Day17(), readLines("2015/input17.txt"), 1304, 18)
    }

    @Test
    fun day18() {
        testSolutions(Day18(), readLines("2015/input18.txt"), 821, 886)
    }

    @Test
    fun day19() {
        testSolutions(Day19(), readLines("2015/input19.txt"), 509, 195)
    }

    @Test
    fun day20() {
        testSolutions(Day20(), 34000000, 786240, 831600)
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
        testSolutions(Day23(), readLines("2015/input23.txt"), 307, 160)
    }

    @Test
    fun day24() {
        testSolutions(Day24(), readLines("2015/input24.txt"), 11846773891L, 80393059L)
    }

    @Test
    fun day25() {
        testSolutions(Day25(), Point(2978L, 3083L), 2650453L, 1138)
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