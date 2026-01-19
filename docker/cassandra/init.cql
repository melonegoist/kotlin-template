CREATE KEYSPACE IF NOT EXISTS operations
WITH REPLICATION = {
  'class': 'SimpleStrategy',
  'replication_factor': 1
};

CREATE TABLE IF NOT EXISTS operations.operations_by_user (
  user_id bigint,
  occurred_at timestamp,
  operation_id bigint,
  amount text,
  PRIMARY KEY ((user_id), occurred_at, operation_id)
) WITH CLUSTERING ORDER BY (occurred_at DESC, operation_id ASC);
