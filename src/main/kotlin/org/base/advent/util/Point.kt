package org.base.advent.util

import kotlin.math.abs

data class Point(val x: Long, val y: Long) {

    constructor (ix: Int, iy:Int) : this(ix.toLong(), iy.toLong())

    val ix by lazy { x.toInt() }
    val iy by lazy { y.toInt() }

    override fun toString() = "$x,$y"

    fun manhattanDistance(point: Point = ORIGIN) = Companion.manhattanDistance(this, point)

    fun move(dx: Int, dy: Int): Point = Point(x + dx, y + dy)

    fun move(dx: Long, dy: Long): Point = Point(x + dx, y + dy)

    fun move(dir: Char, distance: Long = 1): Point = move(dir.toString(), distance)

    fun move(dir: Dir, distance: Long = 1): Point = move(dir.name, distance)

    fun move(dir: String, distance: Long = 1): Point = when (dir) {
        ">", "R", "E", Dir.East.name    -> move(distance, 0)
        "<", "L", "W", Dir.West.name    -> move(-distance, 0)
        "^", "U", "N", Dir.North.name   -> move(0, distance)
        "v", "D", "S", Dir.South.name   -> move(0, -distance)
        else -> this
    }

    fun neighbors(): List<Point> = listOf(move('U'), move('R'), move('D'), move('L'))

    fun corners(): List<Point> = listOf(
            move(1, 1), move(1, -1), move(-1, -1), move(-1, 1))

    fun surrounding(): List<Point> = listOf(move(0, 1), move(1, 1), move(1, 0),
            move(1, -1), move(0, -1), move(-1, -1), move(-1, 0), move(-1, 1))

    companion object {
        val ORIGIN = Point(0, 0)

        fun inGrid(pt: Point, width: Int, height: Int? = null): Boolean =
                (pt.x in 0 until width) && (pt.y in 0 until (height ?: width))

        fun manhattanDistance(a: Point, b: Point): Long = abs(b.x - a.x) + abs(b.y - a.y)
    }
}

enum class Dir {
    North, East, South, West;

    fun turn(dir: String): Dir = when (dir) {
        ">", "R", "E" -> values()[(this.ordinal + 1) % 4]
        "<", "L", "W" -> values()[(this.ordinal + 3) % 4]
        else -> this
    }
}
