package org.base.advent.k2021

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import kotlin.math.max

/**
 * <a href="https://adventofcode.com/2021/day/18">Day 18</a>
 */
class Day18 : PuzzleReader, TimeSaver {

    private val input = readLines("2021/input18.txt")

    override fun solve1(): Any = rootSnailFish.magnitude()

    override fun solve2(): Any = if (fullSolve) maxMagnitude() else maxMagnitudeSnailFish

    private val snailFish by lazy { input.map { parseFish(it, mutableMapOf()) } }

    private val rootSnailFish by lazy {
        snailFish.drop(1).fold(reduceFish(snailFish.first())) { sum, fish -> reduceFish(PairFish(sum, fish)) }
    }

    private val maxMagnitudeSnailFish by lazy {
        parseFish("[[[[9,8],[9,8]],[[8,8],[7,8]]],[[[8,8],[7,8]],[[8,0],[8,7]]]]", mutableMapOf()).magnitude()
    }

    private fun maxMagnitude(): Int {
        var magnitude = 0
        for (i in snailFish.indices) {
            for (j in snailFish.indices) {
                if (i != j) {
                    val f1 = parseFish(input[i], mutableMapOf())
                    val f2 = parseFish(input[j], mutableMapOf())
                    val f3 = reduceFish(PairFish(f1, f2))
                    magnitude = max(magnitude, f3.magnitude())
                }
            }
        }
        return magnitude
    }

    private fun reduceFish(fish: SnailFish): SnailFish {
        var snail = fish.toString()
        while (fish.explode(0, fish.flatten())) {
            snail = fish.toString()
        }
        return if (fish.split()) reduceFish(fish) else parseFish(snail, mutableMapOf())
    }

    private fun parseFish(snail: String, map: MutableMap<String, SnailFish>): SnailFish {
        var key = if (map.isEmpty()) KEY else map.maxOf { it.key }.toInt()
        var start = 0
        var next = ""
        var m = REGEX.find(snail, start)
        while (m != null) {
            val (m1, m2) = m.destructured
            val f1 = map.remove(m1) ?: SingleFish(m1.toInt())
            val f2 = map.remove(m2) ?: SingleFish(m2.toInt())
            val fish = PairFish(f1, f2)
            key += 1
            map[key.toString()] = fish
            next += "${snail.substring(start, m.range.first)}$key"
            start = m.range.last + 1
            m = REGEX.find(snail, start)
        }
        next += snail.substring(start)

        return if (map.containsKey(next)) map[next]!! else parseFish(next, map)
    }

    companion object {
        private const val KEY = 1000000
        private val REGEX = "\\[(\\d+),(\\d+)]".toRegex()
    }
}

interface SnailFish {
    fun explode(depth: Int = 0, list: List<SingleFish>): Boolean = false
    fun leaf(): Boolean = true
    fun split(): Boolean = false

    fun flatten(): List<SingleFish>
    fun magnitude(): Int
}

class SingleFish(var number: Int) : SnailFish {
    override fun flatten(): List<SingleFish> = listOf(this)
    override fun magnitude(): Int = number
    override fun toString(): String = number.toString()
}

class PairFish(private var left: SnailFish, private var right: SnailFish) : SnailFish {
    override fun explode(depth: Int, list: List<SingleFish>): Boolean =
        when {
            depth < 3 -> left.explode(depth + 1, list) || right.explode(depth + 1, list)
            depth == 3 -> {
                if (left.explode(depth + 1, list)) {
                    left = SingleFish(0)
                    true
                }
                else if (right.explode(depth + 1, list)) {
                    right = SingleFish(0)
                    true
                }
                else false
            }
            depth == 4 && (left.leaf() && right.leaf()) -> {
                val li = list.indexOf(left)
                if (li > 0) {
                    list[li - 1].number = list[li - 1].number + (left as SingleFish).number
                }
                val ri = list.indexOf(right)
                if (ri < list.indices.last) {
                    list[ri + 1].number = list[ri + 1].number + (right as SingleFish).number
                }
                true
            }
            else -> false
        }

    override fun flatten(): List<SingleFish> = listOf(left, right).flatMap { it.flatten() }
    override fun leaf(): Boolean = false
    override fun magnitude(): Int = (3 * left.magnitude()) + (2 * right.magnitude())

    override fun split(): Boolean {
        if (left.leaf() && left.magnitude() >= 10) {
            left = Companion.split(left.magnitude())
            return true
        }
        else if (left.split())
            return true
        if (right.leaf() && right.magnitude() >= 10) {
            right = Companion.split(right.magnitude())
            return true
        }
        return right.split()
    }

    override fun toString(): String = "[$left,$right]"

    companion object {
        fun split(value: Int): PairFish {
            val half = value / 2
            return PairFish(SingleFish(half), SingleFish(value - half))
        }
    }
}