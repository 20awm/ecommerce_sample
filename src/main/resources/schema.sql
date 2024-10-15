-- Payments Table
CREATE TABLE IF NOT EXISTS payments (
    payment_id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(order_id),
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50),
    payment_status VARCHAR(50)
);

-- Shipments Table
CREATE TABLE IF NOT EXISTS shipments (
    shipment_id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(order_id),
    shipment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    carrier VARCHAR(100),
    tracking_number VARCHAR(100),
    shipment_status VARCHAR(50)
);

-- Cart Table
CREATE TABLE IF NOT EXISTS cart (
    cart_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(customer_id),
    product_id INT REFERENCES products(product_id),
    quantity INT NOT NULL,
    added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PurchaseHistory Table
CREATE TABLE IF NOT EXISTS purchase_history (
    purchase_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(customer_id),
    order_id INT REFERENCES orders(order_id),
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL
);