package org.base.advent.k2021

import org.base.advent.PuzzleFunction
import org.base.advent.util.GridReader.flipSpell
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2021/day/13">Day 13</a>
 */
class Day13 : PuzzleFunction<List<String>, Pair<Any, Any>> {
    override fun apply(input: List<String>): Pair<Any, Any> =
        with (TransparentPaper(input)) {
            visibleDots to activationCode
        }

    data class TransparentPaper(val input: List<String>) {
        private val points by lazy {
            input.takeWhile { it.isNotBlank() }.map { it.split(",").map { i -> i.toInt() } }
                .map { pt -> Point(pt[0], pt[1]) }
        }

        private val foldInstructions by lazy {
            input.subList(points.size + 1, input.size).map { it.split(" ").last().split("=") }.map { it[0] to it[1] }
        }

        val visibleDots = foldInstructions(foldInstructions.subList(0, 1))

        /**
         * #### #  #  ##  #  #  ##  #### #  #  ##
         *    # # #  #  # #  # #  # #    #  # #  #
         *   #  ##   #  # #  # #    ###  #  # #   
         *  #   # #  #### #  # #    #    #  # #   
         * #    # #  #  # #  # #  # #    #  # #  #
         * #### #  # #  #  ##   ##  #     ##   ## 
         */
        val activationCode = foldInstructions(foldInstructions, true)


        private fun foldInstructions(folds: List<Pair<String, String>>, show: Boolean = false): Any {
            val visible = folds.fold(points.toSet()) { set, f ->
                val fold = f.second.toInt()
                when (f.first) {
                    "x" -> set.map { pt -> foldLeft(pt, fold) }.toSet()
                    else -> set.map { pt -> foldUp(pt, fold) }.toSet()
                }
            }
            return if (show) flipSpell("Day13, 2021", visible) /* ZKAUCFUC */ else visible.size
        }

        private fun foldLeft(pt: Point, fold: Int): Point = if (pt.x < fold) pt else Point(fold - (pt.x - fold), pt.y)

        private fun foldUp(pt: Point, fold: Int): Point = if (pt.y < fold) pt else Point(pt.x, fold - (pt.y - fold))

    }
}
