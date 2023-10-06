package org.base.advent.util

import org.apache.commons.lang3.Range
import org.apache.commons.lang3.Range.between

/**
 * Utility class to display
 * <a href="https://en.wikipedia.org/wiki/Cartesian_coordinate_system>Cartesian coordinate grids</a>.
 * <table>
 * <caption>Summary of Grid methods</caption>
 *  <tr>
 *    <th>Method Prefix</th><th>Quadrant</th><th>Description</th>
 *  </tr>
 *  <tr>
 *    <td>show</td>
 *    <td>1st</td>
 *    <td>Displays grid from maxY to minY</td>
 *  </tr>
 *  <tr>
 *    <td>flip</td>
 *    <td>2nd</td>
 *    <td>Displays grid from minY to maxY</td>
 *  </tr>
 * </table>
 */
data class Grid(val visible: Collection<Point>, val buffer: Long = 0L) {
    private val xAxis: Range<Long> by lazy { between(visible.minOf { it.x }, visible.maxOf { it.x }) }
    private val yAxis: Range<Long> by lazy { between(visible.minOf { it.y }, visible.maxOf { it.y }) }
    private val minX: Long by lazy { xAxis.minimum - buffer }
    private val maxX: Long by lazy { xAxis.maximum + buffer }
    private val minY: Long by lazy { yAxis.minimum - buffer }
    private val maxY: Long by lazy { yAxis.maximum + buffer }

    fun show(id: String, points: (Point) -> String) {
        for (y in maxY downTo minY)
            row(y, points)
        footer(id)
    }

    fun flip(id: String, points: (Point) -> String) {
        for (y in minY..maxY) {
            row(y, points)
        }
        footer(id)
    }

    private fun row(y: Long, points: (Point) -> String) {
        println()
        for (x in minX..maxX)
            with(Point(x, y)) {
                print(points(this))
            }

    }
    private fun footer(id: String) {
        println("\n--- $id\n")
    }
    private fun hash(pt: Point): String = if (visible.contains(pt)) "#" else " "

    companion object {
        fun show(id: String, visible: Collection<Point>, buffer: Long = 0L) =
            with (Grid(visible, buffer)) {
                show(id, this::hash)
            }

        fun flip(id: String, visible: Collection<Point>, buffer: Long = 0L) =
            with (Grid(visible, buffer)) {
                flip(id, this::hash)
            }
    }
}
