package com.edu.operations.query.infra.persistence

import com.edu.operations.domain.Operation
import com.edu.operations.domain.OperationPage
import com.edu.operations.domain.OperationRepository
import com.edu.operations.domain.TimeRange
import com.edu.operations.domain.UserId
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.core.query.Criteria
import org.springframework.data.cassandra.core.query.Query
import org.springframework.data.domain.PageRequest
import org.springframework.data.cassandra.core.query.CassandraPageRequest
import org.springframework.stereotype.Component
import java.nio.ByteBuffer
import java.util.Base64

@Component
class OperationRepositoryAdapter(
    private val cassandraTemplate: CassandraTemplate
) : OperationRepository {
    override fun save(operation: Operation) {
        throw UnsupportedOperationException("Write operations are not supported in query service")
    }

    override fun findByUserAndPeriod(
        userId: UserId,
        range: TimeRange,
        pageSize: Int,
        pagingState: String?
    ): OperationPage {
        val basePage = PageRequest.of(0, pageSize)
        val pageRequest = pagingState
            ?.takeIf { it.isNotBlank() }
            ?.let { CassandraPageRequest.of(basePage, decodePagingState(it)) }
            ?: basePage

        val query = Query.query(
            Criteria.where("user_id").`is`(userId.value)
        ).and(
            Criteria.where("occurred_at").greaterThanOrEquals(range.from)
        ).and(
            Criteria.where("occurred_at").lessThanOrEquals(range.to)
        ).pageRequest(pageRequest)

        val slice = cassandraTemplate.slice(query, OperationEntity::class.java)
        val items = slice.content.map {
            Operation.restore(
                userId = it.key.userId,
                operationId = it.key.operationId,
                amount = it.amount,
                occurredAt = it.key.occurredAt
            )
        }

        val nextPagingState = (slice.nextPageable() as? CassandraPageRequest)
            ?.pagingState
            ?.let { encodePagingState(it) }

        return OperationPage(items, nextPagingState)
    }

    private fun decodePagingState(state: String): ByteBuffer =
        ByteBuffer.wrap(Base64.getDecoder().decode(state))

    private fun encodePagingState(state: ByteBuffer): String {
        val buffer = state.asReadOnlyBuffer()
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return Base64.getEncoder().encodeToString(bytes)
    }
}
