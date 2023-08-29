package org.base.advent

import org.base.advent.Duration.DurationHelper.readableTime
import kotlin.test.assertEquals

object PuzzleTester {
    private const val LINE = "\n\t======================="

    fun banner(year: Int) {
        println("$LINE\n\t Advent of Code - $year$LINE\n")
    }

    fun <T> testSolutions(solver: PuzzleSolver<T>, input: T, expected1: Any? = null, expected2: Any? = null) {
        printSolution(solver.name) {
            stopwatch("${solver.name} part1") { assertEquals(expected1, solver.solve1(input)) } +
                    stopwatch("${solver.name} part2") { assertEquals(expected2, solver.solve2(input)) }
        }
    }

    fun <A, B> testSolutions(solver: PuzzleSupplier<Pair<A, B>>,
                             expected1: A? = null,
                             expected2: B? = null) {
        printSolution(solver.name) {
            val result = solver.get()
            assertEquals(expected1, result.first)
            assertEquals(expected2, result.second)
        }
    }

    fun <T, A, B> testSolutions(solver: PuzzleFunction<T, Pair<A, B>>,
                                input: T,
                                expected1: A? = null,
                                expected2: B? = null) {
        printSolution(solver.name) {
            val result = solver.apply(input)
            assertEquals(expected1, result.first)
            assertEquals(expected2, result.second)
        }
    }

    private fun printSolution(solverName: String, test: () -> Unit) {
        val total = stopwatch(null) { test() }
        printTime("$solverName total", Duration(total))
        println("\t-----")
    }

    private fun printTime(name: String, duration: Duration) {
        println("\t$name: ${readableTime(duration)}")
    }

    private fun stopwatch(name: String?, test: () -> Unit): Long {
        val start = System.nanoTime()
        test.invoke()
        val duration = Duration(System.nanoTime() - start)
        name?.let { printTime(it, duration) }
        return duration.time
    }
}