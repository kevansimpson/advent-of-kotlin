package org.base.advent.k2024

import org.base.advent.PuzzleFunction

/**
 * <a href="https://adventofcode.com/2024/day/00">Day 00</a>
 */
class Day09 : PuzzleFunction<String, Pair<Long, Long>> {

    override fun apply(input: String): Pair<Long, Long> {
        val disk1 = mutableListOf<Int>()
        val disk2 = mutableListOf<Block>()
        var fileId = 0
        var freeSpace = false
        for (ch in input.toCharArray()) {
            val num = ch.digitToInt()
            for (i in 0 until num) {
                if (freeSpace)
                    disk1.add(-1)
                else
                    disk1.add(fileId)
            }

            if (freeSpace) {
                disk2.add(Block(-1, num))
                fileId++
            }
            else
                disk2.add(Block(fileId, num))
            freeSpace = !freeSpace
        }

        defrag(disk1)
        defragBlocks(disk2)
        return checksum(disk1) to checksumBlocks(disk2)
    }

    data class Block(val id: Int, val length: Int)

    private fun checksum(disk: List<Int>): Long {
        var checksum = 0L
        for (i in disk.indices) {
            if (disk[i] >= 0)
                checksum += (i * disk[i])
        }

        return checksum
    }

    private fun checksumBlocks(disk: List<Block>): Long {
        var checksum = 0L
        var index = 0
        for (b in disk) {
            if (b.id >= 0)
                for (i in 0 until b.length)
                    checksum += index++.toLong() * b.id
            else
                index += b.length
        }

        return checksum
    }

    private fun defrag(disk: MutableList<Int>) {
        for (i in (disk.size - 1) downTo  0) {
            val free = disk.indexOf(-1)
            if (free <= i) {
                val num = disk[i]
                if (num >= 0) {
                    disk[free] = num
                    disk[i] = -1
                }
            }
        }
    }

    private fun defragBlocks(disk: MutableList<Block>) {
        for (i in (disk.size - 1) downTo  0) {
            val block = disk[i]
            if (block.id >= 0) {
                val empty = disk.firstOrNull { it.id < 0 && it.length >= block.length }
                empty?.let {
                    val index = disk.indexOf(empty)
                    if (index <= i) {
                        if (empty.length == block.length) {
                            disk[index] = block
                            disk[i] = empty
                        }
                        else {
                            val remaining = empty.length - block.length
                            disk[i] = Block(-1, block.length)
                            disk.removeAt(index)
                            disk.add(index, Block(-1, remaining))
                            disk.add(index, block)
                        }
                    }
                }
            }
        }
    }
}