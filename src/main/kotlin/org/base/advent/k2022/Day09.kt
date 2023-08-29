package org.base.advent.k2022

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2022/day/09">Day 09</a>
 */
class Day09 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val knots = input.map { with(it.split(" ")) { first() to last().toInt() } }
        return fromInputToPlank(knots, 2) to fromInputToPlank(knots,10)
    }

    private fun fromInputToPlank(input: List<Pair<String, Int>>, knots: Int): Int =
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
