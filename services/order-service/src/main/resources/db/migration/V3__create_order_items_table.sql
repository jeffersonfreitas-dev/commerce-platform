CREATE TABLE order_items (
    id VARCHAR(60) PRIMARY KEY,
    product_id VARCHAR(60) NOT NULL,
    quantity NUMERIC(15, 2) NOT NULL,
    unit_price NUMERIC(15, 2) NOT NULL,
    order_id VARCHAR(60) NOT NULL,
    CONSTRAINT items_order_fk FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT items_product_fk FOREIGN KEY (product_id) REFERENCES products(id)
);