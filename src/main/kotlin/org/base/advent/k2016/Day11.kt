package org.base.advent.k2016

import org.base.advent.ParallelSolution
import org.base.advent.TimeSaver
import org.base.advent.util.Node
import org.base.advent.util.Node.Companion.createRootNode
import org.base.advent.util.Permutations.combinations
import java.lang.Long.parseLong
import java.util.*
import java.util.Comparator.comparingLong
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2016/day/11">Day 11</a>
 */
class Day11(pool: ExecutorService) : ParallelSolution<List<String>>(pool), TimeSaver {
    override fun solve1(input: List<String>): Any = scanFacility(input).findFewestSteps()

    override fun solve2(input: List<String>): Any {
        val facility = scanFacility(input)
        val extraParts = listOf("EG", "EM", "DG", "DM")
        val newGenChips = facility.allGenChips.toMutableList().apply {
            addAll(extraParts)
        }.sorted()
        extraParts.forEach { gm -> facility.floors[gm] = 1 }
        val newFacility = Facility(newGenChips, facility.floors)
        if (fullSolve)
            newFacility.display()
        return newFacility.findFewestSteps()
    }

    class Facility(val allGenChips: List<String>,
                   val floors: MutableMap<String, Int> = mutableMapOf(),
                   private var elevator: Int = 1) {
        val size = allGenChips.size
        private val maxSize = size * 4
        val target: Long by lazy {
            val floor ="0100".padStart(size, '0')
            val fourth = "".padStart(size, '1').padEnd(4 * size, '0')
            parseLong("$floor$fourth", 2)
        }
        private val twos: LongArray by lazy {
            val array = LongArray(maxSize) { 1L }
            for (i in (maxSize) - 2 downTo 0) {
                array[i] = 2L * array[i + 1]
            }
            array
        }
        private val elevatorScores by lazy {
            (0..4).map { it.toLong() shl maxSize }.toLongArray()
        }

        fun findFewestSteps(): Long {
            val visited = mutableMapOf<Long, Long>()
            val fewestSteps = 62L // just higher than part2 answer to improve performance
            val queue = PriorityQueue(comparingLong<Node<Long>> { it.data }.reversed())
            queue.add(createRootNode(score()))

            while (queue.isNotEmpty()) {
                val node = queue.poll()
                val score = node.data
                if ((!visited.containsKey(score) || node.depth < visited[score]!!)) {
                    visited[score] = node.depth
                    if (score == target) {
                        if (node.depth < fewestSteps) {
                            return node.depth
                        }
                    }

                    val elevator = (score shr maxSize).toInt() // 1-4
                    val allFloors = score - elevatorScores[elevator]
                    val allFloorsBin = allFloors.toString(2).padStart(maxSize, '0')
                    val start = (4 - elevator) * size
                    val end = start + size
                    val currentFloor = mutableListOf<Long>().apply {
                        for (i in start until end)
                            if (allFloorsBin[i] == '1')
                                add(twos[i])
                    }

                    var movePairDown = true
                    val pairs = combinations(currentFloor, 2)
                    if (elevator < 4) {
                        // move pair up
                        for (pair in pairs) {
                            val sum = pair.sum()
                            val upScore = allFloors - sum + (sum shl size)
                            if (isValid(upScore)) {
                                queue.add(node.addChild(upScore + elevatorScores[elevator + 1]))
                                movePairDown = false
                            }
                        }

                        // move single up
                        for (gc in currentFloor) {
                            val upScore = allFloors - gc + (gc shl size)
                            if (isValid(upScore)) {
                                queue.add(node.addChild(upScore + elevatorScores[elevator + 1]))
                                movePairDown = false
                            }
                        }
                    }

                    if (elevator > 1) {
                        // move single down
                        for (gc in currentFloor) {
                            val downScore = allFloors - gc + (gc shr size)
                            if (isValid(downScore)) {
                                queue.add(node.addChild(downScore + elevatorScores[elevator - 1]))
                                movePairDown = false
                            }
                        }

                        if (movePairDown) {
                            for (pair in pairs) {
                                val sum = pair.sum()
                                val downScore = allFloors - sum + (sum shr size)
                                if (isValid(downScore))
                                    queue.add(node.addChild(downScore + elevatorScores[elevator - 1]))
                            }
                        }
                    }
                }
            }

            return -1
        }

        // should not have elevator prepended
        private fun isValid(score: Long): Boolean =
            isValid(score.toString(2).padStart(maxSize, '0'))

        private fun isValid(facility: String): Boolean {
            for (floor in facility.chunked(size)) {
                var gens = 0
                var unprotectedChips = 0
                for (i in floor.indices step 2) {
                    if (floor[i] == '1')
                        gens++
                    else if (floor[i + 1] == '1')
                        unprotectedChips++
                }
                if (unprotectedChips > 0 && gens > 0) {
                    return false
                }
            }
            return true
        }

        private fun floorBinary(level: Int): String {
            val score = StringBuilder()
            for (gc in allGenChips)
                score.append(if (level == floors[gc]) 1 else 0)

            return score.toString()
        }

        private fun score(): Long {
            val score = StringBuilder().append(elevator.toString(2))
            for (level in 4 downTo  1)
                score.append(floorBinary(level))

            return parseLong(score.toString(), 2)
        }

        fun display() {
            println()
            for (level in 4 downTo  1) {
                println()
                print("F$level " + if (level == elevator) "E " else "  ")
                for (gc in allGenChips)
                    print("${if (level == floors[gc]) gc else "  "} ")
            }
            println()
        }
    }

    private fun scanFacility(input: List<String>): Facility {
        val floors = mutableMapOf<String, Int>()
        val allGenChips = mutableListOf<String>()
        for (floor in input.indices) {
            val chips = CHIP_REGEX.abbreviateAll(input[floor], "M")
            allGenChips.addAll(chips)
            chips.forEach { c -> floors[c] = floor + 1 }
            val gens = GEN_REGEX.abbreviateAll(input[floor], "G")
            allGenChips.addAll(gens)
            gens.forEach { g -> floors[g] = floor + 1 }
        }
        return Facility(allGenChips.sorted(), floors)
    }

    private fun Regex.abbreviateAll(text: String, suffix: String): List<String> =
        this.findAll(text).map {
            "${it.value.substring(0, 1).map { ch -> ch.uppercaseChar() }[0]}$suffix" }.toList()

    companion object {
        val CHIP_REGEX = "(\\w+)-compatible microchip".toRegex()
        val GEN_REGEX = "(\\w+) generator".toRegex()
    }
}

