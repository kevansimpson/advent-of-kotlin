package org.base.advent.k2019

import org.base.advent.PuzzleSolver
import org.base.advent.util.Extensions.extractLong
import org.base.advent.util.Permutations.combinations
import kotlin.math.abs

/**
 * <a href="https://adventofcode.com/2019/day/12">Day 12</a>
 */
class Day12 : PuzzleSolver<List<String>> {
    override fun solve1(input: List<String>): Any =
        with (scanMoons(input)) {
            (0 until 1000).forEach { _ -> simulate(this) }
            sumOf { it.energy() }
        }

    override fun solve2(input: List<String>): Any {
        val orbits = (0..2).map { i -> axisOrbit(scanMoons(input), i) }
        return lcm(lcm(orbits[0], orbits[1]), orbits[2])
    }

    private fun axisOrbit(moons: List<Moon>, axis: Int): Long {
        val set = mutableSetOf<String>()
        var hash = hash(moons, axis)
        while (!set.contains(hash)) {
            set.add(hash)
            simulate(moons)
            hash = hash(moons, axis)
        }

        return set.size.toLong()
    }

    private fun hash(moons: List<Moon>, axis: Int): String = moons.joinToString("_") { it.hash(axis) }

    private fun simulate(moons: List<Moon>) {
        PAIRS.forEach { simulate(moons[it[0]], moons[it[1]]) }
        moons.forEach { it.move() }
    }

    private fun simulate(m1: Moon, m2: Moon) {
        for (i in 0 until 3)
            if (m1.position[i] < m2.position[i]) {
                m1.velocity[i] += 1L
                m2.velocity[i] -= 1L
            }
            else if (m1.position[i] > m2.position[i]) {
                m1.velocity[i] -= 1L
                m2.velocity[i] += 1L
            }
    }

    private fun scanMoons(input: List<String>): List<Moon> =
        input.map { it.extractLong() }.map { Moon(longArrayOf(it[0], it[1], it[2])) }

    companion object {
        val PAIRS = combinations(listOf(0,1,2,3), 2)

        // least common multiple
        fun lcm(n1: Long, n2: Long): Long =
            if (n1 == 0L || n2 == 0L)
                0L
            else {
                val nums = listOf(n1, n2).sorted()
                var lcm = nums[1]
                while ((lcm % nums[0]) != 0L)
                    lcm += nums[1]
                lcm
            }
    }
}

data class Moon(val position: LongArray, val velocity: LongArray = LongArray(3) { 0L }) {

    fun energy(): Long = position.sumOf { abs(it) } * velocity.sumOf { abs(it) }

    fun hash(axis: Int): String = "(${position[axis]},${velocity[axis]})"

    fun move() {
        for (i in 0 until 3)
            position[i] += velocity[i]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Moon

        if (!position.contentEquals(other.position)) return false
        return velocity.contentEquals(other.velocity)
    }

    override fun hashCode(): Int {
        var result = position.contentHashCode()
        result = 31 * result + velocity.contentHashCode()
        return result
    }
}
