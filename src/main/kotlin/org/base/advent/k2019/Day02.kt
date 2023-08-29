package org.base.advent.k2019

import org.base.advent.PuzzleFunction
import org.base.advent.PuzzleReader
import org.base.advent.k2019.intCode.Program

/**
 * <a href="https://adventofcode.com/2019/day/02">Day 02</a>
 */
class Day02 : PuzzleFunction<String, Pair<Int, Int>>, PuzzleReader {
    override fun apply(input: String): Pair<Int, Int> {
        val nums = input.csvToInt()
        return gravityAssist(12, 2, nums) to targetOutput(codes = nums)
    }

    private fun gravityAssist(noun: Int, verb: Int, codes: List<Int>): Int =
            with (Program(codes.toMutableList().apply {
                this[1] = noun
                this[2] = verb
            })) {
                run()
                result.first()
            }

    private fun targetOutput(target: Int = 19690720, codes: List<Int>): Int {
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