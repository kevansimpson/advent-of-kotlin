package org.base.advent.k2022

import org.base.advent.PuzzleReader
import org.base.advent.util.Extensions.extractInt
import java.util.*

/**
 * <a href="https://adventofcode.com/2022/day/05">Day 05</a>
 */
class Day05 : PuzzleReader {

    private val input = readLines("2022/input05.txt")

    private val stackLines = input.takeWhile { it.contains("]") }
    private val numStacks: Int = input[stackLines.size].extractInt().maxOrNull()!!
    private val numIndices: Int = numStacks - 1
    private val moves = input.drop(stackLines.size + 2).map { it.extractInt() }

    override fun solve1(): Any = moveCrates { qty, from, to ->
        for (s in 0 until qty)
            if (from.isNotEmpty())
                to.push(from.pop())
    }

    override fun solve2(): Any = moveCrates { qty, from, to ->
        with (Stack<Char>()) {
            for (s in 0 until qty)
                if (from.isNotEmpty()) push(from.pop())

            while (isNotEmpty()) to.push(pop())
        }
    }

    private fun moveCrates(mover: CrateMover): String = with (scanCrates()) {
        moves.forEach { list ->
            val (qty, from, to) = list
            mover(qty, this[from - 1], this[to - 1])
        }
        map { it.peek()!! }.joinToString("")
    }

    private fun scanCrates(): List<Stack<Char>> = (0..numIndices).fold(mutableListOf()) { list, stackIndex ->
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