package org.base.advent.k2022

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2022/day/04">Day 04</a>
 */
class Day04 : PuzzleReader {

    private val input = readLines("2022/input04.txt")
            .map { it.split(",") }
            .map { ls -> ls.map {
                with (it.split("-").map { i -> i.toInt() }) {
                    (this.first()..this.last())
                } } }
            .map { it.first().toList() to it.last().toList() }

    override fun solve1(): Any = input.sumOf {
        val (elf1, elf2) = it
        if (elf1.containsAll(elf2) || elf2.containsAll(elf1)) 1.toInt() else 0
    }

    // need `toInt()` due to bug: https://youtrack.jetbrains.com/issue/KT-46360

    override fun solve2(): Any = input.sumOf {
        val (elf1, elf2) = it
        if (elf1.any(elf2::contains) || elf2.any(elf1::contains)) 1.toInt() else 0
    }
}