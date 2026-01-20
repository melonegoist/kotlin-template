package com.edu.operations.ingestion.app

import com.edu.operations.domain.Operation
import com.edu.operations.domain.OperationRepository
import org.springframework.stereotype.Service

@Service
class RecordPurchaseOperationUseCase(
    private val repository: OperationRepository
) {
    fun execute(command: RecordPurchaseOperationCommand) {
        val operation = Operation.record(
            userId = command.userId,
            operationId = command.operationId,
            amount = command.amount,
            occurredAt = command.occurredAt
        )
        repository.save(operation)
    }
}

class RecordPurchaseOperationCommand(
    val userId: Long,
    val operationId: Long,
    val amount: String,
    val occurredAt: java.time.OffsetDateTime
)
