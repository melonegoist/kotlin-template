package com.edu.operations.domain

interface OperationRepository {
    fun save(operation: Operation)

    fun findByUserAndPeriod(
        userId: UserId,
        range: TimeRange,
        pageSize: Int,
        pagingState: String?
    ): OperationPage
}

data class OperationPage(
    val items: List<Operation>,
    val nextPagingState: String?
)
