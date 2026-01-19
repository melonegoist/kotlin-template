package com.edu.operations.query.infra.persistence

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.time.OffsetDateTime

@Table("operations_by_user")
class OperationEntity(
    @PrimaryKey
    val key: OperationKey,
    val amount: String
)

@PrimaryKeyClass
class OperationKey(
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED)
    val userId: Long,
    @PrimaryKeyColumn(name = "occurred_at", ordinal = 1, type = org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED)
    val occurredAt: OffsetDateTime,
    @PrimaryKeyColumn(name = "operation_id", ordinal = 2, type = org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED)
    val operationId: Long
)
