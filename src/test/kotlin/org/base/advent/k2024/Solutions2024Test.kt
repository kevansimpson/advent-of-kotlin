package org.base.advent.k2024

import org.base.advent.PuzzleReader
import org.base.advent.PuzzleTester.banner
import org.base.advent.PuzzleTester.testSolutions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Solutions2024Test : PuzzleReader {

    @Test
    fun day01() {
        testSolutions(Day01(), readLines("2024/input01.txt"), 2057374, 23177084)
    }

    companion object {
        @BeforeAll @JvmStatic
        fun header() {
            banner(2024)
        }

        @AfterAll @JvmStatic
        fun footer() {
            banner(2024)
        }
    }
}
