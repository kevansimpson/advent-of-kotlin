package org.base.advent

import java.nio.file.Files
import java.nio.file.Path

interface PuzzleSolver {
    val name: String
        get() = this::class.java.simpleName

    fun solve1(): Any
    fun solve2(): Any
}

interface PuzzleReader : PuzzleSolver {
    val root: Path
        get() = Path.of("src", "test", "resources")

    fun readLines(input: String): List<String> = Files.readAllLines(root.resolve(input))

    fun readSingleLine(input: String): String = Files.readString(root.resolve(input))
}