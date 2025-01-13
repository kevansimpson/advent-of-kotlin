package org.base.advent.k2016

import org.apache.commons.lang3.StringUtils.reverse
import org.base.advent.ParallelSolution
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2016/day/16">Day 16</a>
 */
class Day16(pool: ExecutorService) : ParallelSolution<String>(pool) {

    override fun solve1(input: String): Any = curve(input, 272)

    override fun solve2(input: String): Any = curve(input, 35651584)

    private fun curve(data: String, targetLength: Int): String {
        var input = data
        while (input.length < targetLength)
            input = "${input}0${flip(reverse(input))}"

        return checksum(input.substring(0, targetLength))
    }

    private fun checksum(data: String): String =
        with (StringBuilder()) {
            for (i in data.indices step 2)
                append(if (data[i] == data[i + 1]) '1' else '0')
            if ((length % 2) == 0) checksum(toString()) else toString()
        }

    private fun flip(data: String): String =
        with (StringBuilder()) {
            data.toCharArray().forEach { bit -> append(if (bit == '0') '1' else '0') }
            toString()
        }
}