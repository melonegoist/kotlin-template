package com.edu.operations.query.infra.grpc

import com.edu.operations.domain.Operation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime
import java.time.ZoneOffset

class GrpcMappersTest {
    @Test
    fun `timestamp conversion is reversible`() {
        val time = OffsetDateTime.of(2024, 1, 10, 12, 30, 45, 123_000_000, ZoneOffset.UTC)
        val restored = time.toTimestamp().toOffsetDateTime()

        assertEquals(time.toInstant(), restored.toInstant())
    }

    @Test
    fun `operation converts to proto`() {
        val operation = Operation.restore(1L, 2L, "10.00", OffsetDateTime.now().minusMinutes(1))
        val proto = operation.toProto()

        assertEquals(operation.userId.value, proto.userId)
        assertEquals(operation.operationId.value, proto.operationId)
        assertEquals(operation.amount.value, proto.amount)
    }
}
