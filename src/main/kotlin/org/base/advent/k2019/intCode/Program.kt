package org.base.advent.k2019.intCode

import org.base.advent.k2019.intCode.Channel.Companion.newChannel
import java.util.concurrent.CompletableFuture.runAsync
import java.util.concurrent.Executors

data class Program(val codes: List<Long>,
                   val input: Channel,
                   val output: Channel) : Runnable {

    internal var index = 0L
    internal var relativeBase = 0L
    var name = "Program"
    val result = codes.mapIndexed { index, c -> index.toLong() to c }.toMap().toMutableMap()

    override fun run() {
        do {
            val params = Parameters(this)

            when (params.opCode) {
                1 -> assign(params.c3, params.a + params.b)               // add
                2 -> assign(params.c3, params.a * params.b)               // multiply
                3 -> assign(params.c1, input.accept())                          // input
                4 -> output.send(params.a)                                      // output
                5 -> params.jump(params.a != 0L, params.b)             // jump if true
                6 -> params.jump(params.a == 0L, params.b)             // jump if false
                7 -> assign(params.c3, if (params.a < params.b) 1L else 0L)     // less than
                8 -> assign(params.c3, if (params.a == params.b) 1L else 0L)    // equal to
                9 -> relativeBase += params.a                                   // adjust relativeBase
                99 -> return
                else -> throw IllegalStateException("$index -> ${params.fullOpCode}")
            }
            index = params.nextIndex()
        } while (get(index) != 99L)
    }

    private fun assign(index: Long, value: Long) {
        result[index] = value
    }

    internal fun get(index: Long): Long = result[index] ?: 0L

    companion object {
        fun runProgram(input: List<Long>): Program =
            Program(input, newChannel(1), newChannel(1)).also { it.run() }

        fun boostProgram(codes: List<Long>, vararg signals: Long): Channel =
            newChannel(100).apply {
                val pool = Executors.newFixedThreadPool(1)
                pool.use {
                    val p = Program(codes, newChannel(10), this)
                    val f = runAsync(p, pool)
                    signals.forEach { p.input.send(it) }
                    f.get()
                }
            }
    }
}