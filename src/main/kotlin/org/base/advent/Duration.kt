package org.base.advent

import org.apache.commons.lang3.StringUtils
import java.util.*
import java.util.concurrent.TimeUnit

data class Duration(val time: Long, val unit: TimeUnit = TimeUnit.NANOSECONDS) {
    /** Equality is based on `readableTime`. */
    override fun equals(other: Any?): Boolean = when (other) {
        is Duration -> StringUtils.equals(readableTime(this), readableTime(other))
        else -> false
    }

    /** Hashcode is based on `readableTime`. */
    override fun hashCode(): Int = readableTime(this).hashCode() * 13

    /** Companion object with helper methods for readable duration. */
    companion object DurationHelper {
        private val timeUnits = mapOf(
                Pair(TimeUnit.MICROSECONDS, "micros"), Pair(TimeUnit.MILLISECONDS, "millis"), Pair(TimeUnit.NANOSECONDS, "nanos"))

        fun readableTime(duration: Duration): String = when (duration.unit) {
            TimeUnit.NANOSECONDS ->
                when {
                    duration.time > 1000000 -> "${TimeUnit.NANOSECONDS.toMillis(duration.time)} ${timeUnits[TimeUnit.MILLISECONDS]}"
                    duration.time > 1000 -> "${TimeUnit.NANOSECONDS.toMicros(duration.time)} ${timeUnits[TimeUnit.MICROSECONDS]}"
                    else -> "${duration.time} ${timeUnits[duration.unit]}"
                }
            else -> "${duration.time} ${timeUnits[duration.unit] ?: deflate(duration.unit)}"
        }

        private fun deflate(unit: TimeUnit) = unit.toString().lowercase(Locale.getDefault())
    }
}
