CREATE TABLE products (
    id VARCHAR(60) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    price NUMERIC(15, 2) NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL
);