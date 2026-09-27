CREATE OR REPLACE FUNCTION product_search_vector(name text, description text)
RETURNS tsvector AS $$
    SELECT
        setweight(to_tsvector('portuguese', coalesce(name, '')), 'A') ||
        setweight(to_tsvector('portuguese', coalesce(description, '')), 'B');
$$ LANGUAGE SQL IMMUTABLE;

CREATE INDEX idx_product_fts
ON products
USING GIN (product_search_vector(name, description));