package org.base.advent.util

import org.base.advent.util.Extensions.merge
import org.base.advent.util.Extensions.size
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/** Unit tests for extension methods. */
class ExtensionsTest {
    @Test
    fun testLongRangeSize() {
        assertEquals(0, LongRange.EMPTY.size())
        assertEquals(1, (0L..0L).size())
        assertEquals(5, (0L..4L).size())
        assertEquals(1, (0L until 1L).size())
    }

    @Test
    fun testLongRangeMerge() {
        // no overlap, return both ranges
        assertEquals((-5L..0L) to (5L..10L), (-5L..0L).merge(5L..10L))
        // a.last > b.first, merge to a.first..b.last
        assertEquals((-5L..10L) to null, (-5L..5L).merge(0L..10L))
        // a.first < b.first && a.last > b.last, merge to a
        assertEquals((-5L..20L) to null, (-5L..20L).merge(5L..10L))
        // a.first > b.first && a.last < b.last, merge to b
        assertEquals((-5L..20L) to null, (5L..10L).merge(-5L..20L))
        // a.first < b.last, merge to b.first..a.last
        assertEquals((-5L..20L) to null, (5L..20L).merge(-5L..10L))
        // no overlap, return both ranges
        assertEquals((-5L..0L) to (5L..10L), (5L..10L).merge(-5L..0L))
    }
}