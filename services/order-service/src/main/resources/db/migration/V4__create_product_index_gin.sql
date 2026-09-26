CREATE INDEX idx_product_fts
ON product
USING GIN (
    setweight(
        to_tsvector('portuguese', coalesce(name, '')),
        'A'
    ) ||
    setweight(
        to_tsvector('portuguese', coalesce(description, '')),
        'B'
    )
);