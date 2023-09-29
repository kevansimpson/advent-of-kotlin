package org.base.advent.k2019.intCode

import org.apache.commons.lang3.StringUtils.leftPad
import java.lang.IllegalStateException
import java.lang.Integer.parseInt
import java.lang.RuntimeException

data class Parameters(val program: Program) {
    internal val fullOpCode = leftPad(program.get(program.index).toString(), 5, '0')
    internal val opCode = parseInt(fullOpCode.substring(3))
    private var next = program.index + when (opCode) {
        1,2,7,8 -> 4L
        3,4,9 -> 2L
        5,6 -> 3L
        else -> throw IllegalStateException("$fullOpCode @ ${program.index}")
    }

    val a by lazy { read(1, 2) }
    val b by lazy { read(2, 1) }

    val c1 by lazy { write(1, 2) }
    val c3 by lazy { write(3, 0) }

    fun jump(condition: Boolean, distance: Long) {
        if (condition)
            next = distance
    }

    fun nextIndex(): Long = next

    private fun read(offset: Long, opCodeIndex: Int): Long =
        when (fullOpCode[opCodeIndex]) {
            '0' -> program.get(get(offset))
            '1' -> get(offset)
            '2' -> program.get(get(offset) + program.relativeBase)
            else -> throw RuntimeException("read")
        }

    private fun write(offset: Long, opCodeIndex: Int): Long =
        when (fullOpCode[opCodeIndex]) {
            '0', '1' -> get(offset)
            '2' -> get(offset) + program.relativeBase
            else -> throw RuntimeException("write")
        }

    private fun get(offset: Long): Long = program.get(program.index + offset)
}
