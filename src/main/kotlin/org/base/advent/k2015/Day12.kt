package org.base.advent.k2015

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleReader
import org.base.advent.util.Extensions.extractInt
import org.json.JSONArray
import org.json.JSONObject

/**
 * <a href="https://adventofcode.com/2015/day/12">Day 12</a>
 */
class Day12 : PuzzleReader {

    private val input = readSingleLine("2015/input12.txt")

    override fun solve1(): Any = sumNumbers(input)

    override fun solve2(): Any = sumJson(JSONObject(input))

    private fun sumNumbers(str: String) = str.extractInt().sum()

    private fun sumJson(obj: JSONObject): Int =
            obj.keySet().fold(0) { total, key ->
                when (val value = obj.get(key)) {
                    is JSONObject -> total + sumJson(value)
                    is JSONArray -> total + sumArray(value)
                    is String -> if (StringUtils.equalsIgnoreCase("red", value)) return 0 else total
                    else -> total + value.toString().toInt()
                }
            }

    private fun sumArray(arr: JSONArray): Int =
            (0 until arr.length()).fold(0) { sum, i ->
                sum + (when (val elem = arr[i]) {
                is JSONObject -> sumJson(elem)
                is JSONArray -> sumArray(elem)
                is String -> 0
                else -> elem.toString().toInt()
            })}
}