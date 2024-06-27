package org.base.advent.util

/** Enables [] access. */
interface IndexedContainer {
    operator fun get(index: Int): CharArray
    operator fun get(point: Point): Char
}

class Square(private val grid: Array<CharArray>): IndexedContainer {
    val indices = grid.indices
    val size = grid.size
    private val range = 0 until size

    override fun get(index: Int): CharArray = grid[index]
    override fun get(point: Point): Char = grid[point.iy][point.ix]

    fun contains(x: Int, y: Int): Boolean = x in range && y in range
    fun contains(point: Point) = contains(point.ix, point.iy)

    fun display() {
        for (y in indices) {
            for (x in indices)
                print(grid[y][x])
            println()
        }
    }

    fun first(target: Char): Point = firstOrNull(target) ?: throw RuntimeException("missing $target")

    fun firstOrNull(target: Char): Point? {
        for (row in grid.indices)
            for (col in grid[row].indices)
                if (grid[row][col] == target)
                    return Point(row, col)
        return null
    }

}
