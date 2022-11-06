package org.base.advent.k2019

import org.base.advent.PuzzleReader
import org.base.advent.k2019.intCode.Program

/**
 * <a href="https://adventofcode.com/2019/day/02">Day 02</a>
 */
class Day02 : PuzzleReader {

    private val input = readSingleLine("2019/input02.txt").csvToInt()

    override fun solve1(): Any = gravityAssist(12, 2, input)

    override fun solve2(): Any = targetOutput()

    private fun gravityAssist(noun: Int, verb: Int, codes: List<Int>): Int =
            with (Program(codes.toMutableList().apply {
                this[1] = noun
                this[2] = verb
            })) {
                this.call()
                this.result[0]
            }

    private fun targetOutput(target: Int = 19690720, codes: List<Int> = input): Int {
        var answer = -1
        run loop@ {
            (0..100).forEach { noun ->
                    (0..100).forEach { verb ->
                        if (target == gravityAssist(noun, verb, codes)) {
                            answer = 100 * noun + verb
                            return@loop
                        }
                    }
            }
        }
        return answer
    }
}