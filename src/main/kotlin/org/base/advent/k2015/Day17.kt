package org.base.advent.k2015

import org.base.advent.PuzzleReader
import java.util.concurrent.atomic.AtomicInteger

/**
 * <a href="https://adventofcode.com/2015/day/17">Day 17</a>
 */
class Day17 : PuzzleReader {

    private val input = readLines("2015/input17.txt")

    override fun solve1(): Any = totalPermutations.get()

    override fun solve2(): Any = totalFewest.get()

    private val cans by lazy { input.map { it.toInt() }.toTypedArray() }
    private val cansCount by lazy { cans.size }
    private val totalPermutations = AtomicInteger(0)
    private val totalFewest = AtomicInteger(0)

    init {
        sumCans(BooleanArray(cansCount), 0)
    }

    private fun sumCans(permutation: BooleanArray,
                        index: Int,
                        fewest: AtomicInteger = AtomicInteger(Int.MAX_VALUE)) {
        if (index >= cansCount) {
            if (sum(permutation) == 150) {
                totalPermutations.incrementAndGet()
                val used = used(permutation)
                if (used < fewest.get()) {
                    fewest.set(used)
                    totalFewest.set(1)
                }
                else if (used == fewest.get()) {
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