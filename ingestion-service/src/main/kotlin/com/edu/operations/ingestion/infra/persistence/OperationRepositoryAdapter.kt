package com.edu.operations.ingestion.infra.persistence

import com.edu.operations.domain.Operation
import com.edu.operations.domain.OperationPage
import com.edu.operations.domain.OperationRepository
import com.edu.operations.domain.TimeRange
import com.edu.operations.domain.UserId
import org.springframework.stereotype.Component

@Component
class OperationRepositoryAdapter(
    private val repository: OperationCassandraRepository
) : OperationRepository {
    override fun save(operation: Operation) {
        repository.save(
            OperationEntity(
                key = OperationKey(
                    userId = operation.userId.value,
                    occurredAt = operation.occurredAt,
                    operationId = operation.operationId.value
                ),
                amount = operation.amount.value
            )
        )
    }

    override fun findByUserAndPeriod(
        userId: UserId,
        range: TimeRange,
        pageSize: Int,
        pagingState: String?
    ): OperationPage {
        throw UnsupportedOperationException("Read operations are not supported in ingestion service")
    }
}
