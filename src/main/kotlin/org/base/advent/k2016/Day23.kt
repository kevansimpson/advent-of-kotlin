package org.base.advent.k2016

import org.base.advent.PuzzleFunction
import org.base.advent.util.MathUtil.factorial

/**
 * <a href="https://adventofcode.com/2016/day/23">Day 23</a>
 */
class Day23 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val part1 = MonorailComputer(input, mapOf("a" to 7)).operateAssembunnyCode()
        // reddit hints pointed to factorials; never done assembly
        val part2 = part1 + factorial(12).toInt() - factorial(7).toInt()
        return part1 to part2
    }
}