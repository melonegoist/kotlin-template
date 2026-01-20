package com.edu.operations.domain

@JvmInline
value class Amount private constructor(val value: String) {
    companion object {
        fun of(raw: String): Amount {
            require(raw.isNotBlank()) { "Amount must not be blank" }
            return Amount(raw.trim())
        }
    }
}
