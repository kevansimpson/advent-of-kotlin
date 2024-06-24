package org.base.advent.k2023

import org.apache.commons.lang3.Range
import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractLong

/**
 * <a href="https://adventofcode.com/2023/day/05">Day 05</a>
 */
class Day05 : PuzzleFunction<List<String>, Pair<Long, Int>> {

    override fun apply(input: List<String>): Pair<Long, Int> {
        val farm = readAlmanac(input)
        val seeds = farm.seeds.toTypedArray()
        farm.almanac.forEach {
            seeds.forEachIndexed { index, seed ->
                for (pair in it)
                    if (pair.first.contains(seed)) {
                        seeds[index] = (seed - pair.first.first) + pair.second.first
                        break
                    }
            }
        }
        return seeds.min() to 0
    }

    private fun readAlmanac(input: List<String>): Farm {
        val almanac = mutableListOf<List<Pair<LongRange, LongRange>>>()
        var list: MutableList<Pair<LongRange, LongRange>>? = null
        for (i in 2 until input.size) {
            val line = input[i];
            if (line.isBlank()) list = null
            else if (list == null) { // skip title
                list = mutableListOf()
                almanac.add(list)
            }
            else {
                val nums = line.extractLong()
                val len = nums[2]
                list.add(nums[1]until (nums[1] + len) to (nums[0] until (nums[0] + len)))
            }

        }
        return Farm(input[0].extractLong(), almanac)
    }

    data class Farm(val seeds: List<Long>, val almanac: List<List<Pair<LongRange, LongRange>>>)
}