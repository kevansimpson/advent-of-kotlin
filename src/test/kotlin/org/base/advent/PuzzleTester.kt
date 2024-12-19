package org.base.advent

import org.base.advent.Duration.DurationHelper.readableTime
import org.base.advent.ParallelSolution.Companion.START_EVENT
import org.base.advent.ParallelSolution.Companion.STOP_EVENT
import org.base.advent.ParallelSolution.Companion.getParallelKey
import org.base.advent.Part.*
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
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

    fun <T> testParallelSolutions(solver: ParallelSolution<T>, input: T, expected1: Any? = null, expected2: Any? = null) {
        val listener = PuzzleListener(solver.name)
        solver.addPropertyChangeListener(listener)
        val results = solver.apply(input)
        assertEquals(expected1, results.first, solver.name)
        assertEquals(expected2, results.second, solver.name)
        val durations = listener.getDurations()
        val d1 = durations[getParallelKey(solver.name, part1)]!!
        val d2 = durations[getParallelKey(solver.name, part2)]!!
        val dt = durations[getParallelKey(solver.name, total)]!!
        printTime("${solver.name} part1", d1)
        printTime("${solver.name} part2", d2)
        printTime("${solver.name} total", dt)
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

class PuzzleListener(val name: String) : PropertyChangeListener {
    private val startTimes = mutableMapOf<String, Long>()
    private val durations = mutableMapOf<String, Duration>()
    private val latch = CountDownLatch(6)

    override fun propertyChange(evt: PropertyChangeEvent) {
        val key = "${evt.oldValue}"
        when (evt.propertyName) {
            START_EVENT -> startTimes[key] = System.nanoTime()
            STOP_EVENT -> durations[key] = Duration(System.nanoTime() - startTimes[key]!!, TimeUnit.NANOSECONDS)
        }
        latch.countDown()
    }

    fun getDurations(): Map<String, Duration> =
        try {
            latch.await()
            durations
        }
        catch (ex: Exception) {
            throw RuntimeException(name, ex)
        }
}