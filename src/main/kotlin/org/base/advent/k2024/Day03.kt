package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractInt

/**
 * <a href="https://adventofcode.com/2024/day/3">Day 3</a>
 */
class Day03 : PuzzleFunction<String, Pair<Int, Int>> {

    private val mulRegex = Regex("mul\\((\\d+),(\\d+)\\)")
    private val doOrDontRegex = Regex("do\\(\\)|don't\\(\\)")

    override fun apply(input: String): Pair<Int, Int> {
        val mulProducts = intArrayOf(0, 0)
        var doMul = true
        var index = 0

        var m = mulRegex.find(input)
        while (m != null) {
            val matches = doOrDontRegex.findAll(input.substring(index..m.range.last))
            matches.forEach { doDont ->
                run {
                    if (doDont.value == "do()")
                        doMul = true
                    else if (doDont.value == "don't()")
                        doMul = false
                }
            }

            val products = m.value.extractInt()
            val mul = products[0] * products[1]
            mulProducts[0] += mul
            if (doMul)
                mulProducts[1] += mul

            index = m.range.last
            m = mulRegex.find(input, index)
        }

        return mulProducts[0] to mulProducts[1]
    }
}