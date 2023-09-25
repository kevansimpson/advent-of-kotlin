package org.base.advent.k2019.intCode

import org.apache.commons.lang3.StringUtils.leftPad
import java.lang.RuntimeException
import java.util.concurrent.ArrayBlockingQueue

data class Program(val codes: List<Long>,
                   val input: Channel,
                   val output: Channel) : Runnable {

    private var index = 0
    var name = "Program"
    val result = codes.toMutableList()

    operator fun get(index: Int) = result[index]

    override fun run() {
        do {
            val fullOpCode = leftPad(result[index].toString(), 4, '0')
            val p1 by lazy { param(fullOpCode, 1, 1) }
            val p2 by lazy { param(fullOpCode, 0, 2) }

            when (fullOpCode.substring(2).toInt()) {
                // add
                1 -> assign(index + 3, p1 + p2).also { index += 4 }
                // multiply
                2 -> assign(index + 3, p1 * p2).also { index += 4 }
                // input
                3 -> assign(index + 1, input.acceptInput()).also { index += 2 }
                // output
                4 -> output.sendOutput(p1).also { index += 2 }
                // jump if true
                5 -> if (p1 != 0L) index = p2.toInt() else index += 3
                // jump if false
                6 -> if (p1 == 0L) index = p2.toInt() else index += 3
                // less than
                7 -> assign(index + 3, (if (p1 < p2) 1L else 0L)).also { index += 4 }
                // equal to
                8 -> assign(index + 3, (if (p1 == p2) 1L else 0L)).also { index += 4 }
                99 -> return
                else -> throw IllegalStateException("$index -> $fullOpCode")
            }
        } while (index != 99)
    }

    private fun assign(index: Int, value: Long) {
        result[result[index].toInt()] = value
    }

    private fun param(fullOpCode: String, opCodeIndex: Int, offset: Int): Long =
        when (fullOpCode[opCodeIndex]) {
            '0' -> result[result[index + offset].toInt()]
            '1' -> result[index + offset]
            else -> throw RuntimeException()
        }

    companion object {
        fun runProgram(input: List<Long>): Program =
            Program(input, newChannel(1), newChannel(1)).also { it.run() }

        fun newChannel(capacity: Int, vararg values: Long): Channel =
            Channel("", ArrayBlockingQueue(capacity)).also { values.forEach { v -> it.sendOutput(v) } }
    }
}