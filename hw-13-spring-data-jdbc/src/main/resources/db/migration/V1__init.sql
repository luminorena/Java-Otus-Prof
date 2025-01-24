create table CART (
    id bigserial primary key,
    price decimal(16,4),
    discount decimal(16,4),
    short_name varchar(100),
    description varchar(300)
);

