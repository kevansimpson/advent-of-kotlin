package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import java.util.*

/**
 * <a href="https://adventofcode.com/2024/day/22">Day 22</a>
 */
class Day22 : PuzzleFunction<List<Int>, Pair<Long, Int>> {

    override fun apply(input: List<Int>): Pair<Long, Int> {
        val bananaMap = mutableMapOf<Int, Int>()
        val part1 = input.sumOf { iterate(it.toLong(), bananaMap) }
        return part1 to bananaMap.values.max()
    }

    private fun iterate(secret: Long, bananaMap: MutableMap<Int, Int>): Long {
        var s = secret
        var price = s.toInt() % 10
        val nums = LongArray(2000)
        val deltas = IntArray(2000)
        val allSequences = mutableSetOf<Int>()
        val test = LinkedList<Int>()

        for (i in 0..1999) {
            s = process(s)
            nums[i] = s
            val p = s.toInt() % 10
            deltas[i] = p - price
            test.add(deltas[i])
            price = p

            if (i > 3) {
                test.removeFirst()
                val key = arrayKey(test)
                if (!allSequences.contains(key)) {
                    allSequences.add(key)
                    bananaMap[key] = (bananaMap[key] ?: 0) + price
                }
            }
        }

        return nums[1999]
    }

    private fun process(secret: Long): Long {
        val prune1 = (secret xor secret * 64L) % 16777216L
        val prune2 = (prune1 xor Math.floorDiv(prune1, 32L)) % 16777216L
        return (prune2 xor prune2 * 2048L) % 16777216L
    }

    private fun arrayKey(list: List<Int>): Int {
        var key = (list[0] + 9) * 100000000
        key += (list[1] + 9) * 1000000
        key += (list[2] + 9) * 10000
        key += (list[3] + 9) * 100
        return key
    }
}