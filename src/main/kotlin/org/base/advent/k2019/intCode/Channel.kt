package org.base.advent.k2019.intCode

import java.lang.IllegalStateException
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit

data class Channel(private val queue: BlockingQueue<Long>) : BlockingQueue<Long> by queue {

    fun send(code: Long) {
        queue.put(code)
    }

    fun accept(): Long = queue.poll(15, TimeUnit.SECONDS)
        ?: throw IllegalStateException("Channel failed to accept input!")

    companion object {
        fun newChannel(capacity: Int, vararg signals: Long): Channel {
            val c = Channel(ArrayBlockingQueue(capacity))
            signals.forEach { c.send(it) }
            return c
        }
    }
}