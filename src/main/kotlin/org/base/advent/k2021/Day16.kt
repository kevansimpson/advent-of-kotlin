package org.base.advent.k2021

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleReader
import org.base.advent.util.Text.bits2hex
import org.base.advent.util.Text.hex2bits

/**
 * <a href="https://adventofcode.com/2021/day/16">Day 16</a>
 */
class Day16 : PuzzleReader {

    private val input = readSingleLine("2021/input16.txt")

    override fun solve1(): Any = packets.sumOf { it.sumVersion() }

    override fun solve2(): Any = packets.sumOf { it.totalValue() }

    private val binary by lazy { toBinary(input) }

    private val packets by lazy { parsePackets(binary) }

    private fun parsePackets(packetBinary: String): List<Packet> {
        val list = mutableListOf<Packet>()
        var bin = packetBinary
        while (bin.replace("0", "").isNotBlank()) {
            val pair = singlePacket(bin)
            list.add(pair.first)
            bin = pair.second
        }
        return list.toList()
    }

    private fun singlePacket(bin: String): Pair<Packet, String> {
        while (bin.isNotBlank()) {
            val version = parseInt(bin.take(3))
            val typeId = parseInt(bin.substring(3).take(3))
            var rest = bin.drop(6)
            when (typeId) {
                4 -> {
                    val literal = parseLiteral(rest)
                    val jump = literal.length.let { it + (it / 4) }
                    return Literal(version, typeId, literal.toLong(2)) to rest.drop(jump)
                }
                else -> {
                    val lengthType = when (rest[0]) {
                        '0' -> 15
                        '1' -> 11
                        else -> throw IllegalStateException("Bad Length Type: ${rest[0]}")
                    }
                    rest = rest.substring(1)
                    val length = rest.take(lengthType).toInt(2)
                    rest = rest.drop(lengthType)
                    return if (lengthType == 15) {
                        val subPackets = parsePackets(rest.take(length))
                        Operator(version, typeId, subPackets) to rest.drop(length)
                    } else {
                        val sub = mutableListOf<Packet>()
                        repeat(length) {
                            val pair = singlePacket(rest)
                            sub.add(pair.first)
                            rest = pair.second
                        }
                        Operator(version, typeId, sub.toList()) to rest
                    }
                }
            }
        }
        throw IllegalStateException("foobar")
    }

    private fun parseInt(bin: String): Int =
            bits2hex[pad(bin)]?.toInt() ?: throw IllegalArgumentException(bin)

    private fun parseLiteral(str: String): String {
        val builder = StringBuilder()
        var has1 = true
        var rest = str
        while (has1) {
            val next = rest.take(5)
            builder.append(next.substring(1))
            rest = rest.drop(5)
            has1 = next.startsWith("1")
        }
        return builder.toString()
    }

    private fun pad(str: String) = StringUtils.leftPad(str, 4, "0")

    private fun toBinary(str: String) = str.toCharArray().map { hex2bits[it.toString()] }.joinToString("")
}

abstract class Packet(val version: Int, val typeId: Int) {
    open fun sumVersion(): Int = version
    abstract fun totalValue(): Long
}

class Literal(version: Int, typeId: Int, val value: Long) : Packet(version, typeId) {
    override fun totalValue(): Long = value
}

class Operator(version: Int, typeId: Int, private val subPackets: List<Packet>) : Packet(version, typeId) {
    override fun sumVersion(): Int = version + subPackets.sumOf { it.sumVersion() }

    override fun totalValue(): Long = when (typeId) {
        0 -> subPackets.sumOf { it.totalValue() }
        1 -> subPackets.map { it.totalValue() }.fold(1L) { total, each -> total * each }
        2 -> subPackets.minOf { it.totalValue() }
        3 -> subPackets.maxOf { it.totalValue() }
        5 -> if (subPackets[0].totalValue() > subPackets[1].totalValue()) 1L else 0L
        6 -> if (subPackets[0].totalValue() < subPackets[1].totalValue()) 1L else 0L
        7 -> if (subPackets[0].totalValue() == subPackets[1].totalValue()) 1L else 0L
        else -> 0L
    }
}