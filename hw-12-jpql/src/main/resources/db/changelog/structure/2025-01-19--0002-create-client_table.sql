CREATE TABLE if not exists client (
id SERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL,
address_id INT UNIQUE,
FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE
);