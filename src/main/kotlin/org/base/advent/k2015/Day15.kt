package org.base.advent.k2015

import org.base.advent.PuzzleReader
import java.util.concurrent.atomic.AtomicInteger

/**
 * <a href="https://adventofcode.com/2015/day/15">Day 15</a>
 */
class Day15 : PuzzleReader {

    private val input = readLines("2015/input15.txt")

    override fun solve1(): Any = highestScore(-1)

    override fun solve2(): Any = highestScore(500)

    private val cookbook by lazy {
        input.map { REGEX.matchEntire(it) }.associate {
            val (name, cap, d, f, t, cal) = it!!.destructured
            Pair(name, Ingredient(name, mapOf(
                    Trait.capacity to cap.toInt(),
                    Trait.durability to d.toInt(),
                    Trait.flavor to f.toInt(),
                    Trait.texture to t.toInt(),
                    Trait.calories to cal.toInt()
            )))
        }
    }

    private fun highestScore(caloricReq: Int): Int {
        val highest = AtomicInteger(Int.MIN_VALUE)
        cookEverything(cookbook.values.toList(), 0, highest, Recipe(), 100, caloricReq)
        return highest.get()
    }

    private fun cookEverything(ingredientList: List<Ingredient>, index: Int, highestScore: AtomicInteger,
                               recipe: Recipe, total: Int, caloricReq: Int) {
        if (ingredientList.size <= index) {
            if (recipe.sumTeaspoons() == 100) {
                if (caloricReq <= 0 || recipe.caloricCount(cookbook) == caloricReq) {
                    val score = recipe.score(cookbook)
                    if (score > highestScore.get())
                        highestScore.set(score)
                }
            }
            return
        }

        for (i in 0..total) {
            val current = ingredientList[index]
            recipe.ingredients[current.name] = i
            cookEverything(ingredientList, index + 1, highestScore, recipe, total - i, caloricReq)
        }
    }

    companion object {
        private val REGEX =
                "([^:]+): [a-z]+ ([-\\d]+), [a-z]+ ([-\\d]+), [a-z]+ ([-\\d]+), [a-z]+ ([-\\d]+), [a-z]+ ([-\\d]+)"
                        .toRegex()
    }
}

enum class Trait {
    capacity, durability, flavor, texture, calories
}

data class Recipe(val ingredients: MutableMap<String, Int> = mutableMapOf()) {
    fun caloricCount(cookbook: Map<String, Ingredient>): Int =
        cookbook.keys.fold(0) { count, name ->
            count + (ingredients[name]!! * cookbook[name]!!.traits[Trait.calories]!!)
        }.coerceAtLeast(0)

    fun score(cookbook: Map<String, Ingredient>): Int {
        var score = 1
        for (trait in Trait.values()) {
            if (trait != Trait.calories) {
                val value = cookbook.keys.fold(0) { count, name ->
                    count + (ingredients[name]!! * cookbook[name]!!.traits[trait]!!)
                }
                if (value <= 0) return 0
                else score *= value
            }
        }
        return score
    }

    fun sumTeaspoons(): Int = ingredients.values.sum()
}

data class Ingredient(val name: String, val traits: Map<Trait, Int>)