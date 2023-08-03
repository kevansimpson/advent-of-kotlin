package org.base.advent.k2022

import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import org.base.advent.util.Extensions.extractInt
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.*
import kotlin.math.truncate

/**
 * <a href="https://adventofcode.com/2022/day/16">Day 16</a>
 * <br/>
 * s/o <a href="https://github.com/frhel/AdventOfCode/blob/master/2022/day_16/js/index.js"/>
 */
class Day16(private val qualifier: String = "") : PuzzleReader, TimeSaver {

    private val input = readLines("2022/input16$qualifier.txt")
    private val valves = input.map { scanCave(it) }
    private val valveMap = valves.associateBy { it.name }
    init {
        valves.forEach { it.edges = shortestPaths(valveMap, it.name) }
    }
    private val importantValves = valveMap.filter { it.value.flowRate > 0 || it.value.name == "AA" }
    private val pool = Executors.newFixedThreadPool(if (qualifier.isNotEmpty()) 2 else 25)

    override fun solve1(): Any = mostEfficientPath(importantValves)

    override fun solve2(): Any =
        // always full solve for unit test
        if (qualifier.isNotEmpty()) mostElephantPath(importantValves)
        // part 2 takes over 3 seconds
        else fastOrFull(2594) { mostElephantPath(importantValves) }

    private fun mostElephantPath(valveMap: Map<String, Valve>,
                                 start: String = "AA",
                                 duration: Int = 26): Int =
        with (mutableListOf<Int>()) {
            val startNode = valveMap[start]!!
            val unopened = valveMap.filter { it.key != start }
            val count = truncate(unopened.size / 2.0).toInt()
            val mod = unopened.size % 2
            val allPaths = combinations(unopened.values.toList(), count + mod)
            val tasks = allPaths.indices.map { i ->
                pool.submit {
                    val temp = allPaths.toMutableList()
                    for (j in 0 until allPaths[i].size) {
                        val index = temp.indexOfFirst { node -> node[0].name == allPaths[i][j].name }
                        if (index > -1)
                            temp.removeAt(index)
                    }

                    val a = allPaths[i].associateBy { it.name } + (start to startNode)
                    val b = temp.flatten().filter { !a.keys.contains(it.name) }.associateBy { it.name } + (start to startNode)
                    add(mostEfficientPath(a, start, duration) + mostEfficientPath(b, start, duration))
                }
            }
            tasks.forEach { it.get() }

            max()
        }

    private fun mostEfficientPath(valveMap: Map<String, Valve>,
                                  start: String = "AA",
                                  duration: Int = 30): Int {
        val queue = ArrayDeque<Path>().apply { addFirst(Path(valveMap[start]!!)) }
        var best = queue[0]
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst().also { it.visited.add(it.valve.name) }
            if (current.flowInfo.totalFlow > best.flowInfo.totalFlow)
                best = current

            valveMap.values.forEach dig@ { edge ->
                if (current.visited.contains(edge.name) ||
                    current.valve.name == edge.name ||
                    current.flowInfo.steps + current.valve.edges[edge.name]!! > duration)
                    return@dig

                val calculated = calculateFlow(
                    current, edge, duration,
                    FlowInfo(steps = current.flowInfo.steps + current.valve.edges[edge.name]!!))
                if (calculated.totalFlow < best.flowInfo.totalFlow &&
                    calculated.steps >= best.flowInfo.steps)
                    return@dig

                queue.addLast(Path(edge, current.visited.toMutableList(), calculated))
            }
        }

        return best.flowInfo.totalFlow
    }

    private fun calculateFlow(current: Path, edge: Valve, duration: Int, flowInfo: FlowInfo): FlowInfo =
        with (flowInfo) {
            val newFlow = (current.flowInfo.flowRate * current.valve.edges[edge.name]!!) + current.flowInfo.flow
            val newRate = current.flowInfo.flowRate + edge.flowRate
            this.copy(
                flow = newFlow,
                flowRate = newRate,
                totalFlow = (newRate * (duration - flowInfo.steps)) + newFlow)
        }

    companion object {
        fun combinations(list: List<Valve>, len: Int): MutableList<List<Valve>> =
            if (len == 0)
                mutableListOf(mutableListOf())
            else
                mutableListOf<List<Valve>>().apply {
                    for (i in 0..(list.size - len)) {
                        val foo = list.slice((i + 1)..list.lastIndex)
                        val sub = combinations(foo, len - 1)
                        for (perm in sub)
                            add(listOf(list[i]) + perm.toList())

                    }
                }

        fun shortestPaths(valveMap: Map<String, Valve>, start: String): Map<String, Int> =
            mutableMapOf<String, Int>().apply {
                valveMap.values.forEach skipValve@ { valve ->
                    if (valve.name == start || valve.flowRate == 0)
                        return@skipValve

                    val visited = mutableSetOf<String>()
                    val queue = ArrayDeque<String>().apply { addLast(start) }
                    val path = Stack<Pair<String, Valve>>()
                    while (queue.isNotEmpty()) {
                        val currentName = queue.removeFirst()
                        val current = valveMap[currentName]!!
                        if (current.tunnels.contains(valve.name)) {
                            this[valve.name] = tracePaths(path, current, valve)
                            break
                        }

                        current.tunnels.forEach dig@ { tunnel ->
                            if (visited.contains(tunnel))
                                return@dig
                            path.push(Pair(tunnel, current))
                            queue.addLast(tunnel)
                        }

                        visited.add(currentName)
                    }
                }
            }

        private fun tracePaths(path: Stack<Pair<String, Valve>>, current: Valve, end: Valve): Int =
            with (mutableListOf(end, current)) {
                var temp = current
                while (path.size > 0) {
                    val nextValve = path.removeLast()
                    if (nextValve.first == temp.name) {
                        add(nextValve.second)
                        temp = nextValve.second
                    }
                }
                size
            }

        fun scanCave(report: String): Valve =
            with (report.split(" ")) {
                Valve(this[1], this[4].extractInt().first(),
                    this.subList(9, this.size).map { it.replace(",", "") })
            }
    }
}

data class Valve(val name: String,
                 val flowRate: Int,
                 val tunnels: List<String>,
                 var edges: Map<String, Int> = mapOf())

data class Path(val valve: Valve,
                val visited: MutableList<String> = mutableListOf(),
                val flowInfo: FlowInfo = FlowInfo()
)

data class FlowInfo(val steps: Int = 0,
                    val flowRate: Int = 0,
                    val flow: Int = 0,
                    val totalFlow: Int = 0)