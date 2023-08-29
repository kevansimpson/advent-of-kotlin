package org.base.advent.k2016

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleFunction
import org.base.advent.util.Text.shiftText

/**
 * <a href="https://adventofcode.com/2016/day/4">Day 4</a>
 */
class Day04 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val roomList = input.map { REGEX.matchEntire(it) }
            .map {
                val (name, sectorId, checksum) = it!!.destructured
                Room(name, sectorId.toInt(), checksum)
            }
        return roomList.filter(this::isReal).sumOf { it.sectorId } to northPoleRoom(roomList)
    }

    private fun northPoleRoom(roomList: List<Room>): Int =
        roomList.map { shiftText(it.name, it.sectorId) to it }
                .first { it.first.contains("northpole") }.second.sectorId

    private fun isReal(room: Room): Boolean =
            room.checksum == StringUtils.substring(
                    room.name.replace("-", "").toCharArray()
                            .map { it.toString() to StringUtils.countMatches(room.name, it) }.distinct().toMap()
                            .entries.sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }
                                    .thenBy { it.key })
                            .joinToString("") { it.key }, 0, 5)

    companion object {
        private val REGEX = "([\\-a-z]+)-(\\d+)\\[([a-z]+)]".toRegex()
    }
}

data class Room(val name: String, val sectorId: Int, val checksum: String)
