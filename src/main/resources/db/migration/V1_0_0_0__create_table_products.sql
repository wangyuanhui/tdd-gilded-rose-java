CREATE TABLE IF NOT EXISTS products
(
    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(191),
    type VARCHAR(191),
    default_quality INTEGER,
    created_at  TIMESTAMP,
    best_before  TIMESTAMP
);
