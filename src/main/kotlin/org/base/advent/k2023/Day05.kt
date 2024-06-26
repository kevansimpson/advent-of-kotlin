package org.base.advent.k2023

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractLong

/**
 * <a href="https://adventofcode.com/2023/day/5">Day 05</a>
 */
class Day05 : PuzzleFunction<List<String>, Pair<Long, Long>> {

    override fun apply(input: List<String>): Pair<Long, Long> {
        val farm = readAlmanac(input)
        val part1 = farm.seeds.minOf { mapSeeds(it..it, farm) }
        val ranges = mutableListOf<LongRange>()
        for (i in farm.seeds.indices step 2) {
            ranges.add(farm.seeds[i]until(farm.seeds[i] + farm.seeds[i + 1]))
        }
        val part2 = ranges.map { r -> mapSeeds(r, farm) }.filter { it > 0 }.min()
        return part1 to part2
    }

    private fun mapSeeds(seeds: LongRange, farm: Farm): Long {
        var list = mutableListOf(seeds)
        farm.almanac.forEach {
            val next = mutableListOf<LongRange>()
            list.forEach { seedRange ->
                for (mapping in it) {
                    if (mapping.src.contains(seedRange.first)) {
                        if (mapping.src.contains(seedRange.last)) {
                            next.add((seedRange.first() + mapping.delta)..(seedRange.last() + mapping.delta))
                        }
                        else {
                            next.add((seedRange.first + mapping.delta)..(mapping.src.last + mapping.delta))
                            next.add((mapping.src.last + 1L)..seedRange.last)
                        }
                        break
                    }
                    else if (mapping.src.contains(seedRange.last)) {
                        next.add(seedRange.first until mapping.src.first)
                        next.add((mapping.src.first + mapping.delta)..(seedRange.last + mapping.delta))
                        break
                    }
                    else if (seedRange.contains(mapping.src.first)) {
                        if (seedRange.contains(mapping.src.last)) {
                            next.add(seedRange.first until mapping.src.first)
                            next.add(mapping.src.first + mapping.delta..mapping.src.last + mapping.delta)
                            next.add(mapping.src.last + 1L..seedRange.last)
                            break
                        }
                    }
                }
            }
            if (next.isNotEmpty()) list = next
        }

        return list.minOf { it.first }
    }

    private fun readAlmanac(input: List<String>): Farm {
        val almanac = mutableListOf<List<Mapping>>()
        var list: MutableList<Mapping>? = null
        for (i in 2 until input.size) {
            val line = input[i]
            if (line.isBlank()) list = null
            else if (list == null) { // skip title
                list = mutableListOf()
                almanac.add(list)
            }
            else {
                val nums = line.extractLong()
                list.add(Mapping(nums[1]until (nums[1] + nums[2]), nums[0] - nums[1]))
            }

        }
        return Farm(input[0].extractLong(), almanac)
    }

    data class Farm(val seeds: List<Long>, val almanac: List<List<Mapping>>)
    data class Mapping(val src: LongRange, val delta: Long)
}