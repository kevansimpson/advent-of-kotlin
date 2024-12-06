package org.base.advent.k2024

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2024/day/5">Day 5</a>
 */
class Day05 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val rulesUpdates = RulesUpdates().readRules(input)
        val answers = intArrayOf(0, 0)
        rulesUpdates.updates.forEach { update ->
            if (inRightOrder(update, rulesUpdates))
                answers[0] += update[update.size / 2]
            else
                answers[1] += reorderUpdate(update, rulesUpdates)
        }

        return answers[0] to answers[1]
    }

    private fun reorderUpdate(update: IntArray, rules: RulesUpdates): Int =
        update.sortedWith(rules)[update.size / 2]

    private fun inRightOrder(update: IntArray, rules: RulesUpdates): Boolean {
        for (i in 0 until update.size - 1)
            for (j in (i + 1) until update.size)
                if (rules.isBefore(update, i, j))
                    return false

        return true
    }

    data class RulesUpdates(val before: MutableMap<Int, MutableList<Int>> = mutableMapOf(),
                            val updates: MutableList<IntArray> = mutableListOf()): Comparator<Int> {
        override fun compare(a: Int, b: Int): Int =
            when {
                isBefore(b, a) -> -1
                a == b -> 0
                else -> 1
            }

        private fun isBefore(i: Int, j: Int): Boolean =
            !before.containsKey(i) || !before.getOrDefault(i, emptyList()).contains(j)

        fun isBefore(update: IntArray, i: Int, j: Int): Boolean =
            !before.containsKey(update[i]) || !before.getOrDefault(update[i], emptyList()).contains(update[j])

        fun readRules(input: List<String>): RulesUpdates {
            var i = 0
            for (line in input) {
                i++
                if (line.isBlank())
                    break
                val rule = line.split("|").map { it.toInt() }.toIntArray()
                if (before.containsKey(rule[0]))
                    before[rule[0]]!!.add(rule[1])
                else
                    before[rule[0]] = mutableListOf(rule[1])
            }

            // updates
            for (j in i until input.size)
                updates.add(input[j].split(",").map { it.toInt() }.toIntArray())

            return this
        }
    }
}