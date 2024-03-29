package org.base.advent.k2015

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.math.NumberUtils
import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2015/day/7">Day 7</a>
 */
class Day07 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val circuitMap = input.map { REGEX.matchEntire(it) }
            .associate {
                val (values, key) = it!!.destructured
                Pair(key, values.split("\\s".toRegex()))
            }
        return HashMap<String, Int>().calculate(circuitMap, "a") to
                HashMap(mapOf(Pair("b", 46065))).calculate(circuitMap, "a")
    }

    private fun HashMap<String, Int>.calculate(circuitMap: CircuitMap, wire: String): Int {
        if (!containsKey(wire)) {
            val logic: List<String> = circuitMap[wire] ?: return wire.toInt()

            when (logic.size) {
                1 -> put(wire,
                        if (NumberUtils.isDigits(logic[0])) logic[0].toInt()
                        else calculate(circuitMap, logic[0]))
                2 -> if (StringUtils.equals("NOT", logic[0])) {
                         put(wire, (65535 - calculate(circuitMap, logic[1])))
                     }
                3 ->
                    put(wire, when (logic[1]) {
                        "AND" ->
                            if (NumberUtils.isDigits(logic[2]))
                                calculate(circuitMap, logic[0]) and logic[0].toInt()
                            else
                                calculate(circuitMap, logic[0]) and calculate(circuitMap, logic[2])
                        "OR" ->
                            calculate(circuitMap, logic[0]) or calculate(circuitMap, logic[2])
                        "LSHIFT" ->
                            calculate(circuitMap, logic[0]) shl calculate(circuitMap, logic[2])
                        "RSHIFT" ->
                            calculate(circuitMap, logic[0]) shr calculate(circuitMap, logic[2])
                        else ->
                            throw IllegalStateException(logic[1])
                    })
            }
        }
        return getOrDefault(wire, -1)
    }

    companion object {
        private val REGEX = "(.+)\\s->\\s([a-z]+)".toRegex()
    }
}

typealias CircuitMap = Map<String, List<String>>
