package org.base.advent.k2024

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2024/day/11">Day 11</a>
 */
class Day11 : PuzzleFunction<String, Pair<Long, Long>> {

    override fun apply(input: String): Pair<Long, Long> {
        var counter = mutableMapOf<Long, Long>()
        input.split(" ").map { it.toLong() }
            .forEach { mark -> counter[mark] = counter.getOrDefault(mark, 0L) + 1L }

        for (i in 0 until 25)
            counter = blink(counter)
        val blinks25 = counter.values.sum()

        for (i in 0 until 50)
            counter = blink(counter)

        return blinks25 to counter.values.sum()
    }

    private fun blink(stones: Map<Long, Long>): MutableMap<Long, Long> {
        val counter = mutableMapOf<Long, Long>()
        stones.forEach { (stone, count) ->
            if (stone == 0L)
                counter[1L] = counter.getOrDefault(1L, 0L) + count
            else {
                val str = stone.toString()
                if ((str.length % 2) == 0) {
                    val left = str.substring(0, str.length / 2).toLong()
                    val right = str.substring(str.length / 2).toLong()
                    counter[left] = counter.getOrDefault(left, 0L) + count
                    counter[right] = counter.getOrDefault(right, 0L) + count
                }
                else {
                    val mult = 2024 * stone
                    counter[mult] = counter.getOrDefault(mult, 0L) + count
                }
            }
        }
        return counter
    }
}