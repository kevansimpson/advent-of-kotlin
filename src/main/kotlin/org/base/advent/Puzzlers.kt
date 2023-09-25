package org.base.advent

import org.apache.commons.lang3.BooleanUtils
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Supplier

interface Named {
    val name: String
        get() = this::class.java.simpleName
}
interface PuzzleSolver<T> : Named {
    fun solve1(input: T): Any
    fun solve2(input: T): Any
}

interface PuzzleFunction<T, R> : java.util.function.Function<T, R>, Named
interface PuzzleSupplier<T> : Supplier<T>, Named

interface PuzzleReader {
    val root: Path
        get() = Path.of("src", "test", "resources")

    fun readIntLines(input: String): List<Int> = readLines(input).map { it.toInt() }

    fun readLines(input: String): List<String> = Files.readAllLines(root.resolve(input))

    fun readSingleLine(input: String): String = Files.readString(root.resolve(input))

    fun String.csv(delims: String = ","): List<String> = this.split(delims)

    fun String.csvToInt(): List<Int> = this.csv().map { it.toInt() }

    fun String.csvToLong(): List<Long> = this.csv().map { it.toLong() }
}

interface TimeSaver {
    var fullSolve: Boolean
        get() = BooleanUtils.toBoolean(System.getProperty("full"))
        set(value) {
            System.setProperty("full", value.toString())
        }

    fun debug(msg: String) {
        if (fullSolve)
            println(msg)
    }

    fun <T> fullOrFast(full: T, fast: T): T = if (fullSolve) full else fast

    fun <T> fastOrFull(fast: T, full: () -> T): T = if (fullSolve) full() else fast
}