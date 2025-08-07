-- Creación de la base de datos
COMMENT ON DATABASE postgres IS 'This database was created by: Daimer. Docker files by Santiago. In collaboration with our team members Ricardo and Danny';

CREATE DATABASE ecommerce_final;

-- Creación de la tabla Roles
CREATE TABLE roles (
    roleid SERIAL PRIMARY KEY,
    rolename VARCHAR(100) NOT NULL
);

-- Creación de la tabla User
CREATE TABLE users (
    uniqueid SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    roleid INTEGER NOT NULL,
    CONSTRAINT FK_User_Role FOREIGN KEY (roleid) REFERENCES roles(roleid)
);

-- Creación de la tabla Products
CREATE TABLE products (
    productid SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL
);

-- Creación de la tabla DeliveryAddress
CREATE TABLE delivery (
    deliveryid SERIAL PRIMARY KEY,
    userid INTEGER NOT NULL,
    address TEXT NOT NULL,
    zipcode INTEGER,
    phonenumber VARCHAR(20),
    CONSTRAINT FK_DeliveryAddress_User FOREIGN KEY (userid) REFERENCES users(uniqueid)
);

-- Creación de la tabla Cart
CREATE TABLE cart (
    cartid SERIAL PRIMARY KEY,
    userid INTEGER NOT NULL,
    productid INTEGER NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    orderdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_Cart_Product FOREIGN KEY (productid) REFERENCES products(productid),
    CONSTRAINT FK_Cart_User FOREIGN KEY (userid) REFERENCES users(uniqueid)
);
