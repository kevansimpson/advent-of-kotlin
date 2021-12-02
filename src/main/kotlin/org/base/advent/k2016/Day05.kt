package org.base.advent.k2016

import org.base.advent.PuzzleSolver
import org.base.advent.TimeSaver
import org.base.advent.util.Text.nextHash

/**
 * <a href="https://adventofcode.com/2016/day/5">Day 5</a>
 */
class Day05 : PuzzleSolver, TimeSaver {

    private val input = "uqwqemis"

    override fun solve1(): Any = if (fullSolve) hackDoor1() else fastHackDoor1()

    override fun solve2(): Any = if (fullSolve) hackDoor2(first8.last().second) else fastHackDoor2()

    private fun hackDoor1(): String = // +86s!
        mergeHashIndexPairs((0..7).fold(Pair(listOf<Pair<String, Long>>(), 0L)) { listIndex, _ ->
            val next = nextHash(input, PREFIX, listIndex.second)
            Pair(listIndex.first + listOf(next), next.second + 1L)
        }.first)

    private fun fastHackDoor1(): String = mergeHashIndexPairs(first8)

    private fun mergeHashIndexPairs(pairs: List<Pair<String, Long>>): String =
            pairs.map { it.first[5] }.joinToString("")

    private val initialPswd by lazy {
        val pswd = charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
        for (pair in first8) {
            if (inRange(pair.first[5]))
                pswd[pair.first[5].digitToInt()] = pair.first[6]
        }
        pswd
    }

    private fun hackDoor2(start: Long = 0L): String {
        val pswd = initialPswd.copyOf()
        var index = start
        while (String(pswd).contains(" ")) {
            val (nextHash, nextIndex) = nextHash(input, PREFIX, index)
            if (inRange(nextHash[5])) {
                val digit = nextHash[5].digitToInt()
                if (pswd[digit] == ' ')
                    pswd[digit] = nextHash[6]
            }
            index = nextIndex + 1L
        }
        return String(pswd)
    }

    private fun inRange(ch: Char): Boolean = VALID_INDICES.contains(ch)

    private fun fastHackDoor2(): String {
        val pswd = initialPswd.copyOf()
        next5.forEach { pair -> pswd[pair.first[5].digitToInt()] = pair.first[6] }
        return String(pswd)
    }

    companion object {
        private const val PREFIX = "00000"
        private const val VALID_INDICES = "01234567"

        // hashes with prefix and indices, from prior runs; cached for `TimeSaver`
        private val first8 = listOf(
                "00000191970e97b86ecd2220e76d86b2" to 4515059L,
                "00000a1568b97dfc4736c4248df549b3" to 6924074L,
                "00000312234ca27718d52476a44c257c" to 8038154L,
                "00000064ec7123bedfc9ff00cc4f55f2" to 13432968L,
                "0000091c9c2cd243304328869af7bab2" to 13540621L,
                "0000096753dd21d352853f1d97e19d01" to 14095580L,
                "00000a220003ca08164ab5fbe0b7c08f" to 14821988L,
                "00000aaa1e7e216d6fb95a53fde7a594" to 16734551L)
        private val next5 = listOf(
                "000002457920bc00c2bd4d769a3da01c" to 17743256L,
                "000005074f875107f82b4ffb39a1fbf0" to 19112977L,
                "0000049d19713e17d7d93e9b1f02c856" to 20616595L,
                "000006c0b6e2bfeabd18eb400b3aecf7" to 21658552L,
                "000007d44ea65d0437b810035fec92f2" to 26326685L)
    }
}