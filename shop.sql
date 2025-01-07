BEGIN;

DROP TABLE IF EXISTS purchases;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;

CREATE TABLE products (
id bigserial PRIMARY KEY,
name VARCHAR(255),
price DECIMAL(10, 2)
);

INSERT INTO products (name, price) VALUES
('milk', '110.00'),
('bread', '40.00'),
('rice', '300');

CREATE TABLE customers (
id bigserial PRIMARY KEY,
name VARCHAR(255)
);

INSERT INTO customers (name) VALUES
('Vasya'),
('Petya'),
('Masha'),
('Nastya'),
('Illya'),
('Oleg');

CREATE OR REPLACE FUNCTION random_timestamp(start_date TIMESTAMPTZ, end_date TIMESTAMPTZ)
    RETURNS TIMESTAMPTZ AS $$
BEGIN
    RETURN start_date + (RANDOM() * (end_date - start_date));
END;
$$
    LANGUAGE plpgsql;

CREATE TABLE purchases (
id bigserial PRIMARY KEY,
customer_id int REFERENCES customers(id) ON DELETE CASCADE,
product_id int REFERENCES products(id) ON DELETE CASCADE,
buy_time TIMESTAMPTZ DEFAULT random_timestamp('2025-01-01T00:00:00+03:00', '2025-01-31T23:59:59+03:00'),
buy_price DECIMAL(10, 2)
);

INSERT INTO purchases (customer_id, product_id, buy_price) VALUES
(1, 3, '300'),
(2, 1, '110.00'),
(3, 2, '40.00'),
(4, 2, '40.00'),
(5, 1, '110.00'),
(6, 3, '300'),
(1, 2, '40.00'),
(2, 3, '300'),
(3, 1, '110.00'),
(4, 3, '300'),
(5, 2, '40.00'),
(6, 1, '110.00');

COMMIT;
