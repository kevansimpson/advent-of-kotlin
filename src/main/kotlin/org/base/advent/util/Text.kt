package org.base.advent.util

object Text {
    private const val alphabet = "abcdefghijklmnopqrstuvwxyz"

    fun shiftText(text: String, shift: Int = 1): String =
        text.toCharArray().map {
            if (alphabet.contains(it))
                alphabet[(alphabet.indexOf(it) + shift) % 26]
            else
                it
        }.joinToString("").replace("-", " ")
}