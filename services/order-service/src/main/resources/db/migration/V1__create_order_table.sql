CREATE TABLE orders (
    id VARCHAR(60) PRIMARY KEY,
    customerId VARCHAR(60) NOT NULL,
    date DATE NOT NULL,
    active BOOLEAN active,
    total NUMERIC(15, 2) NOT NULL,
    status VARCHAR(20) NOT NULL
);