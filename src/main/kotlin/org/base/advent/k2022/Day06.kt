package org.base.advent.k2022

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleReader

/**
 * <a href="https://adventofcode.com/2022/day/06">Day 06</a>
 */
class Day06 : PuzzleReader {

    private val input = readSingleLine("2022/input06.txt")

    override fun solve1(): Any = findMarker(4)

    override fun solve2(): Any = findMarker(14)

    private fun findMarker(packetSize: Int): Any = with (input) {
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