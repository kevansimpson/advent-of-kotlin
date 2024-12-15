package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.util.Extensions.extractLong
import org.base.advent.util.Point
import java.util.*

/**
 * <a href="https://adventofcode.com/2024/day/14">Day 14</a>
 */
class Day14(private val width: Int, private val height: Int) : PuzzleFunction<List<String>, Pair<Int, Int>> {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val bots = findRobots(input)
        bots.forEach { b -> b.move(100, width, height) }
        return safetyFactor(bots) to findTree(bots)
    }

    private fun findTree(bots: List<Robot>): Int {
        for (s in 101..10000) {
            val image = mutableSetOf<Point>()
            val positions = Array(height) { IntArray(width) }
            bots.forEach { b ->
                b.move(1, width, height)
                positions[-b.position.iy][b.position.ix]++
                image.add(Point(-b.position.iy, b.position.ix))
            }

            if (image.size == bots.size) {
                printTree(positions)
                return s
            }
        }
        return -1
    }

    private fun printTree(positions: Array<IntArray>) {
        positions.forEach { row ->
            println(row.contentToString().replace(", ", "").replace("0", " "))
        }
    }

    private fun safetyFactor(bots: List<Robot>): Int {
        val midX = width / 2
        val midY = height / 2
        val quadrants = intArrayOf(0, 0, 0, 0)
        bots.forEach { b ->
            if (b.position.ix < midX) {
                if (b.position.iy > -midY)
                    quadrants[0]++
                else if (b.position.iy < -midY)
                    quadrants[1]++
            }
            else if (b.position.ix > midX) {
                if (b.position.iy > -midY)
                    quadrants[2]++
                else if (b.position.iy < -midY)
                    quadrants[3]++
            }
        }
        return quadrants.fold(1) { factor, num -> factor * num }
    }

    private fun findRobots(input: List<String>): List<Robot> =
        input.map { line: String ->
            val nums: List<Long> = line.extractLong()
            Robot(Point(nums[0], -nums[1]), Point(nums[2], -nums[3]))
        }

    data class Robot(var position: Point, val velocity: Point) {
        fun move(seconds: Int, width: Int, height: Int) {
            for (s in 0 until seconds) {
                var newX = position.x + velocity.x
                var newY = position.y + velocity.y
                if (newX < 0)  // out of bounds horizontally
                    newX += width.toLong()
                else if (newX >= width)  // out of bounds horizontally
                    newX -= width.toLong()
                if (newY > 0)  // out of bounds vertically
                    newY -= height.toLong()
                else if (newY <= -height)  // out of bounds vertically
                    newY += height.toLong()

                position = Point(newX, newY)
            }
        }
    }
}