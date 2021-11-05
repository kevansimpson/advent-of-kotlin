package org.base.advent.util

import org.base.advent.util.Extensions.toHex
import java.security.MessageDigest

object Extensions {

    fun ByteArray.toHex(): String =
            joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }}

    fun String.md5ToHex() =
            MessageDigest.getInstance("MD5").digest(this.toByteArray()).toHex()