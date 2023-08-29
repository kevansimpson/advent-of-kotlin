package org.base.advent.k2022

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleSolver

/**
 * <a href="https://adventofcode.com/2022/day/06">Day 06</a>
 */
class Day06 : PuzzleSolver<String> {

    override fun solve1(input: String): Any = findMarker(input, 4)

    override fun solve2(input: String): Any = findMarker(input, 14)

    private fun findMarker(input: String, packetSize: Int): Any = with (input) {
        var marker = -1
        run santa@ {
            indices.forEach {
                val packet = StringUtils.substring(this, it, it + packetSize)
                if (packet.length == packetSize && packet.toCharArray().toSet().size == packetSize) {
                    marker = it + packetSize
                    return@santa
                }
            }
        }
        return marker
    }
}