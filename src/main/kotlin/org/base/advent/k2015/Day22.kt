package org.base.advent.k2015

import org.base.advent.PuzzleSupplier
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.max

/**
 * <a href="https://adventofcode.com/2015/day/22">Day 22</a>
 */
class Day22 : PuzzleSupplier<Pair<Int, Int>> {
    override fun get(): Pair<Int, Int> =
        fightBoss(newQueue(), false) to fightBoss(newQueue(), true)

    private fun fightBoss(wizards: PriorityQueue<Wizard>, hard: Boolean): Int {
        val lowestManaSpent = AtomicInteger(Int.MAX_VALUE)
        while (!wizards.isEmpty()) {
            val wiz = wizards.poll()
            if (hard) {
                wiz.hitPoints -= 1
                if (wiz.hitPoints <= 0)
                    continue
            }
            wiz.applyEffects()
            for (spell in Spell.values()) {
                if (wiz.canCast(spell)) {
                    val copy = wiz.copy(activeEffects = wiz.activeEffects.toMutableMap())
                    copy.cast(spell)
                    copy.applyEffects()

                    if (copy.bossHP <= 0) {
                        if (copy.totalManaSpent < lowestManaSpent.get()) {
                            lowestManaSpent.set(copy.totalManaSpent)
                            wizards.removeIf { it.totalManaSpent > lowestManaSpent.get() }
                        }
                    }
                    else {
                        copy.hitPoints -= max(1, BOSS_DAMAGE - copy.armor)
                        if (copy.hitPoints > 0 && copy.mana > 0 && copy.totalManaSpent < lowestManaSpent.get())
                            wizards.add(copy)
                    }
                }
            }
        }
        return lowestManaSpent.get()
    }

    companion object {
        private const val BOSS_HP = 71
        private const val BOSS_DAMAGE = 10

        private fun newQueue(): PriorityQueue<Wizard> {
            val queue = PriorityQueue<Wizard> { w1, w2 -> w2.totalManaSpent.compareTo(w1.totalManaSpent) }
            queue.add(Wizard(50, 500, BOSS_HP))
            return queue
        }
    }
}

data class Wizard(var hitPoints: Int, var mana: Int, var bossHP: Int,
                  var armor: Int = 0, var totalManaSpent: Int = 0,
                  val activeEffects: MutableMap<Spell, Int> = mutableMapOf()) {

    fun applyEffects() {
        if (!activeEffects.containsKey(Spell.Shield) || activeEffects[Spell.Shield] == 0)
            armor = 0

        for (spell in activeEffects.keys) {
            val duration = getDuration(spell)
            if (duration > 0) {
                activeEffects[spell] = duration - 1
                when (spell) {
                    Spell.Shield -> armor = 7
                    Spell.Poison -> bossHP -= 3
                    Spell.Recharge -> mana += 101
                    else -> throw IllegalStateException(spell.name)
                }
            }
        }
    }

    fun canCast(spell: Spell): Boolean = mana >= spell.manaCost && (spell.ordinal < 2 || getDuration(spell) == 0)

    fun cast(spell: Spell) {
        mana -= spell.manaCost
        totalManaSpent += spell.manaCost
        when (spell) {
            Spell.MagicMissile -> bossHP -= 4
            Spell.Drain -> {
                bossHP -= 2
                hitPoints += 2
            }
            else -> activeEffects[spell] = spell.effectLasts
        }
    }

    private fun getDuration(spell: Spell) = activeEffects.computeIfAbsent(spell) { 0 }
}

enum class Spell(val manaCost: Int, val effectLasts: Int) {
    MagicMissile(53, 0),    // 4 dmg
    Drain(73, 0),           // 2 dmg + 2 HP
    Shield(113, 6),         // +7 armor / turn
    Poison(173, 6),         // 3 dmg / turn
    Recharge(229, 5);       // +101 mana / turn

}