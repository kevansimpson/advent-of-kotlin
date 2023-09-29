package org.base.advent.k2019

import org.base.advent.PuzzleFunction
import org.base.advent.PuzzleReader
import org.base.advent.k2019.intCode.Program.Companion.runProgram
import java.lang.RuntimeException

/**
 * <a href="https://adventofcode.com/2019/day/02">Day 02</a>
 */
class Day02 : PuzzleFunction<List<Long>, Pair<Long, Long>>, PuzzleReader {
    override fun apply(input: List<Long>): Pair<Long, Long> {
        return gravityAssist(12, 2, input) to targetOutput(codes = input)
    }

    private fun gravityAssist(noun: Long, verb: Long, codes: List<Long>): Long {
        val p = runProgram(codes.toMutableList().apply {
                this[1] = noun
                this[2] = verb
            })
        return p.result[0] ?: throw RuntimeException("gravityAssist: $noun $verb")
    }

    private fun targetOutput(target: Long = 19690720L, codes: List<Long>): Long {
        var answer = -1L
        run loop@ {
            (0L..100L).forEach { noun ->
                    (0L..100L).forEach { verb ->
                        if (target == gravityAssist(noun, verb, codes)) {
                            answer = 100L * noun + verb
                            return@loop
                        }
                    }
            }
        }
        return answer
    }
}