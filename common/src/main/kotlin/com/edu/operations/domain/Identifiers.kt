package com.edu.operations.domain

@JvmInline
value class UserId private constructor(val value: Long) {
    companion object {
        fun of(value: Long): UserId {
            require(value > 0) { "UserId must be positive" }
            return UserId(value)
        }
    }
}

@JvmInline
value class OperationId private constructor(val value: Long) {
    companion object {
        fun of(value: Long): OperationId {
            require(value > 0) { "OperationId must be positive" }
            return OperationId(value)
        }
    }
}
