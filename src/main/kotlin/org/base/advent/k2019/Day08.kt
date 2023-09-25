package org.base.advent.k2019

import org.apache.commons.lang3.StringUtils.countMatches
import org.base.advent.PuzzleSolver

/**
 * <a href="https://adventofcode.com/2019/day/8">Day 8</a>
 */
class Day08 : PuzzleSolver<String> {
    override fun solve1(input: String): Any = zeroLayer(input)

    override fun solve2(input: String): Any = drawImage(input)

    private fun zeroLayer(image: String, altWidth: Int? = null, altHeight: Int? = null): Int =
        with (toLayers(image, altWidth, altHeight).minBy { countMatches(it, "0") }) {
            (countMatches(this, "1") * countMatches(this, "2"))
        }

    private fun drawImage(image: String, altWidth: Int? = null, altHeight: Int? = null): String =
        with (toLayers(image, altWidth, altHeight)) {
            val decoded = this[0].toCharArray()
            for (i in 1 until size) {
                val layer = this[i]
                for (p in layer.indices) {
                    if (decoded[p] == '2')
                        decoded[p] = layer[p]
                }
            }

            String(decoded)
        }


    private fun toLayers(image: String, altWidth: Int? = null, altHeight: Int? = null): List<String> {
        val w = altWidth ?: 25
        val h = altHeight ?: 6
        return image.split(("(?<=\\G.{" + w * h + "})").toRegex()).filter { it.isNotBlank() }
    }
}
