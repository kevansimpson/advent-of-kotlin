package org.base.advent.util

import org.base.advent.util.Extensions.toHex
import java.security.MessageDigest

object Extensions {

    fun ByteArray.toHex(): String =
            joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }}

    infix fun Int.toward(to: Int): IntProgression {
        val step = if (this > to) -1 else 1
        return IntProgression.fromClosedRange(this, to, step)
    }

    infix fun Long.toward(to: Long): LongProgression {
        val step = if (this > to) -1L else 1L
        return LongProgression.fromClosedRange(this, to, step)
    }

    fun String.md5ToHex() =
            MessageDigest.getInstance("MD5").digest(this.toByteArray()).toHex()