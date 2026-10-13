CREATE TABLE products (
    id VARCHAR(60) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(300) NOT NULL,
    price NUMERIC(15, 2) NOT NULL,
    original_price NUMERIC(15, 2) NOT NULL,
    image TEXT NOT NULL,
    galery TEXT[] NOT NULL,
    category VARCHAR(255) NOT NULL,
    stock NUMERIC(15, 2) NOT NULL,
    rating NUMERIC(3, 2) NOT NULL,
    reviews INTEGER NOT NULL,
    tags TEXT[] NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);