package org.base.advent

import org.apache.commons.lang3.BooleanUtils
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

    fun String.csv(): List<String> = this.split(",")

    fun String.csvToInt(): List<Int> = this.csv().map { it.toInt() }
}

interface TimeSaver {
    val fullSolve: Boolean
        get() = BooleanUtils.toBoolean(System.getProperty("full"))

    fun debug(msg: String) {
        if (fullSolve)
            println(msg)
    }

    fun <T> fullOrFast(full: T, fast: T): T = if (fullSolve) full else fast

    fun <T> fastOrFull(fast: T, full: () -> T): T = if (fullSolve) full() else fast
}