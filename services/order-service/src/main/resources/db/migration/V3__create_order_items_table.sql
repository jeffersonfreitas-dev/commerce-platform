CREATE TABLE order_items (
    id VARCHAR(60) PRIMARY KEY,
    productId VARCHAR(60) NOT NULL,
    quantity NUMERIC(15, 2) NOT NULL,
    unit_price NUMERIC(15, 2) NOT NULL,
    orderId VARCHAR(60) NOT NULL,
    CONSTRAINT items_order_fk FOREIGN KEY (orderId) REFERENCES orders(id)
);