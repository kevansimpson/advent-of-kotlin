package org.base.advent.k2022

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2022/day/01">Day 01</a>
 */
class Day01 : PuzzleReader {

    private val input = readLines("2022/input01.txt")
            .fold(mutableListOf(mutableListOf<String>())) { acc, line ->
                if (line.isNotBlank()) acc.last().add(line) else acc.add(mutableListOf())
                acc
    }

    private val chunked by lazy { input.map { it.sumOf { i -> i.toInt() } } }

    override fun solve1(): Any = chunked.maxOrNull() ?: 1138

    override fun solve2(): Any = chunked.sortedDescending().take(3).sum()
}