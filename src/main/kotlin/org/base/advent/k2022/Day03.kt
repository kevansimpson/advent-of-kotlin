package org.base.advent.k2022

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2022/day/03">Day 03</a>
 */
class Day03 : PuzzleReader {

    private val input = readLines("2022/input03.txt")

    override fun solve1(): Any = input
            .associate { with(it.length / 2) { it.substring(0, this) to it.substring(this) } }
            .map { c -> (c.key.toCharArray().toSet() intersect c.value.toCharArray().toSet()).first() }
            .sumOf { priority(it) }

    override fun solve2(): Any = input.chunked(3).sumOf {
        val (l1, l2, l3) = it
        priority(((l1.toCharArray() intersect l2.toCharArray().toSet()) intersect l3.toCharArray().toSet()).first())
    }

    private fun priority(c: Char) = if (c.isLowerCase()) c.code - 96 else c.code - 38
}