package org.base.advent.k2022

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2022/day/01">Day 01</a>
 */
class Day01 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val chunked = input.fold(mutableListOf(mutableListOf<String>())) { acc, line ->
            if (line.isNotBlank()) acc.last().add(line) else acc.add(mutableListOf())
                acc
            }.map { it.sumOf { i -> i.toInt() } }

        return chunked.max() to chunked.sortedDescending().take(3).sum()
    }
}