package org.base.advent.k2016

import org.base.advent.ParallelSolution
import org.base.advent.util.Extensions.extractInt
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2016/day/15">Day 15</a>
 */
class Day15(pool: ExecutorService) : ParallelSolution<List<String>>(pool) {

    override fun solve1(input: List<String>): Any = dropCapsules(input.map { makeDisc(it) })

    override fun solve2(input: List<String>): Any {
        val discs = input.map { makeDisc(it) }.toMutableList()
        discs.add(Disc(discs.size, 11, 0, 0))
        return dropCapsules(discs)
    }

    data class Disc(val id: Int, val positions: Int, val time: Int, val atZero: Int) {
        fun discPosition(currentTime: Int) = (atZero + currentTime) % positions
    }

    private fun dropCapsules(discs: List<Disc>): Int {
        val target = discs.size
        val capsules = mutableMapOf<Int, Int>()

        for (time in 0 until 4000000) {
            val current = IntArray(target)
            for (i in 0 until target)
                current[i] = discs[i].discPosition(time)

            val keys = capsules.keys.toMutableSet()
            for (c in keys) {
                val d = capsules[c]!!
                if (current[d] == 0) {
                    val falls = d + 1
                    if (falls == target)
                        return c
                    else
                        capsules[c] = falls
                }
                else
                    capsules.remove(c)
            }
            if (current[0] == 0)
                capsules[time - 1] = 1
        }

        return -1
    }

    private fun makeDisc(input: String): Disc =
        with (input.extractInt()) {
            Disc(this[0], this[1], this[2], this[3])
        }
}