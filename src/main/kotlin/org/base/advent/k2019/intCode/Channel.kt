package org.base.advent.k2019.intCode

import java.lang.IllegalStateException
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit

data class Channel(val name: String,
                   val queue: BlockingQueue<Long>) : BlockingQueue<Long> by queue {

    fun sendOutput(code: Long) {
        queue.put(code)
    }

    fun acceptInput(): Long = queue.poll(5, TimeUnit.MINUTES)
        ?: throw IllegalStateException("Channel $name failed to accept input!")
}