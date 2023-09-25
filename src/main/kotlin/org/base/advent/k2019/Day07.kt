package org.base.advent.k2019

import org.base.advent.PuzzleSolver
import org.base.advent.k2019.intCode.Program
import org.base.advent.k2019.intCode.Program.Companion.newChannel
import org.base.advent.util.Permutations.permutations
import java.util.concurrent.Executors

/**
 * <a href="https://adventofcode.com/2019/day/7">Day 7</a>
 */
class Day07 : PuzzleSolver<List<Long>> {

    override fun solve1(input: List<Long>): Any = maxThrusterSignal(input, false, PART1_PHASES)

    override fun solve2(input: List<Long>): Any = maxThrusterSignal(input, true, PART2_PHASES)

    private fun maxThrusterSignal(codes: List<Long>, feedback: Boolean, boosts: List<Long>): Long {
        var maxThrust = 0L
        permutations(boosts).forEach {
            val thrust = calcThrust(codes, feedback, it)
            if (thrust > maxThrust)
                maxThrust = thrust
        }

        return maxThrust
    }

    private fun calcThrust(codes: List<Long>, feedback: Boolean, boosts: List<Long>): Long {
        val amps = stackAmps(codes, feedback, boosts)
        Executors.newFixedThreadPool(5).use {
            amps.values.forEach { p -> it.execute(p) }
            // To start the process, a 0 signal is sent to amplifier A's input exactly once.
            amps["A"]!!.input.sendOutput(0L)
        }

        return amps["E"]?.output?.poll() ?: -1L
    }

    private fun stackAmps(codes: List<Long>, feedback: Boolean, boosts: List<Long>): Map<String, Program> =
        mutableMapOf<String, Program>().apply {
            val ab = newChannel(2, boosts[1])
            val bc = newChannel(2, boosts[2])
            val cd = newChannel(2, boosts[3])
            val de = newChannel(2, boosts[4])
            val outE = newChannel(2)
            val inA = if (feedback) outE else newChannel(2)
            inA.sendOutput(boosts[0])
            this["A"] = Program(codes, inA, ab)
            this["B"] = Program(codes, ab, bc)
            this["C"] = Program(codes, bc, cd)
            this["D"] = Program(codes, cd, de)
            this["E"] = Program(codes, de, outE)
        }

    companion object {
        val PART1_PHASES = listOf(0L,1L,2L,3L,4L)
        val PART2_PHASES = listOf(5L,6L,7L,8L,9L)
    }
}