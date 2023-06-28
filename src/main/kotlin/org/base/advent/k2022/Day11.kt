package org.base.advent.k2022

import org.base.advent.PuzzleReader
import org.base.advent.util.Extensions.extractInt
import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicLong

/**
 * <a href="https://adventofcode.com/2022/day/11">Day 11</a>
 */
class Day11 : PuzzleReader {

    private val input = readLines("2022/input11.txt")

    override fun solve1(): Any =
        with (takeMonkeyNotes(input)) {
            play20Rounds(this)
            mostActive(this)
        }

    override fun solve2(): Any =
        with (takeMonkeyNotes(input)) {
            (1..10000).forEach { _ ->
                this.forEach { m -> quickMonkey(m, this) }
            }
            mostActive(this)
        }

    private fun divisorProduct(monkeys: List<Monkey>): Long =
        monkeys.map { it.test }.fold(1L) { a, b -> a * b }

    // https://jactl.io/blog/2023/04/17/advent-of-code-2022-day11.html
    private fun quickMonkey(monkey: Monkey, allMonkeys: List<Monkey>) =
        with (monkey) {
            val next = items.toList()
            items.clear()
            next.forEach { item ->
                totalInspected.incrementAndGet()
                val worry = evalOperation(item) % divisorProduct(allMonkeys)
                if (worry % test == 0L)
                    allMonkeys[testPass].items.add(worry)
                else
                    allMonkeys[testFail].items.add(worry)
            }
    }

    private fun play20Rounds(monkeys: List<Monkey>) =
        (1..20).forEach { _ ->
            monkeys.forEach { it.takeTurn(monkeys) }
        }

    private fun mostActive(monkeys: List<Monkey>): Long =
        with (monkeys) {
            val active = sortedByDescending { it.totalInspected.get() }
            active[0].totalInspected.get() * active[1].totalInspected.get()
        }

    private fun takeMonkeyNotes(notes: List<String>): List<Monkey> =
        notes.chunked(7) { lines ->
            Monkey(lines[0].extractInt().first(),
                lines[1].extractInt().map { it.toLong() }.toMutableList(),
                lines[2].substringAfter("new = "),
                lines[3].extractInt().first().toLong(),
                lines[4].extractInt().first(),
                lines[5].extractInt().first())
        }
}

data class Monkey(val position: Int,
                  val items: MutableList<Long> = mutableListOf(),
                  val operation: String,
                  val test: Long,
                  val testPass: Int,
                  val testFail: Int,
                  val totalInspected: AtomicLong = AtomicLong(0L)
) {

    fun takeTurn(allMonkeys: List<Monkey>) =
        items.forEach { worry ->
            totalInspected.incrementAndGet()
            val current = evalOperation(worry) / 3L
            if (current % test == 0L)
                allMonkeys[testPass].items.add(current)
            else
                allMonkeys[testFail].items.add(current)
        }.also { items.clear() }

    fun evalOperation(worry: Long): Long =
        with (operation.split(" ")) {
            val l = eval(worry, this[0])
            val r = eval(worry, this[2])
            when (val op = this[1]) {
                "+" -> l + r
                "*" -> l * r
                else -> throw IllegalStateException(op)
            }
        }

    private fun eval(worry: Long, operand: String): Long =
        if (operand == "old") worry else operand.toLong()
}
