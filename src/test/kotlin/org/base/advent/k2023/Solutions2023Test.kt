package org.base.advent.k2023

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2023Test : PuzzleReader {

    @Test
    fun day01() {
        testSolutions(Day01(), readLines("2023/input01.txt"), 54667, 54203)
    }

    @Test
    fun day02() {
        testSolutions(Day02(), readLines("2023/input02.txt"), 2593, 54699)
    }

    companion object {
        @BeforeAll @JvmStatic
        fun header() {
            banner(2023)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2023)
        }
    }
}
