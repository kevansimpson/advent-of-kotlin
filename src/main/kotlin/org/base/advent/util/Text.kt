package org.base.advent.util

import org.apache.commons.lang3.StringUtils

object Text {
    private const val alphabet = "abcdefghijklmnopqrstuvwxyz"

    fun nextHash(input: String, prefix: String, start: Long = 1L): Pair<String, Long> {
        for (index in start..Long.MAX_VALUE) {
            val hash = (input + index.toString()).md5ToHex()
            if (StringUtils.startsWith(hash, prefix))
                return Pair(hash, index)
        }
        return Pair(input, -1)
    }

    fun shiftText(text: String, shift: Int = 1): String =
        text.toCharArray().map {
            if (alphabet.contains(it))
                alphabet[(alphabet.indexOf(it) + shift) % 26]
            else
                it
        }.joinToString("").replace("-", " ")
}