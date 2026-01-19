package com.edu.operations.query.app

import com.edu.operations.domain.OperationPage
import com.edu.operations.domain.OperationRepository
import com.edu.operations.domain.TimeRange
import com.edu.operations.domain.UserId
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class FindOperationsUseCase(
    private val repository: OperationRepository
) {
    fun execute(query: FindOperationsQuery): OperationPage {
        val range = TimeRange.of(query.from, query.to)
        val userId = UserId.of(query.userId)
        return repository.findByUserAndPeriod(
            userId = userId,
            range = range,
            pageSize = query.pageSize,
            pagingState = query.pagingState
        )
    }
}

class FindOperationsQuery(
    val userId: Long,
    val from: OffsetDateTime,
    val to: OffsetDateTime,
    val pageSize: Int,
    val pagingState: String?
)
