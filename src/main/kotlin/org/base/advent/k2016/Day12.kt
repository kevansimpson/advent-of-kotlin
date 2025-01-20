package org.base.advent.k2016

import org.apache.commons.lang3.math.NumberUtils.toInt
import org.base.advent.ParallelSolution
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2016/day/12">Day 12</a>
 */
class Day12(pool: ExecutorService) : ParallelSolution<List<String>>(pool) {

    override fun solve1(input: List<String>): Any = MonorailComputer(input, emptyMap()).operateAssembunnyCode()

    override fun solve2(input: List<String>): Any = MonorailComputer(input, mapOf("c" to 1)).operateAssembunnyCode()
}

class MonorailComputer(instructions: List<String>, initial: Map<String, Int>) {
    private val register = mutableMapOf("a" to 0, "b" to 0, "c" to 0, "d" to 0).apply { putAll(initial) }
    private val bitsArray = instructions.map { it.split(" ").toMutableList() }

    fun operateAssembunnyCode(): Int {
        var i = 0
        while (i < bitsArray.size) {
            var jump = 1
            val bits = bitsArray[i]
            when (bits[0]) {
                "cpy" -> register[bits[2]] = register[bits[1]] ?: bits[1].toInt()
                "jnz" ->
                    if ((register[bits[1]] ?: bits[1].toInt()) != 0)
                        jump = toInt(bits[2], register.getOrDefault(bits[2], 1))
                "inc" -> register[bits[1]] = register[bits[1]]!! + 1
                "dec" -> register[bits[1]] = register[bits[1]]!! - 1
                "tgl" -> {
                    val tix = register[bits[1]]!!
                    val toToggle = bitsArray[(i + tix) % bitsArray.size]
                    toToggle[0] = when (toToggle[0]) {
                        "jnz" -> "cpy"
                        "cpy" -> "jnz"
                        "inc" -> "dec"
                        else -> "inc"
                    }
                }
            }
            i += jump
        }

        return register["a"]!!
    }
}