package com.edu.operations.query.infra.grpc

import com.edu.operations.proto.OperationsRequest
import com.edu.operations.proto.OperationsResponse
import com.edu.operations.proto.OperationsServiceGrpc
import com.edu.operations.query.config.PagingProperties
import com.edu.operations.query.app.FindOperationsQuery
import com.edu.operations.query.app.FindOperationsUseCase
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory

@GrpcService
class OperationsGrpcService(
    private val useCase: FindOperationsUseCase,
    private val pagingProperties: PagingProperties
) : OperationsServiceGrpc.OperationsServiceImplBase() {
    private val logger = LoggerFactory.getLogger(OperationsGrpcService::class.java)

    override fun getOperations(
        request: OperationsRequest,
        responseObserver: StreamObserver<OperationsResponse>
    ) {
        try {
            val pageSize = request.pageSize.takeIf { it > 0 } ?: pagingProperties.defaultSize
            val result = useCase.execute(
                FindOperationsQuery(
                    userId = request.userId,
                    from = request.from.toOffsetDateTime(),
                    to = request.to.toOffsetDateTime(),
                    pageSize = pageSize.coerceAtMost(pagingProperties.maxSize),
                    pagingState = request.pagingState.ifBlank { null }
                )
            )

            val response = OperationsResponse.newBuilder()
                .addAllOperations(result.items.map { it.toProto() })
                .setNextPagingState(result.nextPagingState ?: "")
                .build()

            responseObserver.onNext(response)
            responseObserver.onCompleted()
        } catch (ex: Exception) {
            logger.error(\"Failed to fetch operations\", ex)
            responseObserver.onError(ex)
        }
    }
}
