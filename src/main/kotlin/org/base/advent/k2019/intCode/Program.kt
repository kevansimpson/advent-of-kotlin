package org.base.advent.k2019.intCode

import org.apache.commons.lang3.StringUtils.leftPad
import java.util.concurrent.Callable

data class Program(val codes: List<Int>) : Callable<Int> {
    private var index = 0
    private var output = 0
    val result = codes.toMutableList()

    operator fun get(index: Int) = result[index]

    override fun call(): Int {
        while (index < result.size) {
            val baseOpCode = result[index]
            val fullOpCode = leftPad(baseOpCode.toString(), 5, '0')
            val p1 by lazy { param(fullOpCode, 2, 1) }
            val p2 by lazy { param(fullOpCode, 1, 2) }

            when (baseOpCode % 100) {
                1 -> result[result[index + 3]] = p1 + p2.also { index += 4 }
                2 -> result[result[index + 3]] = p1 * p2.also { index += 4 }
                99 -> return output
                else -> throw IllegalStateException("$index -> $fullOpCode")
            }
        }
        return output
    }

    private fun param(fullOpCode: String, opCodeIndex: Int, offset: Int): Int =
            with (result[index + offset]) {
                if ('0' == fullOpCode[opCodeIndex]) result[this] else this }
}