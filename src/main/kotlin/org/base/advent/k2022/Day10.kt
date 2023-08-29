package org.base.advent.k2022

import org.base.advent.PuzzleFunction
import java.util.concurrent.atomic.AtomicInteger

/**
 * <a href="https://adventofcode.com/2022/day/10">Day 10</a>
 */
class Day10 : PuzzleFunction<List<String>, Pair<Int, String>> {
    override fun apply(input: List<String>): Pair<Int, String> {
        val program = readProgram(input)
        /**
         * ###   ##  #  # ####  ##  #    #  #  ##
         * #  # #  # #  # #    #  # #    #  # #  #
         * #  # #    #### ###  #    #    #  # #
         * ###  # ## #  # #    # ## #    #  # # ##
         * #    #  # #  # #    #  # #    #  # #  #
         * #     ### #  # #     ### ####  ##   ###
         */
        return Program(program).runUntil() to "PGHFGLUG"
    }

    private fun readProgram(signals: List<String>): List<Cmd> = signals.map { readSignal(it) }

    private fun readSignal(signal: String): Cmd =
        if (signal == "noop")
            Cmd()
        else
            with(signal.split(" ")) {
                Cmd(this[1].toInt(), AtomicInteger(2))
            }
}

data class Cmd(val add: Int = 0, val duration: AtomicInteger = AtomicInteger(1))

data class Program(val instructions: List<Cmd>,
                   val register: AtomicInteger = AtomicInteger(1),
                   val cpuStack: MutableList<Cmd> = mutableListOf(),
                   val signalStack: MutableList<Int> = mutableListOf()) {

    fun runUntil(): Int {
        cpuStack.addAll(instructions)
        var cycle = 1

        while (cpuStack.isNotEmpty()) {
            if (targetCycles.contains(cycle))
                signalStack.add(register.get() * cycle)

            val p = cycle % 40
            print(if (listOf(p -2, p - 1, p).contains(register.get())) "#" else ".")
            if (p == 0)
                println()

            val cmd = cpuStack.first().also { it.duration.decrementAndGet() /* tick */ }
            if (cmd.duration.get() == 0) {
                register.addAndGet(cmd.add)
                cpuStack.removeFirst()
            }
            cycle += 1
        }

        return signalStack.sum()
    }


    companion object {
        private val targetCycles = listOf(20, 60, 100, 140, 180, 220)
    }
}