package org.base.advent.k2015

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2015/day/2">Day 2</a>
 */
class Day02 : PuzzleReader {

    private val input = readLines("2015/input02.txt")

    override fun solve1(): Any = answer.first

    override fun solve2(): Any = answer.second

    private val answer: Pair<Int, Int> by lazy { wrapPresents(input) }

    private fun wrapPresents(lines: List<String>): Pair<Int, Int> =
            lines.map { it.split("x").map { dim -> dim.toInt() }.sorted() }
                    .fold(Pair(0, 0)) { pair, present ->
                        Pair(pair.first + paper(present),
                             pair.second + ribbon(present)) }

    private fun paper(p: Present) =
            (3 * p[0] * p[1]) + (2 * p[1] * p[2]) + (2 * p[2] * p[0])

    private fun ribbon(p: Present) =
            (2 * p[0]) + (2 * p[1]) + /* bow */ (p[0] * p[1] * p[2])
}

typealias Present = List<Int>