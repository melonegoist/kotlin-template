package com.edu.operations.domain

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime

class DomainValidationTest {
    @Test
    fun `amount rejects blanks`() {
        assertThrows(IllegalArgumentException::class.java) { Amount.of(" ") }
    }

    @Test
    fun `time range requires from before to`() {
        val now = OffsetDateTime.now()
        assertThrows(IllegalArgumentException::class.java) { TimeRange.of(now.plusDays(1), now) }
    }

    @Test
    fun `operation accepts valid payload`() {
        val now = OffsetDateTime.now().minusMinutes(1)
        assertDoesNotThrow {
            Operation.record(1L, 10L, "100.00", now)
        }
    }
}
