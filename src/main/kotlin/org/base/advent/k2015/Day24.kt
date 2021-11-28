package org.base.advent.k2015

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import java.util.concurrent.atomic.AtomicReference

/**
 * <a href="https://adventofcode.com/2015/day/24">Day 24</a>
 */
class Day24 : PuzzleReader, TimeSaver {

    private val input = readLines("2015/input24.txt").map { it.toInt() }

    override fun solve1(): Any = if (fullSolve) solveFor(input, 3) else 11846773891L

    override fun solve2(): Any = solveFor(input, 4)

    private fun solveFor(containers: List<Int>, numCompartments: Int): Long {
        val solution = AtomicReference(Solution())
        findSmallest(containers, numCompartments, solution)
        solve(containers, 0, listOf(), 0, solution)
        return solution.get().lowestQE
    }

    private fun findSmallest(containers: List<Int>, numCompartments: Int, solution: AtomicReference<Solution>) {
        val reversed = containers.reversed()
        val max = 2 shl reversed.size - 1
        val expectedSum = reversed.sum() / numCompartments

        for (i in 0 until max) {
            val ia = toArray(i, reversed)
            if (ia.isEmpty() || ia.sum() != expectedSum) continue

            if (ia.size < solution.get().smallestGroup) {
                solution.set(Solution(ia.size, calcQE(ia), expectedSum))
                return
            }
        }
    }

    private fun solve(containers: List<Int>, index: Int,
                      permutation: List<Int>, currentSum: Int, solution: AtomicReference<Solution>) {
        if (currentSum == solution.get().expectedSum) {
            if (permutation.size == solution.get().smallestGroup) {
                val qe = calcQE(permutation.toIntArray())
                if (qe < solution.get().lowestQE) {
                    solution.set(solution.get().copy(lowestQE = qe))
                }
            }
            return
        }

        if (index >= containers.size || permutation.size >= solution.get().smallestGroup) return

        for (i in containers.indices) {
            val value = containers[i]
            if (permutation.contains(value)) continue
            val next = permutation + value
            solve(containers, 1 + index, next, value + currentSum, solution)
        }
    }

    private fun calcQE(ints: IntArray): Long =
            ints.map { it.toLong() }.reduce { l1, l2 -> l1 * l2 }.toLong()

    private fun toArray(flag: Int, containers: List<Int>): IntArray {
        val set = sortedSetOf<Int>()
        val reverse = StringUtils.reverse(Integer.toBinaryString(flag))
        for (i in reverse.indices)
            if ('1' == reverse[i])
                set.add(containers[i])

        return set.toIntArray()
    }
}

data class Solution(val smallestGroup: Int = Int.MAX_VALUE,
                    val lowestQE: Long = Long.MAX_VALUE,
                    val expectedSum: Int = -1)