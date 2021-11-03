package org.base.advent

import org.base.advent.Duration.DurationHelper.readableTime
import kotlin.test.assertEquals

object PuzzleTester {
    private const val LINE = "\n\t======================="

    fun banner(year: Int) {
        println("$LINE\n\t Advent of Code - $year$LINE\n")
    }

    fun testSolutions(solver: PuzzleSolver, expected1: Any? = null, expected2: Any? = null) {
        val total =
            stopwatch("${solver.name} part1") { assertEquals(expected1, solver.solve1()) } +
            stopwatch("${solver.name} part2") { assertEquals(expected2, solver.solve2()) }
        printTime("${solver.name} total", Duration(total))
        println("\t-----")
    }

    private fun printTime(name: String, duration: Duration) {
        println("\t$name: ${readableTime(duration)}")
    }

    private fun stopwatch(name: String, test: () -> Unit): Long {
        val start = System.nanoTime()
        test.invoke()
        val duration = Duration(System.nanoTime() - start)
        printTime(name, duration)
        return duration.time
    }
}