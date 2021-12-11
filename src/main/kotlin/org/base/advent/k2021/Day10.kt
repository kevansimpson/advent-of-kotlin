package org.base.advent.k2021

import org.base.advent.PuzzleReader
import java.util.*

/**
 * <a href="https://adventofcode.com/2021/day/10">Day 10</a>
 */
class Day10 : PuzzleReader {

    private val input = readLines("2021/input10.txt")

    override fun solve1(): Any = input.sumOf { evaluate(it) }

    override fun solve2(): Any = middle(input.filter { evaluate(it) == 0 }
            .map { scoreCompletion(it) }.sorted())

    private fun evaluate(line: String): Int {
        val stack = Stack<Char>()
        var last = ' '
        run santa@ {
            line.forEach { ch -> when (ch) {
                ')' -> if (stack.peek() == '(') stack.pop() else {
                    last = ch
                    return@santa
                }
                ']' -> if (stack.peek() == '[') stack.pop() else {
                    last = ch
                    return@santa
                }
                '}' -> if (stack.peek() == '{') stack.pop() else {
                    last = ch
                    return@santa
                }
                '>' -> if (stack.peek() == '<') stack.pop() else {
                    last = ch
                    return@santa
                }
                else -> stack.push(ch)
            } }
        }
        return ERROR_POINTS[last] ?: 0
    }

    private fun middle(list: List<Long>): Long = list[list.size / 2]

    private fun scoreCompletion(line: String): Long {
        val stack = Stack<Char>()

        line.forEach { ch -> when (ch) {
            ')' -> if (stack.peek() == '(') stack.pop()
            ']' -> if (stack.peek() == '[') stack.pop()
            '}' -> if (stack.peek() == '{') stack.pop()
            '>' -> if (stack.peek() == '<') stack.pop()
            else -> stack.push(ch)
        } }
        val buffer = StringBuffer()
        while (stack.isNotEmpty()) {
            buffer.append(SYNTAX_PAIRS[stack.pop()])
        }
        return buffer.toString().fold(0L) { total, ch -> (5L * total) + (COMPLETION_POINTS[ch] ?: 0L) }
    }

    companion object {
        val SYNTAX_PAIRS = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val COMPLETION_POINTS = mapOf(')' to 1L, ']' to 2L, '}' to 3L, '>' to 4L)
        val ERROR_POINTS = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    }
}