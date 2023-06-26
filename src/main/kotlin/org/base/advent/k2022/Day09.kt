package org.base.advent.k2022

import org.base.advent.PuzzleReader
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2022/day/09">Day 09</a>
 */
class Day09 : PuzzleReader {

    private val input =
        readLines("2022/input09.txt")
            .map { with (it.split(" ")) { first() to last().toInt() } }

    override fun solve1(): Any = fromInputToPlank(2)

    override fun solve2(): Any = fromInputToPlank(10)

    private fun fromInputToPlank(knots: Int): Int =
        input.fold(Planck(knots = Array(knots) { Point.ORIGIN }.toList())) { planck, move ->
            movePlanck(planck, move) }.path.size

    private fun movePlanck(planck: Planck, move: Move): Planck =
        with (planck) {
            var (alt, path) = this
            repeat(move.second) {
                alt = alt.drop(1).fold(mutableListOf(alt.first().move(move.first))) { acc, point ->
                    acc.apply {
                        add(follow(acc.last(), point))
                    }
                }
                path.add(alt.last())
            }
            Planck(alt, path)
        }

    private fun follow(head: Point, tail: Point): Point =
            with (head) {
                if (surrounding().contains(tail))
                    tail
                else {
                    tail.move(ix.compareTo(tail.ix), iy.compareTo(tail.iy))
                }
            }
}

typealias Move = Pair<String, Int>

data class Planck(val knots: List<Point> = listOf(Point.ORIGIN, Point.ORIGIN),
                  val path: MutableSet<Point> = mutableSetOf(Point.ORIGIN))
