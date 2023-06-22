package org.base.advent.k2022

import org.base.advent.PuzzleReader
import java.util.*

/**
 * <a href="https://adventofcode.com/2022/day/07">Day 07</a>
 */
class Day07 : PuzzleReader {

    private val input = readLines("2022/input07.txt").map { it.split(" ") }
    private val rootDir: Dir = parseLogs(input)
    private val totalSize = rootDir.totalSize
    private val allDirs = rootDir.flatten()

    override fun solve1(): Any = allDirs.filter { it.totalSize < 100000 }.sumOf { it.totalSize }

    override fun solve2(): Any = with (30000000 - (70000000 - totalSize)) {
        allDirs.filter { it.totalSize >= this }.minOf { it.totalSize }
    }

    private fun parseLogs(logs: List<List<String>>): Dir = with(Stack<Dir>()) {
        val root = Dir()
        push(root)
        logs.forEach { line ->
            when (line.size) {
                3 -> when (line[1]) {
                    "cd" -> when (val path = line[2]) {
                        "/" -> clear().also { push(root) }
                        ".." -> pop()
                        else -> push(peek().children[path])
                    }
                }
                2 -> when (line[0]) {
                    "$" -> { /* no op */ }
                    "dir" -> peek().children[line[1]] = Dir()
                    else -> peek().files[line[1]] = line[0].toInt()
                }
            }
        }
        root
    }
}

data class Dir(val children: MutableMap<String, Dir> = mutableMapOf(),
               val files: MutableMap<String, Int> = mutableMapOf()) {

    fun flatten(): List<Dir> = children.values.toList() + children.values.flatMap { it.flatten() }
    val totalSize: Int by lazy { children.values.sumOf { it.totalSize } + files.values.sum() }
}