package org.base.advent.k2015

import org.base.advent.PuzzleFunction
import java.util.concurrent.atomic.AtomicInteger

/**
 * <a href="https://adventofcode.com/2015/day/17">Day 17</a>
 */
class Day17 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val cans = input.map { it.toInt() }.toList()
        return with (CanCounter(cans)) {
            sumCans(BooleanArray(cansCount), 0)
            totalPermutations.get() to totalFewest.get()
        }
    }

    data class CanCounter(val cans: List<Int>) {
        val cansCount = cans.size
        val totalPermutations = AtomicInteger(0)
        val totalFewest = AtomicInteger(0)

        internal fun sumCans(permutation: BooleanArray,
                             index: Int,
                             fewest: AtomicInteger = AtomicInteger(Int.MAX_VALUE)) {
            if (index >= cansCount) {
                if (sum(permutation) == 150) {
                    totalPermutations.incrementAndGet()
                    val used = used(permutation)
                    if (used < fewest.get()) {
                        fewest.set(used)
                        totalFewest.set(1)
                    } else if (used == fewest.get()) {
                        totalFewest.incrementAndGet()
                    }
                }
                return
            }

            val off = BooleanArray(cansCount)
            val on = BooleanArray(cansCount)
            System.arraycopy(permutation, 0, off, 0, cansCount)
            System.arraycopy(permutation, 0, on, 0, cansCount)

            sumCans(off, 1 + index, fewest)
            on[index] = true
            sumCans(on, 1 + index, fewest)
        }

        private fun sum(permutation: BooleanArray): Int =
            (0 until cansCount).fold(0) { total, index ->
                total + if (permutation[index]) cans[index] else 0
            }

        private fun used(permutation: BooleanArray): Int =
            (0 until cansCount).count { permutation[it] }
    }
}