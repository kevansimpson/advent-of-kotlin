package org.base.advent.util

import java.security.MessageDigest

object Extensions {

    private fun ByteArray.toHex(): String =
            joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

    infix fun Int.toward(to: Int): IntProgression {
        val step = if (this > to) -1 else 1
        return IntProgression.fromClosedRange(this, to, step)
    }

    infix fun Long.toward(to: Long): LongProgression {
        val step = if (this > to) -1L else 1L
        return LongProgression.fromClosedRange(this, to, step)
    }

    fun LongRange.merge(that: LongRange): Pair<LongRange, LongRange?> =
        if (this.first <= that.first)
            when {
                this.last >= that.last -> this to null // this contains that
                this.last < that.first -> this to that // no overlap
                else -> this.first..that.last to null // this + that
            }
        else
            when {
                this.last <= that.last -> that to null // that contains this
                this.first > that.last -> that to this // no overlap
                else -> that.first..this.last to null // that + this
            }

    fun LongRange.size(): Long = this.last - this.first + 1L

    fun String.md5ToHex() =
            MessageDigest.getInstance("MD5").digest(this.toByteArray()).toHex()

    fun String.sort() = this.toCharArray().sorted().joinToString("")

    private val DIGITS = "([-\\d])".toRegex()
    private val LETTERS = "(\\w+)".toRegex()
    private val NUMS = "([-\\d]+)".toRegex()
    fun String.extractDigits(): List<Int> = DIGITS.findAll(this).map { it.value.toInt() }.toList()
    fun String.extractInt(): List<Int> = NUMS.findAll(this).map { it.value.toInt() }.toList()
    fun String.extractLong(): List<Long> = NUMS.findAll(this).map { it.value.toLong() }.toList()
    fun String.extractLetters(): List<String> = LETTERS.findAll(this).map { it.value }.toList()
}