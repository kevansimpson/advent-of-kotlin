package org.base.advent.k2022

import org.base.advent.PuzzleTester.testSolutions
import org.base.advent.k2022.Day13.Companion.parse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Advent2022UnitTest {
    @Test
    fun day13() {
        with (Day13("b")) {
            assertEquals(-2, compareValues(parse("[1,1,3,1,1]"), parse("[1,1,5,1,1]")))
            assertEquals(-2, compareValues(parse("[[1],[2,3,4]]"), parse("[[1],4]")))
            assertEquals(1, compareValues(parse("[9]"), parse("[[8,7,6]]")))
            assertEquals(-1, compareValues(parse("[[4,4],4,4]"), parse("[[4,4],4,4,4]")))
            assertEquals(1, compareValues(parse("[7,7,7,7]"), parse("[7,7,7]")))
            assertEquals(-1, compareValues(parse("[]"), parse("[3]")))
            assertEquals(1, compareValues(parse("[[[]]]"), parse("[[]]")))
            assertEquals(7, compareValues(
                parse("[1,[2,[3,[4,[5,6,7]]]],8,9]"), parse("[1,[2,[3,[4,[5,6,0]]]],8,9]")))

            testSolutions(this, 13, 140)
        }
    }

    /*
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
}
