package org.base.advent.k2022

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractInt
import java.util.*

/**
 * <a href="https://adventofcode.com/2022/day/05">Day 05</a>
 */
class Day05 : PuzzleFunction<List<String>, Pair<String, String>> {
    override fun apply(input: List<String>): Pair<String, String> {
        val stackLines = input.takeWhile { it.contains("]") }
        val numStacks: Int = input[stackLines.size].extractInt().maxOrNull()!!
        val numIndices: Int = numStacks - 1
        val moves = input.drop(stackLines.size + 2).map { it.extractInt() }
        val onTop1 = moveCrates(moves, numIndices, stackLines) { qty, from, to ->
            for (s in 0 until qty)
                if (from.isNotEmpty())
                    to.push(from.pop())
        }
        val onTop2 = moveCrates(moves, numIndices, stackLines) { qty, from, to ->
            with (Stack<Char>()) {
                for (s in 0 until qty)
                    if (from.isNotEmpty()) push(from.pop())

                while (isNotEmpty()) to.push(pop())
            }
        }
        return onTop1 to onTop2
    }

    private fun moveCrates(moves: List<List<Int>>,
                           numIndices: Int,
                           stackLines: List<String>,
                           mover: CrateMover): String =
        with (scanCrates(numIndices, stackLines)) {
            moves.forEach { list ->
                val (qty, from, to) = list
                mover(qty, this[from - 1], this[to - 1])
            }
            map { it.peek()!! }.joinToString("")
        }

    private fun scanCrates(numIndices: Int, stackLines: List<String>): List<Stack<Char>> =
        (0..numIndices).fold(mutableListOf()) { list, stackIndex ->
            list.apply { add(Stack<Char>().apply {
                val column = 1 + stackIndex * 4
                for (i in (stackLines.size - 1) downTo 0) {
                    with (stackLines[i]) {
                        if (length >= column && this[column] != ' ')
                            push(this[column])
                    }
                }
            }) }
        }
}

typealias CrateMover = (Int, Stack<Char>, Stack<Char>) -> Unit
