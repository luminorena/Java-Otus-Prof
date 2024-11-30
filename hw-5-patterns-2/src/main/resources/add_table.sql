CREATE TABLE items (
id serial PRIMARY KEY,
title VARCHAR(255),
price DECIMAL(10, 2)
);

select * from items;

truncate table items
restart identity;