package com.edu.operations.ingestion.infra.kafka

import com.edu.operations.ingestion.app.RecordPurchaseOperationCommand
import com.edu.operations.ingestion.app.RecordPurchaseOperationUseCase
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class PurchaseOperationListener(
    private val useCase: RecordPurchaseOperationUseCase
) {
    private val logger = LoggerFactory.getLogger(PurchaseOperationListener::class.java)

    @KafkaListener(
        topics = ["\${app.kafka.topics.purchase-operations}"],
        containerFactory = "purchaseKafkaListenerContainerFactory"
    )
    fun handle(message: PurchaseOperationMessage, acknowledgment: Acknowledgment) {
        logger.info("Received purchase operation with id={}, userId={}", message.operationId, message.userId)
        useCase.execute(
            RecordPurchaseOperationCommand(
                userId = message.userId,
                operationId = message.operationId,
                amount = message.amount,
                occurredAt = message.date
            )
        )
        acknowledgment.acknowledge()
    }
}
