package org.base.advent.k2016

import org.base.advent.ParallelSolution
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2016/day/12">Day 12</a>
 */
class Day12(pool: ExecutorService) : ParallelSolution<List<String>>(pool) {

    override fun solve1(input: List<String>): Any = operateAssembunnyCode(input, 0)

    override fun solve2(input: List<String>): Any = operateAssembunnyCode(input, 1)

    private fun operateAssembunnyCode(input: List<String>, initialC: Int): Int {
        val register = mutableMapOf("a" to 0, "b" to 0, "c" to initialC, "d" to 0)
        var i = 0
        while (i < input.size) {
            var jump = 1
            val bits = input[i].split(" ")
            when (bits[0]) {
                "cpy" -> register[bits[2]] = register[bits[1]] ?: bits[1].toInt()
                "jnz" ->
                    if ((register[bits[1]] ?: bits[1].toInt()) != 0)
                        jump = bits[2].toInt()
                "inc" -> register[bits[1]] = register[bits[1]]!! + 1
                "dec" -> register[bits[1]] = register[bits[1]]!! - 1
            }
            i += jump
        }

        return register["a"]!!
    }
}