package org.base.advent.k2016

import org.base.advent.ParallelSolution
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2016/day/00">Day 00</a>
 */
class Day19(pool: ExecutorService) : ParallelSolution<Int>(pool) {

    // double the difference b/w count and highest 2^ less than count, add 1
    override fun solve1(input: Int): Any {
        var pow2 = 4096
        while (pow2 * 2 < input)
            pow2 *= 2

        return (input - pow2) * 2 + 1
    }

    // difference b/w count and highest 3^ less than count
    override fun solve2(input: Int): Any {
        var pow3 = 3
        while (pow3 * 3 < input)
            pow3 *= 3

        return input - pow3
    }
}