package org.base.advent.k2021

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleReader
import org.base.advent.TimeSaver
import java.util.concurrent.atomic.AtomicInteger

/**
 * <a href="https://adventofcode.com/2021/day/12">Day 12</a>
 */
class Day12 : PuzzleReader, TimeSaver {

    private val input = readLines("2021/input12.txt")
//    private val input = listOf(
//        "start-A",
//        "start-b",
//        "A-c",
//        "A-b",
//        "b-d",
//        "A-end",
//        "b-end")

    override fun solve1(): Any = paths { p, n -> p.visited().contains(n) }

    override fun solve2(): Any = if (fullSolve) paths { p, n -> noVisitTwice(p, n) } else 96988

    private val caveMap by lazy {
        input.map { it.split("-") }
                .fold(mutableMapOf<String, MutableList<String>>()) { map, a2b ->
            add(add(map, a2b[0], a2b[1]), a2b[1], a2b[0])
        }.filter { it.key != "end" }
                .mapValues { e -> e.value.filter { cave -> cave != "start" } }
    }
    // {start=[A, b], A=[c, b, end], b=[A, d, end], c=[A], d=[b]}

    private fun paths(isNotValid: (Path, String) -> Boolean): Int {
        val paths = AtomicInteger(0)
        var current = mutableListOf(Path(mutableListOf("start")))
        while (current.isNotEmpty()) {
            val next = mutableListOf<Path>()
            current.forEach { p ->
                caveMap[p.last()]!!.forEach { n ->
                    if (!(StringUtils.isAllLowerCase(n) && isNotValid(p, n))) {
                        if ("end" == n)
                            paths.incrementAndGet()
                        else {
                            val copy = p.copy(caves = p.toMutableList())
                            copy.add(n)
                            next.add(copy)
                        }
                    }
                }
            }
            current = next
        }

        debug("Cave path count: ${paths.get()}")
        return paths.get()
    }

    private fun noVisitTwice(path: Path, neighbor: String): Boolean {
        val counts = path.groupingBy { it }.eachCount()
        val atLeastTwo = counts.filter { StringUtils.isAllLowerCase(it.key) && it.value >= 2 }

        if (atLeastTwo.containsKey(neighbor)) return true

        return when (counts.getOrDefault(neighbor, 0)) {
            1 -> atLeastTwo.isNotEmpty()
            else -> false
        }
    }

    private fun add(map: CaveMap, a: String, b: String): CaveMap {
        if (map.contains(a)) map[a]!!.add(b) else map[a] = mutableListOf(b)
        return map
    }
}

typealias CaveMap = MutableMap<String, MutableList<String>>

data class Path(val caves: MutableList<String>) : MutableList<String> by caves {
    fun visited() = caves.filter { StringUtils.isAllLowerCase(it) }
}
