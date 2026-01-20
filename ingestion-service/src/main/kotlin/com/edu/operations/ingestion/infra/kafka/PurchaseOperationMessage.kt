package com.edu.operations.ingestion.infra.kafka

import java.time.OffsetDateTime

class PurchaseOperationMessage(
    val userId: Long,
    val operationId: Long,
    val amount: String,
    val date: OffsetDateTime
)
