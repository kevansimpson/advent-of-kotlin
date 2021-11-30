package org.base.advent.k2016

import org.base.advent.PuzzleReader
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2016/day/2">Day 2</a>
 */
class Day02 : PuzzleReader {

    private val input = readLines("2016/input02.txt")

    override fun solve1(): Any = follow(input, squarePad)

    override fun solve2(): Any = follow(input, diamondPad)

    private fun follow(directions: List<String>, buttonPad: Map<Point, Char>): String {
        val builder = StringBuilder(directions.size)
        var point = buttonPad.entries.filter { it.value == '5' }.map { it.key }.first()
        for (dir in directions) {
            var start = point
            for (ch in dir.toCharArray()) {
                val next = start.move(ch)
                if (buttonPad.containsKey(next))
                    start = next
            }

            builder.append(buttonPad[start])
            point = start
        }

        return builder.toString()
    }

    companion object {
        private val squarePad: Map<Point, Char> = mapOf(
                Point(-1, 1)    to '1',
                Point(0, 1)     to '2',
                Point(1, 1)     to '3',
                Point(-1, 0)    to '4',
                Point.ORIGIN          to '5',
                Point(1, 0)     to '6',
                Point(-1, -1)   to '7',
                Point(0, -1)    to '8',
                Point(1, -1)    to '9')

        private val diamondPad: Map<Point, Char> = mapOf(
                Point(0, 2)     to '1',
                Point(-1, 1)    to '2',
                Point(0, 1)     to '3',
                Point(1, 1)     to '4',
                Point(-2, 0)    to '5',
                Point(-1, 0)    to '6',
                Point.ORIGIN          to '7',
                Point(1, 0)     to '8',
                Point(2, 0)     to '9',
                Point(-1, -1)   to 'A',
                Point(0, -1)    to 'B',
                Point(1, -1)    to 'C',
                Point(0, -2)    to 'D')
    }
}