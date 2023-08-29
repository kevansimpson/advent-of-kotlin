package org.base.advent.k2022

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2022/day/04">Day 04</a>
 */
class Day04 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val assignmentPairs = input
            .map { it.split(",") }
            .map { ls ->
                ls.map {
                    with(it.split("-").map { i -> i.toInt() }) {
                        (this.first()..this.last())
                    }
                }
            }
            .map { it.first().toList() to it.last().toList() }
        val fullyContained = assignmentPairs.sumOf {
            val (elf1, elf2) = it
            if (elf1.containsAll(elf2) || elf2.containsAll(elf1)) 1.toInt() else 0
            // need `toInt()` due to bug: https://youtrack.jetbrains.com/issue/KT-46360
        }
        val overlap = assignmentPairs.sumOf {
            val (elf1, elf2) = it
            if (elf1.any(elf2::contains) || elf2.any(elf1::contains)) 1.toInt() else 0
        }
        return fullyContained to overlap
    }
}