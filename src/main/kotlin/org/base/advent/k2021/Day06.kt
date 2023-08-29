package org.base.advent.k2021

import org.base.advent.PuzzleFunction
import org.base.advent.PuzzleReader
import java.util.*

/**
 * <a href="https://adventofcode.com/2021/day/6">Day 6</a>
 */
class Day06 : PuzzleFunction<String, Pair<Long, Long>>, PuzzleReader {
    override fun apply(input: String): Pair<Long, Long> {
        val fish = input.csvToInt()
        return fish.chunked(5).sumOf { simulateLanternFish(it) }.toLong() to timedLanternFish(fish)
    }

    // from Reddit, adapted from https://github.com/Praful/advent_of_code/blob/main/2021/src/day06.jl
    private fun timedLanternFish(fishList: List<Int>, days: Int = 256): Long {
        val fish = (0..9).map { index -> fishList.count { index == it }.toLong() }.toLongArray()
        for (day in 1..days) {
            val newFish = fish[9]
            fish[9] = fish[1]
            for (c in 1..7)
                fish[c] = fish[c + 1]
            fish[6] += newFish
            fish[8] = newFish
        }
        return fish.sum()
    }

    // my solution, doesn't scale to 256 days
    private fun simulateLanternFish(fishList: List<Int>, days: Int = 80): Int =
            (0 until days).fold(fishList.toMutableList()) { list, _ -> nextList(list)
        }.size

    private fun nextList(list: List<Int>): MutableList<Int> {
        var add8 = 0
        val next = list.indices.fold(LinkedList<Int>()) { list2, index ->
            val fish = list[index]
            if (fish > 0) {
                list2.add(fish - 1)
                list2
            }
            else {
                list2.add(6)
                add8 += 1
                list2
            }
        }
        (0 until add8).forEach { _ -> next.add(8) }
        return next.toMutableList()
    }
}