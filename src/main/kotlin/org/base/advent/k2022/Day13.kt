package org.base.advent.k2022

import org.base.advent.PuzzleFunction
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * <a href="https://adventofcode.com/2022/day/13">Day 13</a>
 */
@Suppress("UNCHECKED_CAST")
class Day13 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val nested = input.filter { it.isNotBlank() }.map { parse(it) }

        val indicesSum = with(AtomicInteger(0)) {
                val stack = nested.toMutableList()
                for (i in 0 until stack.indices.max() step 2) {
                    if (compareValues(stack[i], stack[i + 1]) < 0)
                        addAndGet((i + 2) / 2)
                }

                get()
            }
        val decoderKey =
            with(PART2_SIGNALS.map { parse(it) }.plus(nested)) {
                val sorted = this.sortedWith { a, b -> compareValues(a, b) }.map { it.toString() }
                val ix1 = sorted.indexOf("[[2]]") + 1
                val ix2 = sorted.indexOf("[[6]]") + 1
                ix1 * ix2
            }

        return indicesSum to decoderKey
    }

    fun compareValues(left: List<Any>, right: List<Any>): Int {
        val lit = left.iterator()
        val rit = right.iterator()
        while (lit.hasNext() && rit.hasNext()) {
            val comp = compareValues(lit.next(), rit.next())
            if (comp != 0)
                return comp
        }
        return when {
            !lit.hasNext() -> if (rit.hasNext()) -1 else 0
            else -> 1
        }
    }

    private fun compareValues(left: Any, right: Any): Int =
        when (left) {
            is Int -> when (right) {
                is Int -> left - right
                else -> compareValues(listOf(left), right as List<Any>)
            }
            else -> when (right) {
                is Int -> compareValues(left as List<Any>, listOf(right))
                else -> compareValues(left as List<Any>, right as List<Any>)
            }
        }

    companion object {
        private val PART2_SIGNALS = listOf("[[2]]", "[[6]]")

        fun parse(nestedList: String): List<Any> {
            val stack = Stack<MutableList<Any>>().apply { push(mutableListOf()) }
            var str = ""
            for (ix in nestedList.indices) {
                when (val ch = nestedList[ix]){
                    '[' -> stack.push(mutableListOf())
                    ',' ->
                        if (str.isNotBlank())
                            stack.peek().add(str.toInt()).also { str = "" }
                    ']' -> {
                        val top = stack.pop()
                        if (str.isNotBlank())
                            top.add(str.toInt()).also { str = "" }
                        stack.peek().add(top)
                    }
                    else -> str += ch
                }
            }

            return stack.peek()[0] as List<Any> //.also { it.forEach { n -> println(n.inspect()) } }
        }
    }
}
