package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractLong

/**
 * <a href="https://adventofcode.com/2024/day/7">Day 7</a>
 */
class Day07 : PuzzleFunction<List<String>, Pair<Long, Long>> {

    override fun apply(input: List<String>): Pair<Long, Long> {
        val calibrations = arrayOf(0L, 0L)
        val addMultiply = listOf<((Long, Long) -> Long)>(Long::plus, Long::times)
        val withConcatenation = addMultiply.toMutableList().also { it.add(this::concatenate) }

        for (equation in input) {
            val values = equation.extractLong().toLongArray()
            if (canBeCalibrated(values, addMultiply))
                calibrations[0] += values[0]
            if (canBeCalibrated(values, withConcatenation))
                calibrations[1] += values[0]
        }
        return calibrations[0] to calibrations[1]
    }

    private fun canBeCalibrated(values: LongArray, operators: List<((Long, Long) -> Long)>): Boolean {
        for (i in operators.indices) {
            if (canBeCalibrated(values, 0L, 1, i, operators))
                return true
        }
        return false
    }

    private fun canBeCalibrated(values: LongArray, sum: Long, index: Int, op: Int,
                                operators: List<((Long, Long) -> Long)>): Boolean {
        if (sum == values[0] && index == values.size)
            return true
        else if (index < values.size) {
            for (i in operators.indices) {
                val newSum = operators[op].invoke(sum, values[index])
                if (canBeCalibrated(values, newSum, index + 1, i, operators))
                    return true
            }
        }
        return false
    }
    private fun concatenate(a: Long, b: Long): Long = String.format("%d%d", a, b).toLong()
}