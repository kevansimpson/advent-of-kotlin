package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2023/day/3">Day 3</a>
 */
class Day03 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> =
        with (readSchematic(input)) {
            val inRange = numbers.filter { inRange(it.key) }.values.sum()
            inRange to 0
        }

    data class Schematic(val numbers: Map<Point, Int>, val symbols: Map<Point, String>) {
        fun inRange(loc: Point): Boolean =
            with (numbers[loc]) {
                (0 until this.toString().length).forEach {
                    val pos = loc.move(it, 0)
                    val sect = symbols.keys.intersect(pos.surrounding().toSet())
                    if (sect.isNotEmpty())
                        return true//.also { println("$this @ $loc/$pos --> $sect") }
                }
                false
            }
    }

    private fun readSchematic(input: List<String>): Schematic {
        val rows = mutableMapOf<Point, Int>()
        val symbols = mutableMapOf<Point, String>()
        input.forEachIndexed { y, str ->
            val nums = numberRegex.findAll(str)
            nums.forEach {
                val n = it.value
                val ix = it.range.first
                rows[Point(ix, y)] = n.toInt()
            }

            val sym = symbolRegex.findAll(str)
            sym.iterator().forEach {
                symbols[Point(it.range.first, y)] = it.value
            }
        }

        return Schematic(rows, symbols)
    }

    companion object {
        val symbolRegex = "[^.\\d]".toRegex()
        val numberRegex = "\\d+".toRegex()
    }
}