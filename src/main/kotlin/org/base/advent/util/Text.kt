package org.base.advent.util

import org.apache.commons.lang3.StringUtils
import org.base.advent.util.Extensions.md5ToHex
import java.util.*
import java.util.stream.IntStream
import java.util.stream.Stream

object Text {
    private const val ALPHABET = "abcdefghijklmnopqrstuvwxyz"

    val hex2bits by lazy {
        (0..15).associate {
            (if (it >= 10) ALPHABET[it - 10].toString().capitalize() else it.toString()) to
                    StringUtils.leftPad(it.toByte().toString(2), 4, "0")
        }
    }

    val bits2hex by lazy { hex2bits.entries.associate { it.value to it.key } }

    fun nextHash(input: String, prefix: String, start: Long = 1L): Pair<String, Long> {
        for (index in start..Long.MAX_VALUE) {
            val hash = (input + index.toString()).md5ToHex()
            if (StringUtils.startsWith(hash, prefix))
                return Pair(hash, index)
        }
        return Pair(input, -1)
    }

    fun rotateLeft(text: String, rotations: Int): String {
        var str = text
        if (rotations >= text.length) {
            for (i in 0 until rotations)
                str = rotateLeft(str, 1)
            return str
        }
        else
            return "${str.substring(rotations)}${str.substring(0, rotations)}"
    }

    fun rotateRight(text: String, rotations: Int): String {
        var str = text
        if (rotations >= text.length) {
            for (i in 0 until rotations)
                str = rotateRight(str, 1)
            return str
        }
        else
            return rotateLeft(str, str.length - rotations)
    }

    fun shiftText(text: String, shift: Int = 1): String =
        text.toCharArray().map {
            if (ALPHABET.contains(it))
                ALPHABET[(ALPHABET.indexOf(it) + shift) % 26]
            else
                it
        }.joinToString("").replace("-", " ")

    fun columns(rows: List<String>): List<String> = if (rows.isEmpty()) listOf() else
            rows.fold(Array(rows[0].length) { "" }) { list, row ->
                rows[0].indices.forEach { list[it] = list[it] + row[it] }
                list
            }.toList()

    fun columnCounts(columns: List<String>): List<Map<String, Int>> =
            columns.map {
                it.toCharArray().map { ch -> ch.toString() to StringUtils.countMatches(it, ch) }.distinct().toMap()
            }

    private fun String.capitalize() = replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }

    fun splitByBlankLine(lines: List<String>): List<List<String>> {
        val indexes = Stream.of(IntStream.of(-1),
            IntStream.range(0, lines.size).filter { lines[it].isBlank() },
            IntStream.of(lines.size)).flatMapToInt { it }.toArray()
        return IntStream.range(0, indexes.size - 1).mapToObj { lines.subList(indexes[it] + 1, indexes[it + 1]) }.toList()
    }
}