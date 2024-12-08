package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.k2024.Day06.Lab.Companion.createLab
import org.base.advent.util.Point

/**
 * <a href="https://adventofcode.com/2024/day/6">Day 6</a>
 */
class Day06 : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val lab = createLab(input)
        val path = lab.followGuard()
        return path.uniqueSteps() to lab.findLoops(path.path)
    }

    data class Lab(val size: Int, val obstacles: Set<Point>, val guard: Point, val direction: Int) {
        fun findLoops(path: Set<Point>): Int =
            path.count { cloneLab(this, it).followGuard().isLoop }

        fun followGuard(): GuardPath {
            val encounters = mutableSetOf<Encounter>()
            val path = mutableSetOf<Point>()
            var pos = guard
            var dir = direction
            while (inLab(pos)) {
                path.add(pos)
                var next = pos.move(DIR[dir])
                while (obstacles.contains(next)) {
                    val enc = Encounter(next, dir)
                    if (encounters.contains(enc))
                        return GuardPath(path, true)
                    else
                        encounters.add(enc)
                    dir = (dir + 1) % 4
                    next = pos.move(DIR[dir])
                }
                pos = next
            }

            return GuardPath(path, false)
        }

        private fun inLab(loc: Point): Boolean =
            (loc.ix in 0..<size && loc.iy <= 0 && loc.iy > -size)

        companion object {
            private val DIR = arrayOf('^','>','v','<')

            fun createLab(input: List<String>): Lab {
                val size = input.size
                val obstacles = mutableSetOf<Point>()
                var guard: Point = Point.ORIGIN
                var direction: Int = -1

                for (r in input.indices) {
                    for (c in input.indices) {
                        when (val at = input[r][c]) {
                            '#' -> obstacles.add(Point(c, -r))
                            '^', '>', 'v', '<' -> {
                                guard = Point(c, -r);
                                direction = DIR.indexOf(at);
                            }
                        }
                    }
                }
                return Lab(size, obstacles, guard, direction)
            }

            fun cloneLab(lab: Lab, newObstacle: Point): Lab =
                Lab(lab.size, lab.obstacles.toMutableSet().also { it.add(newObstacle) }, lab.guard, lab.direction)
        }
    }

    data class Encounter(val obstacle: Point, val direction: Int)

    data class GuardPath(val path: Set<Point>, val isLoop: Boolean) {
        fun uniqueSteps(): Int = path.size
    }
}