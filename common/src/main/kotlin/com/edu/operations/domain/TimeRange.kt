package com.edu.operations.domain

import java.time.OffsetDateTime

class TimeRange private constructor(
    val from: OffsetDateTime,
    val to: OffsetDateTime
) {
    init {
        require(!from.isAfter(to)) { "From must be before or equal to to" }
    }

    companion object {
        fun of(from: OffsetDateTime, to: OffsetDateTime): TimeRange = TimeRange(from, to)
    }
}
