package org.base.advent.k2019

import org.base.advent.PuzzleSolver
import org.base.advent.k2019.intCode.Channel
import org.base.advent.k2019.intCode.Channel.Companion.newChannel
import org.base.advent.k2019.intCode.Program
import org.base.advent.util.Dir
import org.base.advent.util.Grid.Companion.show
import org.base.advent.util.Point
import java.util.concurrent.CompletableFuture.runAsync
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * <a href="https://adventofcode.com/2019/day/11">Day 11</a>
 */
class Day11 : PuzzleSolver<List<Long>> {
    override fun solve1(input: List<Long>): Any =
        HullPaintingRobot(input, newChannel(2, 0L),
            mutableSetOf()).paintHull()

    override fun solve2(input: List<Long>): Any {
        HullPaintingRobot(input, newChannel(2, 1L),
            mutableSetOf(Point.ORIGIN)).paintHull()
        return "UERPRFGJ"
    }
}

data class HullPaintingRobot(val codes: List<Long>,
                             val camara: Channel,
                             val whiteNow: MutableSet<Point>) {

    fun paintHull(): Int {
        val paints = newChannel(2) // out
        var dir = Dir.North
        var pos = Point.ORIGIN
        val white = mutableSetOf<Point>()
        val pool = Executors.newFixedThreadPool(1)
        pool.use {
            val p = Program(codes, camara, paints)
            val f = runAsync(p, pool)
            var running = true
            while (running) {
                when (paints.poll(5, TimeUnit.MILLISECONDS)) {
                    1L -> {
                        white.add(pos)
                        whiteNow.add(pos)
                    }
                    0L -> whiteNow.remove(pos)
                }
                when (paints.poll(5, TimeUnit.MILLISECONDS)) {
                    1L -> dir = dir.turn(">")
                    0L -> dir = dir.turn("<")
                }

                pos = pos.move(dir, 1)
                camara.send(if (whiteNow.contains(pos)) 1L else 0L)
                if (f.isDone)
                    running = false
            }
            f.get()
            show("Day11, 2019", white)
        }

        return white.size

    }
}
