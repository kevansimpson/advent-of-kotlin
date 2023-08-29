package org.base.advent.k2015

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleSolver

/**
 * <a href="https://adventofcode.com/2015/day/8">Day 8</a>
 */
class Day08 : PuzzleSolver<List<String>> {
    override fun solve1(input: List<String>): Any =
            input.fold(0) { inMem, dir -> inMem + 2 + computeInMemory(chop(dir)) }

    override fun solve2(input: List<String>): Any =
            input.fold(0) { inMem, dir -> inMem + 4 + computeEncrypted(chop(dir)) }

    private fun chop(direction: String): String =
            StringUtils.chop(direction.trim()).substring(1)

    private fun computeInMemory(direction: String): Int =
            direction.toCharArray().fold(Pair(0, 0)) { countFlag, ch ->
                val (count, flag) = countFlag
                when (ch) {
                    '\\' -> if (flag == 1) Pair(count + 1, 0) else Pair(count, 1)
                    '"' -> Pair(if (flag == 1) count + 1 else count, 0)
                    'x' -> Pair(count, if (flag == 1) 2 else 0)
                    '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' ->
                        when (flag) {
                            3 -> Pair(count + 3, 0)
                            2 -> Pair(count, 3)
                            else -> Pair(count, 0)
                        }
                    else -> countFlag
                }
            }.first

    private fun computeEncrypted(direction: String): Int =
            direction.toCharArray().fold(Pair(0, 0)) { countFlag, ch ->
                val (count, flag) = countFlag
                when (ch) {
                    '\\' -> if (flag == 1) Pair(count + 2, 0) else Pair(count, 1)
                    '"' -> Pair(if (flag == 1) count + 2 else count, 0)
                    'x' -> Pair(count, if (flag == 1) 2 else 0)
                    '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' ->
                        when (flag) {
                            3 -> Pair(count + 1, 0)
                            2 -> Pair(count, 3)
                            else -> Pair(count, 0)
                        }
                    else -> countFlag
                }
            }.first
}