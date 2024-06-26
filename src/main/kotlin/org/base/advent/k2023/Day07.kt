package org.base.advent.k2023

import org.apache.commons.lang3.StringUtils
import org.base.advent.PuzzleFunction
import java.util.SortedSet

/**
 * <a href="https://adventofcode.com/2023/day/7">Day 07</a>
 */
class Day07 : PuzzleFunction<List<String>, Pair<Long, Long>> {

    override fun apply(input: List<String>): Pair<Long, Long> {
        val cards = dealCards(input)
        var sum = 0L
        cards.toSortedSet(CamelComparator(CARDS, Day07::rank))
            .forEachIndexed { index, card -> sum += (index + 1).toLong() * card.bid }
        var wildSum = 0L
        cards.toSortedSet(CamelComparator(WILDS, Day07::wildRank))
            .forEachIndexed { index, card -> wildSum += (index + 1).toLong() * card.bid }
        return sum to wildSum
    }

    private fun sum(cards: SortedSet<CamelCards>): Long {
        var sum = 0L;
        cards.forEachIndexed { index, card -> sum += (index + 1).toLong() * card.bid }
        return sum;
    }

    private fun dealCards(input: List<String>): List<CamelCards> =
        input.map {
            with(it.split(" ")) {
                CamelCards(this[0], this[1].toLong())
            }
        }

    data class CamelCards(val hand: String, val bid: Long) {
        val counts by lazy {
            hand.map { ch -> ch.toString() to StringUtils.countMatches(hand, ch) }.distinct().toMap()
        }
        val rank: String by lazy {
            format(counts.values)
        }
    }

    class CamelComparator(private val cardRanks: String,
                          private val ranker: (CamelCards) -> String) : Comparator<CamelCards> {
        override fun compare(o1: CamelCards, o2: CamelCards): Int =
            when (val comp = ranker(o1).compareTo(ranker(o2))) {
                0 -> {
                    o1.hand.forEachIndexed { index, ch ->
                        with (cardRanks.indexOf(ch)) {
                            val ix = cardRanks.indexOf(o2.hand[index])
                            if (this < ix)
                                return -1
                            else if (this > ix)
                                return 1
                        }
                    }
                    0
                }
                else -> comp
            }
    }

    companion object {
        private const val CARDS = "23456789TJQKA"
        private const val WILDS = "J23456789TQKA"

        /*
        HIGH_CARD,          // 11111
        ONE_PAIR,           // 21110
        TWO_PAIR,           // 22100
        THREE_OF_A_KIND,    // 31100
        FULL_HOUSE,         // 32000
        FOUR_OF_A_KIND,     // 41000
        FIVE_OF_A_KIND      // 50000
         */
        private fun rank(cards: CamelCards): String = cards.rank

        private fun wildRank(cards: CamelCards): String =
            if (cards.hand.contains("J"))
                with (cards.counts) {
                    val newHand = toMutableMap()
                    val jokers = newHand.remove("J")
                    if (newHand.isEmpty())
                        cards.rank
                    else {
                        val max = newHand.maxByOrNull { it.value }!!
                        newHand[max.key] = max.value + jokers!!
                        format(newHand.values)
                    }
                }
            else
                cards.rank

        private fun format(counts: Collection<Int>) =
            StringUtils.rightPad(counts.sorted().reversed().joinToString(""), 5, "0")
    }
}