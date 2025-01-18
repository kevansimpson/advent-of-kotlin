package org.base.advent.k2016

import org.apache.commons.lang3.StringUtils
import org.base.advent.ParallelSolution
import org.base.advent.util.Permutations.permutations
import org.base.advent.util.Text.rotateLeft
import org.base.advent.util.Text.rotateRight
import java.util.concurrent.ExecutorService
import kotlin.math.max
import kotlin.math.min

/**
 * <a href="https://adventofcode.com/2016/day/21">Day 21</a>
 */
class Day21(pool: ExecutorService) : ParallelSolution<List<String>>(pool) {

    override fun solve1(input: List<String>): Any = unscramble("abcdefgh", input)

    override fun solve2(input: List<String>): Any {
        val passwords = permutations(listOf('a','b','c','d','e','f','g','h'))
        for (perm in passwords) {
            val pswd = perm.joinToString("")
            if ("fbgdceah" == unscramble(pswd, input))
                return pswd
        }
        return "foobar"
    }

    private fun unscramble(pswd: String, input: List<String>): String {
        var unscrambled = pswd
        for (operation in input) {
            val ops = operation.split(" ")
            unscrambled = when (ops[0]) {
                "swap" ->
                    if ("position" == ops[1]) swapPositions(unscrambled, ops)
                    else swapLetters(unscrambled, ops)
                "rotate" ->
                    when (ops[1]) {
                        "left" -> rotateLeft(unscrambled, ops[2].toInt())
                        "right" -> rotateRight(unscrambled, ops[2].toInt())
                        else -> {
                            val steps = unscrambled.indexOf(ops[6])
                            rotateRight(unscrambled, steps + 1 + (if (steps >= 4) 1 else 0))
                        }
                    }
                "reverse" -> reversePositions(unscrambled, ops)
                else -> move(unscrambled, ops)
            }
        }
        return unscrambled
    }

    private fun move(pswd: String, ops: List<String>): String {
        val p1 = ops[2].toInt()
        val p2 = ops[5].toInt()
        val base = "${pswd.substring(0, p1)}${pswd.substring(p1 + 1)}"
        return "${base.substring(0, p2)}${pswd[p1]}${base.substring(p2)}"
    }

    private fun reversePositions(pswd: String, ops: List<String>): String {
        val p1 = ops[2].toInt()
        val p2 = ops[4].toInt()
        val xy = StringUtils.reverse(pswd.substring(p1, p2 + 1))
        return "${pswd.substring(0, p1)}${xy}${pswd.substring(p2 + 1)}"
    }
    private fun swapLetters(pswd: String, ops: List<String>): String =
        swap(pswd, pswd.indexOf(ops[2]), pswd.indexOf(ops[5]))

    private fun swapPositions(pswd: String, ops: List<String>): String =
        swap(pswd, ops[2].toInt(), ops[5].toInt())

    private fun swap(pswd: String, p1: Int, p2: Int): String {
        val min = min(p1, p2)
        val max = max(p1, p2)
        return "${pswd.substring(0, min)}${pswd[max]}${pswd.substring(min + 1, max)}${pswd[min]}${pswd.substring(max + 1)}"
    }
}