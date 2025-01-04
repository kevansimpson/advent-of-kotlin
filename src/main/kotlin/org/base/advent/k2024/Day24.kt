package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Text.splitByBlankLine

/**
 * <a href="https://adventofcode.com/2024/day/24">Day 24</a>
 */
class Day24 : PuzzleFunction<List<String>, Pair<Long, String>> {

    override fun apply(input: List<String>): Pair<Long, String> {
        val system = scan(input)
        var gateLevels = system.firstLevelGates()
        val swaps = mutableSetOf<String>()
        while (true) {
            system.identifySwappedWires(gateLevels, swaps)
            if (swaps.size == 8) break
            gateLevels = system.nextLevelGates(gateLevels, swaps)
        }

        system.reset()
        return system.resolveAll("z") to swaps.sorted().joinToString(",")
    }

    data class LiveSystem(val initialWires: Map<String, Long>,
                          val gatesByOutput: MutableMap<String, Gate>,
                          val gatesByInput: Map<String, List<Gate>>,
                          val resolved: MutableMap<String, Long>) {
        fun resolveAll(prefix: String): Long {
            val output = StringBuilder()
            for (c in wireCount(prefix) - 1 downTo 0)
                output.append(resolve(String.format("%s%02d", prefix, c)))

            return output.toString().toLong(2)
        }

        private fun resolve(output: String): Long {
            if (!resolved.containsKey(output)) {
                val g = gatesByOutput[output]!!
                val left = resolve(g.left)
                val right = resolve(g.right)
                when (g.op) {
                    "AND" -> resolved[output] = left and right
                    "OR" -> resolved[output] = left or right
                    "XOR" -> resolved[output] = left xor right
                }
            }

            return resolved[output]!!
        }

        fun nextLevelGates(current: List<Gate>, swaps: MutableSet<String>): List<Gate> {
            val next = mutableListOf<Gate>()
            current.forEach { level ->
                val wire = level.output
                if (!wire.startsWith("z") && !swaps.contains(wire))
                    gatesByInput[wire]!!.forEach { g ->
                        g.type = "${level.type} ${level.op}"
                        g.nextOps = findNextOps(g.output)
                        next.add(g)
                    }
            }
            return next
        }

        fun identifySwappedWires(gateLevels: List<Gate>, swaps: MutableSet<String>) {
            val outputById = mutableMapOf<String, MutableList<String>>()
            gateLevels.forEach { level ->
                val id = level.id()
                if (!isOutlier(level, id)) {
                    if (!outputById.containsKey(id))
                        outputById[id] = mutableListOf()
                    outputById[id]!!.add(level.output)
                }
            }

            outputById.values.forEach { outputs ->
                if (outputs.size <= 8)
                    swaps.addAll(outputs)
            }
        }

        private fun isOutlier(gate: Gate, id: String): Boolean =
            when (id) {
                "XOR~~XY~~ZZZ", "AND~~XY~~AND-XOR" -> gate.left.endsWith("00")
                "OR~~XY AND~~ZZZ" -> gate.output.startsWith("z") &&
                        gate.output.substring(1).toInt() == initialWires.size / 2
                else -> false
            }

        fun firstLevelGates(): List<Gate> {
            val firstLevel = Array<Gate?>(initialWires.size) { null }
            gatesByOutput.forEach { (out, g) ->
                if (g.left.startsWith("x") || g.left.startsWith("y")) {
                    g.type = "XY"
                    g.nextOps = findNextOps(out)
                    val index = g.left.substring(1).toInt() * 2 + (if (g.op == "AND") 0 else 1)
                    firstLevel[index] = g
                }
            }
            return firstLevel.filterNotNull()
        }

        private fun findNextOps(outWire: String): String =
            if (outWire.startsWith("z"))
                "ZZZ"
            else
                gatesByInput[outWire]!!.map { it.op }.sorted().joinToString("-")

        fun reset() = with (resolved) {
            clear()
            putAll(initialWires)
        }

        private fun wireCount(prefix: String): Int {
            var count = 0
            gatesByOutput.keys.forEach { out ->
                if (out.startsWith(prefix))
                    count++
            }

            if (count == 0)
                resolved.keys.forEach { out ->
                    if (out.startsWith(prefix))
                        count++
                }

            return count
        }
    }

    private fun scan(input: List<String>): LiveSystem {
        val data = splitByBlankLine(input)
        val initialWires = mutableMapOf<String, Long>()
        data[0].forEach { wire ->
            val parts = wire.split(": ")
            initialWires[parts[0]] = parts[1].toLong()
        }

        val gatesByOutput = mutableMapOf<String, Gate>()
        val gatesByInput = mutableMapOf<String, MutableList<Gate>>()
        data[1].forEach { conn ->
            val wires = conn.split(" ")
            val g = Gate(wires[0], wires[1], wires[2], wires[4])
            gatesByOutput[wires[4]] = g

            if (!gatesByInput.containsKey(g.left))
                gatesByInput[g.left] = mutableListOf()
            gatesByInput[g.left]!!.add(g)
            if (!gatesByInput.containsKey(g.right))
                gatesByInput[g.right] = mutableListOf()
            gatesByInput[g.right]!!.add(g)
        }

        return LiveSystem(initialWires, gatesByOutput, gatesByInput, initialWires.toMutableMap())
    }

    class Gate(l: String, o: String, r: String, out: String) {
        val left = l
        val op = o
        val right = r
        val output = out
        var type = ""
        var nextOps = ""

        fun id() = "$op~~$type~~$nextOps"
    }
}