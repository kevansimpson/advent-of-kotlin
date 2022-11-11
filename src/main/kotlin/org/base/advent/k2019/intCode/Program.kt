package org.base.advent.k2019.intCode

import org.apache.commons.lang3.StringUtils.leftPad

data class Program(val codes: List<Int>,
                   val input: () -> Int = { 0 }) : Runnable {

    private var index = 0
    var output = 0
    val result = codes.toMutableList()

    operator fun get(index: Int) = result[index]

    override fun run() {
        while (index < result.size) {
            val baseOpCode = result[index]
            val fullOpCode = leftPad(baseOpCode.toString(), 5, '0')
            val p1 by lazy { param(fullOpCode, 2, 1) }
            val p2 by lazy { param(fullOpCode, 1, 2) }

            when (baseOpCode % 100) {
                // add
                1 -> result[result[index + 3]] = p1 + p2.also { index += 4 }
                // multiply
                2 -> result[result[index + 3]] = p1 * p2.also { index += 4 }
                // input
                3 -> {
                    if ('0' == fullOpCode[2]) result[result[index + 1]] = input()
                    else result[index + 1] = input()
                    index +=2
                }
                // output
                4 -> output = p1.also { index += 2 }
                // jump if true
                5 -> if (p1 != 0) index = p2 else index += 3
                // jump if false
                6 -> if (p1 == 0) index = p2 else index += 3
                // less than
                7 -> result[result[index + 3]] = (if (p1 < p2) 1 else 0).also { index += 4 }
                // equal to
                8 -> result[result[index + 3]] = (if (p1 == p2) 1 else 0).also { index += 4 }
                99 -> return
                else -> throw IllegalStateException("$index -> $fullOpCode")
            }
        }
    }

    private fun param(fullOpCode: String, opCodeIndex: Int, offset: Int): Int =
            with (result[index + offset]) {
                if ('0' == fullOpCode[opCodeIndex]) result[this] else this }

    companion object {
        fun runProgram(input: List<Int>, value: Int): Int = Program(input) { value }.apply { run() }.output
    }
}