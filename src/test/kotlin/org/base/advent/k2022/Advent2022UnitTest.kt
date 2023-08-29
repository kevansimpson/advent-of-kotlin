package org.base.advent.k2022

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.testSolutions
import org.base.advent.k2022.Day13.Companion.parse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Advent2022UnitTest : PuzzleReader {
    @Test
    fun day13() {
        val input = readLines("2022/input13b.txt")
        with (Day13()) {
            assertEquals(-2, compareValues(parse("[1,1,3,1,1]"), parse("[1,1,5,1,1]")))
            assertEquals(-2, compareValues(parse("[[1],[2,3,4]]"), parse("[[1],4]")))
            assertEquals(1, compareValues(parse("[9]"), parse("[[8,7,6]]")))
            assertEquals(-1, compareValues(parse("[[4,4],4,4]"), parse("[[4,4],4,4,4]")))
            assertEquals(1, compareValues(parse("[7,7,7,7]"), parse("[7,7,7]")))
            assertEquals(-1, compareValues(parse("[]"), parse("[3]")))
            assertEquals(1, compareValues(parse("[[[]]]"), parse("[[]]")))
            assertEquals(7, compareValues(
                parse("[1,[2,[3,[4,[5,6,7]]]],8,9]"), parse("[1,[2,[3,[4,[5,6,0]]]],8,9]")))

            testSolutions(this, input, 13, 140)
        }
    }

    @Test
    fun day14() {
        with (Day14()) {
            testSolutions(this, listOf(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9"), 24, 93)
        }
    }

    @Test
    fun day15() {
        testSolutions(Day15(), Day15.BeaconMap(readLines("2022/input15b.txt"), 10L, 0L),
            26L, 56000011L)
    }

    @Test
    fun day16() {
        testSolutions(Day16(), readLines("2022/input16b.txt"), 1651, 1707)
    }
}
