CREATE TABLE brands (
    brand_id UUID PRIMARY KEY,
    brand_name VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    product_id UUID PRIMARY KEY
);

CREATE TABLE prices (
    price_id UUID PRIMARY KEY,
    brand_id UUID NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_code INTEGER NOT NULL,
    product_id UUID NOT NULL,
    priority INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    curr VARCHAR(3) NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands(brand_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);