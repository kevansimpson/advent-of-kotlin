package org.base.advent.k2021

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2021/day/4">Day 4</a>
 */
class Day04 : PuzzleFunction<List<String>, Pair<Int, Int>> {
    override fun apply(input: List<String>): Pair<Int, Int> {
        val numbers = input[0].split(",").map { it.trim().toInt() }
        val bingoCards = input.drop(1).fold(listOf<MutableList<String>>()) { list, line ->
                if (StringUtils.isBlank(line))
                    list + listOf(mutableListOf())
                else {
                    list.last().add(line)
                    list
                }
            }.map { rows ->
                BingoCard(rows.map { r ->
                    r.split(" ").filter { s -> s.isNotBlank() }
                        .map { it.trim().toInt() }.toIntArray() to BooleanArray(5) { false }
                })
            }

        val winners = with (bingoCards.map { it.copy() }) {
            numbers.fold(mutableListOf<BingoCard>()) { list, num ->
                forEach { card ->
                    if (!card.hasWon()) {
                        card.rows.forEach { pair ->
                            val at = pair.first.indexOf(num)
                            if (at >= 0)
                                pair.second[at] = true
                        }
                        if (card.isWinner()) {
                            card.winningNumber = num
                            list.add(card)
                        }
                    }
                }
                list
            }
        }
        return winners.first().score() to winners.last().score()
    }
}

data class BingoCard(val rows: List<Pair<IntArray, BooleanArray>>, var winningNumber: Int = -1) {

    fun score(): Int = sumUnmarked() * winningNumber

    fun hasWon(): Boolean = winningNumber >= 0

    fun isWinner(): Boolean {
        val markedRows = rows.map { it.second }
        markedRows.forEach { mr ->
            if (mr.count { b -> b } == 5)
                return true
        }

        (0..4).map { index -> markedRows.map { it[index] } }.forEach { col ->
            if (col.count { b -> b } == 5)
                return true
        }
        return false
    }

    private fun sumUnmarked(): Int = rows.fold(0) { sum, row ->
        sum + row.first.filterIndexed { index, _ -> !(row.second[index]) }.sum()
    }
}
