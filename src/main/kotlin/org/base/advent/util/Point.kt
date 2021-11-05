package org.base.advent.util

data class Point(val x: Long, val y: Long) {

    fun move(dx: Long, dy: Long): Point = Point(x + dx, y + dy)

    fun move(dir: Char, distance: Long = 1): Point = move(dir.toString(), distance)

    fun move(dir: String, distance: Long = 1): Point = when (dir) {
        ">", "R", "E" -> move(distance, 0)
        "<", "L", "W" -> move(-distance, 0)
        "^", "U", "N" -> move(0, distance)
        "v", "D", "S" -> move(0, -distance)
        else -> this
    }

    companion object {
        val ORIGIN = Point(0, 0)
    }
}
