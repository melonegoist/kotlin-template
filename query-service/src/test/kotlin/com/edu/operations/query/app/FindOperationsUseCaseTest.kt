package com.edu.operations.query.app

import com.edu.operations.domain.OperationPage
import com.edu.operations.domain.OperationRepository
import com.edu.operations.domain.TimeRange
import com.edu.operations.domain.UserId
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime

class FindOperationsUseCaseTest {
    private val repository = mockk<OperationRepository>()
    private val useCase = FindOperationsUseCase(repository)

    @Test
    fun `delegates to repository`() {
        val from = OffsetDateTime.now().minusDays(1)
        val to = OffsetDateTime.now()
        val expected = OperationPage(emptyList(), null)

        every {
            repository.findByUserAndPeriod(UserId.of(1L), TimeRange.of(from, to), 100, null)
        } returns expected

        val result = useCase.execute(FindOperationsQuery(1L, from, to, 100, null))

        assertEquals(expected, result)
    }
}
