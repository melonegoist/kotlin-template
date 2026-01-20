package com.edu.operations.query.infra.grpc

import com.edu.operations.domain.Operation
import com.google.protobuf.Timestamp
import java.time.OffsetDateTime
import java.time.ZoneOffset

fun OffsetDateTime.toTimestamp(): Timestamp =
    Timestamp.newBuilder()
        .setSeconds(this.toEpochSecond())
        .setNanos(this.nano)
        .build()

fun Timestamp.toOffsetDateTime(): OffsetDateTime =
    OffsetDateTime.ofInstant(java.time.Instant.ofEpochSecond(seconds, nanos.toLong()), ZoneOffset.UTC)

fun Operation.toProto(): com.edu.operations.proto.Operation =
    com.edu.operations.proto.Operation.newBuilder()
        .setUserId(userId.value)
        .setOperationId(operationId.value)
        .setAmount(amount.value)
        .setOccurredAt(occurredAt.toTimestamp())
        .build()
