package org.base.advent.k2015

import org.base.advent.PuzzleSolver

/**
 * <a href="https://adventofcode.com/2015/day/20">Day 20</a>
 */
class Day20 : PuzzleSolver<Int> {
    override fun solve1(input: Int): Any = lowestHouse(input)

    override fun solve2(input: Int): Any = lowestHouseNewRules(input)

    private fun lowestHouse(input: Int): Int {
        val houses = IntArray(MAX) { 0 }
        for (elf in 1 until MAX)
            for (v in elf until MAX step elf)
                houses[v] += elf * 10

        return targetHouse(input, houses)
    }

    private fun lowestHouseNewRules(input: Int): Int {
        val houses = IntArray(MAX) { 0 }

        for (elf in 1 until MAX) {
            var count = 0
            run santa@ {
                for (v in elf until MAX step elf) {
                    houses[v] += elf * 11
                    count += 1
                    if (count >= 50) return@santa
                }
            }
        }
        return targetHouse(input, houses)
    }

    private fun targetHouse(input: Int, houses: IntArray): Int {
        var answer = 0
        run santa@ {
            for (i in 0 until MAX)
                if (houses[i] >= input) {
                    answer = i
                    return@santa
                }
        }
        return answer
    }

    companion object {
        private const val MAX = 1000000
    }
}