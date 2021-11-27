package org.base.advent.k2015

import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2015/day/23">Day 23</a>
 */
class Day23 : PuzzleReader {

    private val input = readLines("2015/input23.txt")

    override fun solve1(): Any = solve(input, 0)

    override fun solve2(): Any = solve(input, 1)

    private fun solve(instructions: List<String>, a: Int, b: Int = 0): Int {
        val registers = mutableMapOf("a" to a, "b" to b)
        followInstructions(registers, instructions, 0)
        return registers["b"] ?: -1
    }

    private fun followInstructions(registers: MutableMap<String, Int>, instructions: List<String>, index: Int) {
        if (index < 0 || index >= instructions.size) return

        val tokens = instructions[index].replace(",", "").split("\\s".toRegex())
        when (tokens[0]) {
            "hlf" -> {
                val hlf = registers[tokens[1]]!!
                registers[tokens[1]] = hlf / 2
                followInstructions(registers, instructions, index + 1)
            }
            "tpl" -> {
                val tpl = registers[tokens[1]]!!
                registers[tokens[1]] = tpl * 3
                followInstructions(registers, instructions, index + 1)
            }
            "inc" -> {
                val inc = registers[tokens[1]]!!
                registers[tokens[1]] = inc + 1
                followInstructions(registers, instructions, index + 1)
            }
            "jmp" -> {
                followInstructions(registers, instructions, index + tokens[1].toInt())
            }
            "jie" -> {
                if ((registers[tokens[1]]!! % 2) == 0)
                    followInstructions(registers, instructions, index + tokens[2].toInt())
                else
                    followInstructions(registers, instructions, index + 1)
            }
            "jio" -> {
                if (registers[tokens[1]]!! == 1)
                    followInstructions(registers, instructions, index + tokens[2].toInt())
                else
                    followInstructions(registers, instructions, index + 1)
            }
        }
    }
}