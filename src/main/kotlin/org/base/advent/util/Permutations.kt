package org.base.advent.util

import java.util.*
import java.util.stream.IntStream
import java.util.stream.LongStream
import java.util.stream.Stream
import kotlin.collections.ArrayList

object Permutations {

    fun nthTriangleNumber(n: Int) = (n * (n + 1)) / 2

    fun permutations(str: String): Stream<String> =
            if (str.isBlank()) Stream.of("")
            else
                IntStream.range(0, str.length).boxed()
                        .flatMap { i ->
                            permutations(str.substring(0, i) + str.substring(i + 1))
                                .map { t -> str[i] + t } }

    fun <T> permutations(list: List<T>): Stream<List<T>> =
            LongStream.range(0, factorial(list.size)).mapToObj { i -> perm(i, list) }

    private fun factorial(num: Int) = LongStream.rangeClosed(2L, num.toLong()).reduce(1L) { x, y -> x * y }

    private fun <T> perm(count: Long, input: List<T>) =
            perm(count, LinkedList(input), ArrayList())

    private fun <T> perm(count: Long, input: LinkedList<T>, output: MutableList<T>): List<T> {
        if (input.isNullOrEmpty()) return output
        val fact = factorial(input.size - 1)
        output.add(input.removeAt((count / fact).toInt()))
        return perm(count % fact, input, output)
    }
}