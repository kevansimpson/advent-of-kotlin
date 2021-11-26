package org.base.advent.k2015

import org.base.advent.PuzzleSolver
import java.util.concurrent.atomic.AtomicReference

/**
 * <a href="https://adventofcode.com/2015/day/21">Day 21</a>
 */
class Day21 : PuzzleSolver {

    override fun solve1(): Any = bestWorst.first.cost

    override fun solve2(): Any = bestWorst.second.cost

    private val shop = Shop()

    private val bestWorst by lazy { makeOutfits() }

    private fun makeOutfits(): Pair<Outfit, Outfit> {
        val best = AtomicReference(
                Outfit(listOf(Item("best", Int.MAX_VALUE, 0, 0))))
        val worst = AtomicReference(
                Outfit(listOf(Item("worst", Int.MIN_VALUE, 0, 0))))
        for (weapon in shop.weapons) {
            for (armor in shop.armor) {
                evaluate(Outfit(listOf(weapon, armor)), best, worst)

                for (lh in shop.rings) {
                    evaluate(Outfit(listOf(weapon, armor, lh)), best, worst)

                    for (rh in shop.rings)
                        if (lh != rh)
                            evaluate(Outfit(listOf(weapon, armor, lh, rh)), best, worst)
                }
            }
        }

        return Pair(best.get(), worst.get())
    }

    private fun evaluate(outfit: Outfit,
                         best: AtomicReference<Outfit>,
                         worst: AtomicReference<Outfit>) {
        val cost = outfit.cost
        if (isWinningOutfit(outfit)) {
            if (cost < best.get().cost)
                best.set(outfit)
        }
        else if (cost > worst.get().cost)
            worst.set(outfit)
    }

    private fun isWinningOutfit(outfit: Outfit): Boolean {
        var myHP = 100
        var bossHP = BOSS_HP
        val myDmg = outfit.dmg - BOSS_ARMOR
        val bossDmg = BOSS_DAMAGE - outfit.armor

        while (myHP > 0) {
            bossHP -= myDmg
            if (bossHP <= 0)
                return true

            myHP -= bossDmg
        }
        return false
    }

    companion object {
        private const val BOSS_HP = 109
        private const val BOSS_DAMAGE = 8
        private const val BOSS_ARMOR = 2
    }
}

data class Outfit(val items: List<Item>) {
    val armor by lazy { items.sumOf { it.armor } }
    val cost by lazy { items.sumOf { it.cost } }
    val dmg by lazy { items.sumOf { it.dmg } }
}

data class Item(val name: String, val cost: Int, val dmg: Int, val armor: Int)

data class Shop(
        val weapons: List<Item> = listOf(
                Item("Dagger",       8, 4, 0),
                Item("Shortsword",  10, 5, 0),
                Item("Warhammer",   25, 6, 0),
                Item("Longsword",   40, 7, 0),
                Item("Greataxe",    74, 8, 0)),
        val armor: List<Item> = listOf(
                Item("Naked",          0, 0, 0),
                Item("Leather",       13, 0, 1),
                Item("Chainmail",     31, 0, 2),
                Item("Splintmail",    53, 0, 3),
                Item("Bandedmail",    75, 0, 4),
                Item("Platemail",    102, 0, 5)),
        val rings: List<Item> = listOf(
                Item("Damage +1",     25, 1, 0),
                Item("Damage +2",     50, 2, 0),
                Item("Damage +3",    100, 3, 0),
                Item("Defense +1",    20, 0, 1),
                Item("Defense +2",    40, 0, 2),
                Item("Defense +3",    80, 0, 3))
)