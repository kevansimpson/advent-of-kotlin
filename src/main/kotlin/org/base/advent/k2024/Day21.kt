package org.base.advent.k2024

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleFunction
import org.base.advent.util.Point
import kotlin.collections.Map.Entry
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2024/day/21">Day 21</a>
 */
class Day21 : PuzzleFunction<List<String>, Pair<Long, Long>> {

    override fun apply(input: List<String>): Pair<Long, Long> {
        val numberPad = toMoveMap(toPointMap(NUMERIC_KEYPAD), true)
        val directionalPad = toMoveMap(toPointMap(DIRECTIONAL_KEYPAD), false)
        val cache = mutableMapOf<PathKey, Long>()
        val parts = input.fold(longArrayOf(0L, 0L)) { result, code ->
            val num = code.replace("A", "").toLong()
            val robot1 = firstRobot(code, numberPad)
            result[0] += num * count(robot1, directionalPad, cache, 2)
            result[1] += num * count(robot1, directionalPad, cache, 25)
            result
        }

        return parts[0] to parts[1]
    }

    data class Move(val start: Char, val end: Char)
    data class PathKey(val path: String, val depth: Int)

    private fun firstRobot(code: String, numberPad: Map<Move, String>): String {
        val moves = StringBuilder()
        moves.append(numberPad[Move('A', code[0])]).append('A')
        for (i in 1 until code.length)
            moves.append(numberPad[Move(code[i - 1], code[i])]).append('A')
        return moves.toString()
    }

    private fun count(code: String, directionalPad: Map<Move, String>,
                      cache: MutableMap<PathKey, Long>, depth: Int): Long {
        if (depth == 0)
            return code.length.toLong()
        if ("A" == code)
            return 1L

        val key = PathKey(code, depth)
        if (cache.containsKey(key))
            return cache[key]!!

        var count = 0L
        val list = code.split("A").toMutableList()
        list.removeLast() // Kotlin split ends with empty move; remove it
        list.forEach { m ->
            val moves = StringBuilder()
            val move = "A${m}A"
            for (i in 1 until move.length)
                moves.append(directionalPad[Move(move[i - 1], move[i])]).append('A')
            count += count(moves.toString(), directionalPad, cache, depth - 1)
        }

        cache[key] = count
        return count
    }

    private fun toMoveMap(pts: Map<Char, Point>, isNumberPad: Boolean): Map<Move, String> =
        mutableMapOf<Move, String>().apply {
            pts.entries.forEach { start -> run {
                pts.entries.forEach { end -> run {
                    this[Move(start.key, end.key)] = generateMove(start, end, isNumberPad)
                } }
            } }
        }

    private fun generateMove(start: Entry<Char, Point>, end: Entry<Char, Point>, isNumberPad: Boolean): String {
        val s = start.value
        val e = end.value
        val dx = e.ix - s.ix
        val dy = e.iy - s.iy
        val h = horizontal(dx)
        val v = vertical(dy)
        val hv = h + v
        val vh = v + h

        if (isNumberPad) {
            if (s.iy == 3 && e.ix == 0)
                return vh
            if (s.ix == 0 && e.iy == 3)
                return hv
        }
        else {
            if (s.ix == 0)
                return hv
            if (e.ix == 0)
                return vh
        }

        return if (dx > 0) vh else hv
    }

    private fun horizontal(dx: Int): String =
        if (dx == 0) "" else StringUtils.leftPad("", abs(dx), if (dx < 0) "<" else ">")

    private fun vertical(dy: Int): String =
        if (dy == 0) "" else StringUtils.leftPad("", abs(dy), if (dy < 0) "^" else "v")

    private fun toPointMap(keypad: Array<CharArray>): Map<Char, Point> =
        mutableMapOf<Char, Point>().apply {
            for (y in keypad.indices)
                for (x in keypad[0].indices)
                    if (keypad[y][x] != ' ')
                        this[keypad[y][x]] = Point(x, y)
        }

    companion object {
        val NUMERIC_KEYPAD = arrayOf(
            charArrayOf('7','8','9'), charArrayOf('4','5','6'), charArrayOf('1','2','3'), charArrayOf(' ','0','A'))
        val DIRECTIONAL_KEYPAD = arrayOf(charArrayOf(' ','^','A'), charArrayOf('<','v','>'))
    }
}