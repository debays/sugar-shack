-- CREATE TABLE flyway_schema_history (
--     installed_rank INT NOT NULL,
--     version VARCHAR(50),
--     description VARCHAR(200) NOT NULL,
--     type VARCHAR(20) NOT NULL,
--     script VARCHAR(1000) NOT NULL,
--     checksum INT,
--     installed_by VARCHAR(100) NOT NULL,
--     installed_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     execution_time INT NOT NULL,
--     success BOOLEAN NOT NULL
-- );

CREATE TABLE IF NOT EXISTS maple_syrup (
    id BIGINT PRIMARY KEY,
    deleted BOOLEAN,
    type VARCHAR(255),
    name VARCHAR(255),
    description VARCHAR(255),
    price DOUBLE PRECISION,
    stock INT,
    image VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cart (
    id BIGINT PRIMARY KEY,
    deleted BOOLEAN,
    createdAt TIMESTAMP WITH TIME ZONE,
    totalPrice DOUBLE PRECISION,
    username VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cart_item (
    id BIGINT PRIMARY KEY,
    deleted BOOLEAN,
    maple_syrup_id BIGINT,
    quantity INT,
    price DOUBLE PRECISION,
    cart_id BIGINT,
    FOREIGN KEY (maple_syrup_id) REFERENCES maple_syrup(id),
    FOREIGN KEY (cart_id) REFERENCES cart(id)
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY,
    orderDate TIMESTAMP WITH TIME ZONE,
    deleted BOOLEAN,
    customerName VARCHAR(255),
    customerAddress VARCHAR(255),
    customerMail VARCHAR(255),
    customerPhone VARCHAR(255),
    orderStatus VARCHAR(255),
    totalPrice DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS order_item (
    id BIGINT PRIMARY KEY,
    deleted BOOLEAN,
    order_id BIGINT,
    maple_syrup_id BIGINT,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (maple_syrup_id) REFERENCES maple_syrup(id)
);