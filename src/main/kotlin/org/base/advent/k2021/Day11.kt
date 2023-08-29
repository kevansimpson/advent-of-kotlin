package org.base.advent.k2021

import org.base.advent.PuzzleSolver
import org.base.advent.TimeSaver
import org.base.advent.util.Extensions.toward
import org.base.advent.util.Point
import org.base.advent.util.Point.Companion.inGrid
import java.util.concurrent.atomic.AtomicInteger

/**
 * <a href="https://adventofcode.com/2021/day/11">Day 11</a>
 */
class Day11 : PuzzleSolver<List<String>>, TimeSaver {
    override fun solve1(input: List<String>): Any = octopusFlashing(input, toGrid(input))

    override fun solve2(input: List<String>): Any = octopusFlashing(input, toGrid(input), 250)

    private fun toGrid(input: List<String>): Map<Point, Int> =
        input.indices.fold(mutableMapOf<Point, Int>()) { map, y ->
            input[y].forEachIndexed { x, ch -> map[Point(x, y)] = ch.digitToInt() }
            map
        }.toMap()

    private fun toEnergyArray(input: List<String>, grid: Map<Point, Int>): Array<MutableList<Point>> =
            grid.entries.fold(Array(input.size) { mutableListOf() }) { arr, entry ->
                arr[entry.value].add(entry.key)
                arr
            }

    private fun octopusFlashing(input: List<String>, grid: Map<Point, Int>, steps: Int = 100): Int {
        val octopus = toEnergyArray(input, grid)
        if (fullSolve) display(octopus)
        val flashCount = AtomicInteger(0)

        for (step in 1..steps) {
            val nines = octopus[9]
            for (c in 8 toward 0)
                octopus[c + 1] = octopus[c]
            octopus[0] = mutableListOf()

            val flashed = mutableListOf<Point>()
            while (nines.isNotEmpty()) {
                val fo = nines.removeFirst() // flashing octopus
                flashed.add(fo)
                fo.surrounding().filter { inGrid(it, octopus.size) }.forEach { sn ->
                    if (!flashed.contains(sn) && !nines.contains(sn)) {
                        val energyList = octopus.first { it.contains(sn) }
                        val energy = octopus.indexOf(energyList)
                        energyList.remove(sn)
                        if (energy == 9) {
                            octopus[0].add(sn)
                            nines.add(sn)
                        } else
                            octopus[energy + 1].add(sn)
                    }
                }
            }
            octopus[0] = flashed
            if (flashed.size == 100)
                return step
            flashCount.addAndGet(flashed.size)
        }
        return flashCount.get()
    }

    private fun display(arr: Array<MutableList<Point>>) {
        val size = arr.size
        val grid = Array(size) { IntArray(size) }
        arr.forEachIndexed { index, energy ->
            energy.forEach { point -> grid[point.x.toInt()][point.y.toInt()] = index }
        }

        for (y in arr.indices) {
            for (x in arr.indices) {
                print(grid[x][y])
            }
            println()
        }
        println("-----------")
    }
}