package org.base.advent.util

import java.util.concurrent.atomic.AtomicLong


data class Node<T>(val data: T,
                   private val parent: Node<T>? = null,
                   val depth: Long = 0L) {

    private val children = mutableListOf<Node<T>>()
    private val context = mutableMapOf<String, Any>()

    operator fun inc(): Node<T> = this.copy(depth = this.depth + 1L)

    fun addChild(newData: T): Node<T> = Node(newData, this, depth + 1L).also { children.add(it) }

    fun contains(target: T): Boolean = data == target || parent?.contains(target) ?: false

    fun detach(reason: String) {
        while (children.isNotEmpty())
            children[0].detach(reason)
        parent?.children?.remove(this)
    }

    fun sumDown(calc: (T) -> Long): Long = calc(data) + children.sumOf { it.sumDown(calc) }

    fun sumUp(calc: (T) -> Long): Long = calc(data) + (parent?.sumUp(calc) ?: 0L)

    override fun toString(): String = "Node[$data @ %$depth w/ %${children.size} kids]"

    companion object {
        fun <N> createRootNode(root: N): Node<N> = Node(root)
    }
}

data class NodeDepthMap<T>(private val depthMap: MutableMap<T, Long> = mutableMapOf(),
                           private val currentDepth: AtomicLong = AtomicLong(-1L),
                           private val minimumDepth: AtomicLong = AtomicLong(Long.MAX_VALUE)) {

    val current: Long
        get() = currentDepth.get()

    var minimum: Long
        get() = minimumDepth.get()
        set(value) = minimumDepth.set(value)

    fun incrementCurrent(): Long = currentDepth.incrementAndGet()

    fun depthAt(point: T): Long = depthMap[point] ?: Long.MAX_VALUE

    fun setDepth(point: T, depth: Long) {
        depthMap[point] = depth
    }

    fun visited(point: T): Boolean = depthMap.containsKey(point)
}