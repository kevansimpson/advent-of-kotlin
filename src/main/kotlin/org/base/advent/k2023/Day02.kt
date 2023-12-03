package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import kotlin.math.max


/**
 * <a href="https://adventofcode.com/2023/day/2">Day 2</a>
 */
class Day02 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val games = input.map { parse(it) }
        val sumGames = games.filter { it.reveals.all { r -> r.only12R13G14B() } }.sumOf { it.id }
        val power = games.map {
            it.reveals.fold(Reveal()) { pwr, r ->
                Reveal(max(pwr.red, r.red), max(pwr.green, r.green), max(pwr.blue, r.blue))
            }
        }.sumOf { it.power() }
        return sumGames to power
    }

    internal fun parse(str: String): Cubes =
        REGEX.matchEntire(str)?.let { Cubes(it.groupValues[1].toInt(), revelio(it.groupValues[2])) } ?: throw IllegalStateException(str)

    private fun revelio(str: String): List<Reveal> =
        str.split("; ").map { c ->
            val cubes = c.split(", ").map { it.split(" ") }.associate { it[1] to it[0].toInt() }
            Reveal(cubes["red"] ?: 0, cubes["green"] ?: 0, cubes["blue"] ?: 0)
        }.toList()

    data class Cubes(val id: Int, val reveals: List<Reveal>)

    data class Reveal(val red: Int = 0, val green: Int = 0, val blue: Int = 0) {
        fun only12R13G14B(): Boolean = red <= 12 && green <= 13 && blue <= 14
        fun power(): Int = red * green * blue
    }

    companion object {
        private val REGEX = "Game (\\d{1,3}): (.+)".toRegex()
    }
}