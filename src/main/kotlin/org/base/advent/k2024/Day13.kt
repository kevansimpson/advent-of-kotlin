package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractLong
import org.base.advent.util.Extensions.safeGet
import org.base.advent.util.Point
import java.util.concurrent.CompletableFuture.supplyAsync
import java.util.concurrent.ExecutorService

/**
 * <a href="https://adventofcode.com/2024/day/13">Day 13</a>
 */
class Day13(private val pool: ExecutorService) : PuzzleFunction<List<String>, Pair<Long, Long>> {

    override fun apply(input: List<String>): Pair<Long, Long> =
        with (installMachines(input)) {
            val near = map { supplyAsync({ playCramersRule(it) }, pool) }
            val far = map { supplyAsync({ playCramersRule(it.further()) }, pool) }
            near.sumOf { it.safeGet() } to far.sumOf { it.safeGet() }
        }

    /**
     * <a href="https://www.youtube.com/watch?v=KOUjAzDyeZY">Cramer's Rule</a>
     * <ul>
     *     <li><pre>Determinant   (D)  =      (a.y * b.x) - (b.y * a.x)</pre></li>
     *     <li><pre>Determinant A (Da) = (target.y * b.x) - (b.y * target.x)</pre></li>
     *     <li><pre>Determinant B (Db) = (a.y * target.x) - (target.y * a.x)</pre></li>
     *     <li><pre>a = Da / D where Da % D == 0</pre></li>
     *     <li><pre>b = Db / D where Db % D == 0</pre></li>
     * </ul>
     */
    private fun playCramersRule(machine: Machine): Long =
        with (machine) {
            val determinant = (btnA.y * btnB.x) - (btnB.y * btnA.x)
            val detA = (target.y * btnB.x) - (btnB.y * target.x)
            val detB = (btnA.y * target.x) - (target.y * btnA.x)
            if ((detA % determinant) == 0L && (detB % determinant) == 0L)
                3L * (detA / determinant) + (detB / determinant)
            else
                0L
        }

    private fun installMachines(input: List<String>): List<Machine> =
        mutableListOf<Machine>().apply {
            for (i in input.indices step 4) {
                val a = input[i].extractLong()
                val b = input[i + 1].extractLong()
                val t = input[i + 2].extractLong()
                add(Machine(Point(a[0], a[1]), Point(b[0], b[1]), Point(t[0], t[1])))
            }
        }

    data class Machine(val btnA: Point, val btnB: Point, val target: Point) {
        fun further(): Machine =
            Machine(btnA, btnB, Point(target.x + 10000000000000L, target.y + 10000000000000L))
    }
}