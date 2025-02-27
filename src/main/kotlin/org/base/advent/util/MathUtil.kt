package org.base.advent.util

import java.math.BigInteger

object MathUtil {
    fun factorial(n: Long): BigInteger =
        if (n == 1L)
            BigInteger.ONE
        else
            BigInteger.valueOf(n).multiply(factorial(n - 1))

    fun lowestCommonMultiple(elements: LongArray): Long {
        var lcm = 1L
        var divisor = 2L
        while (true) {
            var counter = 0
            var divisible = false
            for (i in elements.indices) {
                if (elements[i] == 0L)
                    return 0
                else if (elements[i] < 0)
                    elements[i] = elements[i] * -1

                if (elements[i] == 1L)
                    counter++

                if (elements[i] % divisor == 0L) {
                    divisible = true
                    elements[i] = elements[i] / divisor
                }
            }
            if (divisible)
                lcm *= divisor
            else
                divisor += 1L

            if (counter == elements.size)
                return lcm
        }
    }
}