package org.base.advent

import kotlin.test.assertEquals

import kotlinx.coroutines.*
import org.base.advent.Duration.Companion.readableTime
import kotlin.system.measureNanoTime

object PuzzleTester {
    private const val LINE = "\n\t======================="
    private val TIMEOUT: Long = System.getProperty("timeout")?.toLong() ?: 500L

    fun banner(year: Int) {
        println("$LINE\n\t Advent of Code - $year$LINE\n")
    }

    fun testSolutions(solver: PuzzleSolver, expected1: Any? = null, expected2: Any? = null) {
        val total = measureNanoTime {
            runBlocking {
                listOf(
                    async { stopwatch("${solver.name} part1") { assertEquals(expected1, solver.solve1()) } },
                    async { stopwatch("${solver.name} part2") { assertEquals(expected2, solver.solve2()) } }
                ).awaitAll()
            }
        }
        printTime("${solver.name} total", Duration(total))
        println("\t-----")
    }

    private fun printTime(name: String, duration: Duration) {
        println("\t$name: ${readableTime(duration)}")
    }

    private suspend fun stopwatch(name: String, test: () -> Unit) {
        val nanos = withTimeoutOrNull(TIMEOUT) {
            val start = System.nanoTime()
            test.invoke()
            // delay() tricks coroutines into running in parallel
            delay(1L)
            System.nanoTime() - start
        }
        // print duration
        if (nanos == null)
            printTime("$name timeout", Duration(TIMEOUT))
        else
            printTime(name, Duration(nanos))
    }
}