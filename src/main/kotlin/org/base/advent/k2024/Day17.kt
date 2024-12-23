package org.base.advent.k2024

import org.base.advent.ParallelSolution
import org.base.advent.util.Extensions.extractInt
import org.base.advent.util.Extensions.extractLong
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2024/day/17">Day 17</a>
 */
class Day17(pool: ExecutorService) : ParallelSolution<List<String>>(pool) {
    override fun solve1(input: List<String>): Any = from(input).run()

    override fun solve2(input: List<String>): Any {
        val program = from(input)
        val target = program.opCodes.joinToString(",")
        val answers = mutableListOf<Long>()
        recurse(1L, target, program.opCodes, answers)
        return answers.min()
    }

    private fun recurse(a: Long, target: String, codes: IntArray, answers: MutableList<Long>) {
        for (add in 0L until 8L) {
            val next = add + a
            val p = Program(next, 0L, 0L, codes)
            val out = p.run()
            if (out.length > target.length)
                return

            if (target == out)
                answers.add(next)
            else if (target.endsWith(out))
                recurse(next * 8L, target, codes, answers)
        }
    }

    companion object {
        fun from(input: List<String>): Program =
            Program(input[0].extractLong()[0], input[1].extractLong()[0], input[2].extractLong()[0],
                input[4].extractInt().toIntArray())
    }
}

class Program(var a: Long, var b: Long, var c: Long, val opCodes: IntArray) {
    private val output = mutableListOf<Long>()

    fun run(): String {
        var pointer = 0
        while (pointer < opCodes.size) {
            when (opCodes[pointer]) {
                0 -> {
                    a /= pow(combo(opCodes[pointer + 1]))
                    pointer += 2
                }
                1 -> {
                    b = b xor opCodes[pointer + 1].toLong()
                    pointer += 2
                }
                2 -> {
                    b = combo(opCodes[pointer + 1]) % 8L
                    pointer += 2
                }
                3 -> if (a == 0L) pointer += 2 else pointer = opCodes[pointer + 1]
                4 -> {
                    b = b xor c
                    pointer += 2
                }
                5 -> {
                    output.add(combo(opCodes[pointer + 1]) % 8L)
                    pointer += 2
                }
                6 -> {
                    b = a / pow(combo(opCodes[pointer + 1]))
                    pointer += 2
                }
                7 -> {
                    c = a / pow(combo(opCodes[pointer + 1]))
                    pointer += 2
                }
            }
        }
        return output.joinToString(",") { it.toString() }
    }

    private fun combo(operand: Int): Long =
        when (operand) {
            4 -> a
            5 -> b
            6 -> c
            7 -> throw RuntimeException("Invalid program!")
            else -> operand.toLong()
        }
    private fun pow(exp: Long): Long =
        if (exp == 0L) 1L else 2 * pow(exp - 1L)
}