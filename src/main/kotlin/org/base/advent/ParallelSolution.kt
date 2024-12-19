package org.base.advent

import org.base.advent.Part.*
import org.base.advent.util.Extensions.safeGet
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import java.util.concurrent.CompletableFuture.supplyAsync
import java.util.concurrent.ExecutorService


abstract class ParallelSolution<T>(private val pool: ExecutorService)
    : PuzzleSolver<T>, PuzzleFunction<T, Pair<Any, Any>> {

    private val propertyChangeSupport by lazy { PropertyChangeSupport(this) }

    override fun apply(input: T): Pair<Any, Any> {
        propertyChangeSupport.firePropertyChange(START_EVENT, getParallelKey(name, total), null)
        propertyChangeSupport.firePropertyChange(START_EVENT, getParallelKey(name, part1), null)
        val part1 = supplyAsync({ solve1(input) }, pool)
            .whenCompleteAsync({ _, _ ->
                propertyChangeSupport.firePropertyChange(
                    STOP_EVENT, getParallelKey(name, part1), null) }, pool)

        propertyChangeSupport.firePropertyChange(START_EVENT, getParallelKey(name, part2), null)
        val part2 = supplyAsync({ solve2(input) }, pool)
            .whenCompleteAsync({ _, _ ->
                propertyChangeSupport.firePropertyChange(
                    STOP_EVENT, getParallelKey(name, part2), null) }, pool)

        try {
            return part1.safeGet() to part2.safeGet()
        }
        finally {
            propertyChangeSupport.firePropertyChange(
                    STOP_EVENT, getParallelKey(name, total), null)
        }
    }

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(listener)
    }

    companion object {
        const val START_EVENT = "start"
        const val STOP_EVENT = "stop"

        fun getParallelKey(name: String, part: Part) = "$name:${part.name}"
    }
}

enum class Part {
    part1, part2, total
}