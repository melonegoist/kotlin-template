package com.edu.operations.domain

import java.time.OffsetDateTime

class Operation private constructor(
    val userId: UserId,
    val operationId: OperationId,
    val amount: Amount,
    val occurredAt: OffsetDateTime
) {
    companion object {
        fun record(
            userId: Long,
            operationId: Long,
            amount: String,
            occurredAt: OffsetDateTime
        ): Operation {
            require(!occurredAt.isAfter(OffsetDateTime.now())) { "Operation date cannot be in the future" }
            return Operation(
                userId = UserId.of(userId),
                operationId = OperationId.of(operationId),
                amount = Amount.of(amount),
                occurredAt = occurredAt
            )
        }

        fun restore(
            userId: Long,
            operationId: Long,
            amount: String,
            occurredAt: OffsetDateTime
        ): Operation = Operation(
            userId = UserId.of(userId),
            operationId = OperationId.of(operationId),
            amount = Amount.of(amount),
            occurredAt = occurredAt
        )
    }
}
