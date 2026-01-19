package com.edu.operations.ingestion.app

import com.edu.operations.domain.OperationRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime

class RecordPurchaseOperationUseCaseTest {
    private val repository = mockk<OperationRepository>(relaxed = true)
    private val useCase = RecordPurchaseOperationUseCase(repository)

    @Test
    fun `records operation in repository`() {
        val now = OffsetDateTime.now().minusMinutes(1)

        useCase.execute(
            RecordPurchaseOperationCommand(
                userId = 10L,
                operationId = 99L,
                amount = "42.00",
                occurredAt = now
            )
        )

        verify(exactly = 1) { repository.save(any()) }
    }
}
