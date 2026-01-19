package com.edu.operations.ingestion.infra.persistence

import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface OperationCassandraRepository : CassandraRepository<OperationEntity, OperationKey>
