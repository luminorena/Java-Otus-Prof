CREATE TABLE if not exists phone (
id SERIAL PRIMARY KEY,
number VARCHAR(15) NOT NULL,
client_id INT NOT NULL,
FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);