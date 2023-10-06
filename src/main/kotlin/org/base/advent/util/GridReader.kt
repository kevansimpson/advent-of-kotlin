package org.base.advent.util

import java.io.ByteArrayOutputStream
import java.io.PrintStream

/** Utility to read ASCII representations of letters from {@link Grid}. */
object GridReader {
    /** Reads ASCII letters whose coordinates are oriented to the first Cartesian quadrant. */
    fun showSpell(id: String, visible: Collection<Point>, buffer: Long = 0L): String =
        spellOut(id, Grid(visible, buffer)) { g -> g.show(g::hash) }

    /** Reads ASCII letters whose coordinates are upside down. */
    fun flipSpell(id: String, visible: Collection<Point>, buffer: Long = 0L): String =
        spellOut(id, Grid(visible, buffer)) { g -> g.flip(g::hash) }

    private fun spellOut(id: String, grid: Grid, block: (Grid) -> Any?): String =
        with (grid) {
            val word = captureSysOut { block(this) }
            println(word)
            footer(id)
            read(word)
        }

    private fun read(word: String): String {
        val lines = word.split("\n").filter { it.isNotBlank() }
        val nums: List<List<Int>> = lines
            .map { it.chunked(5).map { s -> s.substring(0, 4) } }
            .map { it.map { s -> segments[s] ?: 0 } }
        val cols = Array(nums[0].size) { mutableListOf<Int>() }
        nums.forEach { list -> list.forEachIndexed { i, it -> cols[i] += it } }
        return cols.map { lookup[it] }.joinToString("")
    }

    private fun captureSysOut(block: () -> Any?): String {
        val originalSysOut = System.out
        val out = ByteArrayOutputStream(512)
        System.setOut(PrintStream(out))
        return try {
            block()
            String(out.toByteArray())
        } finally {
            System.setOut(originalSysOut)
        }
    }

    private val segments = mapOf( // indices 0,1,2,3 map to 1,2,4,8, respectively
        "#   " to 1, " #  " to 2, "##  " to 3, "  # " to 4, "# # " to 5, " ## " to 6, "### " to 7, "   #" to 8,
        "#  #" to 9, " # #" to 10, "## #" to 11, "  ##" to 12, "# ##" to 13, " ###" to 14, "####" to 15)

    // letters to list of segments in bitwise form, ordered top to bottom
    private val alphabet = mapOf(
        "A" to listOf( 6, 9, 9,15, 9, 9),
        "B" to listOf( 7, 9, 7, 9, 9, 7),
        "C" to listOf( 6, 9, 1, 1, 9, 6),
//        "D" to listOf(6,9,9,15,9,9),
        "E" to listOf(15, 1, 7, 1, 1,15),
        "F" to listOf(15, 1, 7, 1, 1, 1),
        "G" to listOf( 6, 9, 1,13, 9,14),
//        "H" to listOf(6,9,9,15,9,9),
//        "I" to listOf(6,9,9,15,9,9),
        "J" to listOf(12, 8, 8, 8, 9, 6),
        "K" to listOf( 9, 5, 3, 5, 5, 9),
        "L" to listOf( 1, 1, 1, 1, 1,15),
//        "M" to listOf(6,9,9,15,9,9),
//        "N" to listOf(6,9,9,15,9,9),
        "O" to listOf( 6, 9, 9, 9, 9, 6),
        "P" to listOf( 7, 9, 9, 7, 1, 1),
//        "Q" to listOf(6,9,9,15,9,9),
        "R" to listOf( 7, 9, 9, 7, 5, 9),
//        "S" to listOf(6,9,9,15,9,9),
//        "T" to listOf(6,9,9,15,9,9),
        "U" to listOf( 9, 9, 9, 9, 9, 6),
//        "V" to listOf(6,9,9,15,9,9),
//        "W" to listOf(6,9,9,15,9,9),
//        "X" to listOf(6,9,9,15,9,9),
//        "Y" to listOf(6,9,9,15,9,9),
        "Z" to listOf(15, 8, 4, 2, 1, 15))

    private val lookup = alphabet.entries.associate { it.value to it.key }
}