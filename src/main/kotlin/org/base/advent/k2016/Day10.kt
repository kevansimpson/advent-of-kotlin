package org.base.advent.k2016

import org.base.advent.PuzzleFunction
import java.util.concurrent.ConcurrentHashMap

/**
 * <a href="https://adventofcode.com/2016/day/10">Day 10</a>
 */
class Day10 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val initialBots = input.filter { it.startsWith("value") }
            .map { it.split(" ") }
            .fold(mutableMapOf<String, MutableList<Int>>()) { map, line ->
                val key = "${line[4]}${line[5]}"
                val chip = line[1].toInt()
                if (map.containsKey(key)) map[key]!!.add(chip) else map[key] = mutableListOf(chip)
                map
            }
            .toSortedMap()

        val instructions = input.filter { it.startsWith("bot") }
            .map { it.split(" ") }
            .map { Instruction("${it[0]}${it[1]}", "${it[5]}${it[6]}", "${it[10]}${it[11]}") }
            .associateBy { it.botId }

        val bots = ConcurrentHashMap(initialBots)
        var targetBot = 0
        while (bots.any { it.value.size > 1 }) {
            bots.forEach { bot ->
                if (bot.value?.size == 2) {
                    if (bot.value.containsAll(listOf(17, 61)))
                        targetBot = bot.key.removePrefix("bot").toInt()

                    val ab = bot.value.sorted()
                    bots[bot.key] = mutableListOf()
                    val instruction = instructions[bot.key]!!
                    if (bots.containsKey(instruction.low))
                        bots[instruction.low]!!.add(ab[0])
                    else
                        bots[instruction.low] = mutableListOf(ab[0])
                    if (bots.containsKey(instruction.high))
                        bots[instruction.high]!!.add(ab[1])
                    else
                        bots[instruction.high] = mutableListOf(ab[1])
                }
            }
        }

        return targetBot to bots.filterKeys { REGEX.matches(it) }.map { it.value.first() }
                .fold(1) { total, chip -> total * chip }
    }

    companion object {
        private val REGEX = "output([012])".toRegex()
    }
}

data class Instruction(val botId: String, val low: String, val high: String)
