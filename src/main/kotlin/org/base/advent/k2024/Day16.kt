package org.base.advent.k2024

import org.base.advent.PuzzleFunction
import org.base.advent.TimeSaver
import org.base.advent.util.Dir
import org.base.advent.util.Dir.Companion.ARROWS
import org.base.advent.util.Node
import org.base.advent.util.Point
import java.util.PriorityQueue

/**
 * <a href="https://adventofcode.com/2024/day/16">Day 16</a>
 */
class Day16 : PuzzleFunction<List<String>, Pair<Int, Int>>, TimeSaver {

    override fun apply(input: List<String>): Pair<Int, Int> {
        val maze = Maze(input.size)
        maze.load(input)
        val visited = mutableMapOf<DirPoint, Int>()
        val begin = DirPoint(Dir.East, maze.start)
        visited[begin] = 0

        val paths = PriorityQueue<ScoredPath> { a, b ->
            val da = maze.end.manhattanDistance(a.node.data.pos)
            val db = maze.end.manhattanDistance(b.node.data.pos)
            (db - da).toInt()
        }
        paths.add(ScoredPath(Node.createRootNode(begin), 0))
        var minScore = Int.MAX_VALUE
        val solutions = mutableListOf<Node<DirPoint>>()

        while (paths.isNotEmpty()) {
            val path = paths.poll()
            val node = path.node
            val score = path.score
            val data = node.data
            // only proceed if not visited or this path has a lower score
            if (!visited.containsKey(data) || score <= visited.getOrDefault(data, Int.MAX_VALUE)) {
                visited[data] = score

                if (data.pos.equals(maze.end)) {
                    if (score < minScore) {
                        minScore = score
                        solutions.clear()
                        solutions.add(node)
                    }
                    else if (score == minScore)
                        solutions.add(node)
                }
                else {
                    // move forward
                    val forward = data.move()
                    if (maze.get(forward.pos) != '#' &&
                        score <= visited.getOrDefault(forward, Int.MAX_VALUE))
                        paths.add(ScoredPath(node.addChild(forward), score + 1))

                    // turns
                    val left = DirPoint(data.dir.turn("<"), data.pos)
                    if (canTurn(left, maze, visited, score))
                        paths.add(ScoredPath(node.addChild(left), score + 1000))
                    val right = DirPoint(data.dir.turn(">"), data.pos)
                    if (canTurn(right, maze, visited, score))
                        paths.add(ScoredPath(node.addChild(right), score + 1000))
                }
            }
        }

        if (fullSolve)
            maze.displayPath(solutions[0])

        return minScore to placesToSit(solutions)
    }

    private fun canTurn(turn: DirPoint, maze: Maze, visited: Map<DirPoint, Int>, score: Int): Boolean =
        maze.get(turn.move().pos) != '#' &&
                (!visited.containsKey(turn) || score <= visited.getOrDefault(turn, Int.MAX_VALUE))

    private fun placesToSit(solutions: List<Node<DirPoint>>): Int {
        var seats = getSeats(solutions[0])
        for (i in 1 until solutions.size)
            seats = seats.union(getSeats(solutions[i]))
        return seats.size
    }

    private fun getSeats(node: Node<DirPoint>): Set<Point> =
        mutableSetOf<Point>().apply {
            var seats: Node<DirPoint>? = node
            while (seats != null) {
                add(seats.data.pos)
                seats = seats.parent
            }
        }
}

data class DirPoint(val dir: Dir, val pos: Point) {
    fun move() = DirPoint(dir, pos.move(dir))
}

data class ScoredPath(val node: Node<DirPoint>, val score: Int)

class Maze(val size: Int) {
    val grid = Array(size) { CharArray(size) }
    var start: Point = Point.ORIGIN
    var end: Point = Point.ORIGIN

    fun get(pos: Point) = grid[pos.iy][pos.ix]

    fun displayPath(node: Node<DirPoint>) {
        var path: Node<DirPoint>? = node
        while (path != null) {
            with (path.data.pos) {
                grid[iy][ix] = ARROWS[path!!.data.dir]!!
                path = path!!.parent
            }
        }

        println()
        for (r in (size - 1) downTo 0)
            println(grid[r].contentToString().replace(",", "").replace(".", " "))
        println()
    }

    fun load(input: List<String>) {
        for (r in (size - 1) downTo 0)
            for (c in 0 until size) {
                val at = input[r][c]
                val y = size - r - 1
                grid[y][c] = at
                when (at) {
                    'S' -> start = Point(c, y)
                    'E' -> end = Point(c, y)
                }
            }
    }
}