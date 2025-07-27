CREATE TABLE produto (
    product_sku VARCHAR(255) NOT NULL PRIMARY KEY,
    preco DECIMAL(19, 2) NOT NULL,
    description VARCHAR(255)
);