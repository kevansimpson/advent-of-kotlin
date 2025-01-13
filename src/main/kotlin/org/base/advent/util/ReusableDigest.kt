package org.base.advent.util

import java.security.MessageDigest

object ReusableDigest {
    private val HEX_ARRAY = "0123456789abcdef".toCharArray()
    private val digest = newMD5()

    fun hashHex(input: String) =
        try {
            digest.convertToMD5ThenHex(input)
        }
        finally {
            digest.reset()
        }

    fun hexCharToInt(ch: Char) = HEX_ARRAY.indexOf(ch)

    private fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

    private fun MessageDigest.convertToMD5ThenHex(text: String): String = digest(text.toByteArray()).toHex()

    private fun newMD5(): MessageDigest =
        try {
            MessageDigest.getInstance("MD5")
        }
        catch (ex: Exception) {
            throw RuntimeException("MD5 go boom", ex)
        }
}