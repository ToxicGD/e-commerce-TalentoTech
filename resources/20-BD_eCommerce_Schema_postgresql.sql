-- Creación de la base de datos
COMMENT ON DATABASE postgres IS 'This database was created by: Daimer. Docker files by Santiago. In collaboration with our team members Ricardo and Danny';

CREATE DATABASE ecommerce_final;

-- Creación de la tabla Roles
CREATE TABLE Roles (
    RoleId SERIAL PRIMARY KEY,
    role_Name VARCHAR(100) NOT NULL
);

-- Creación de la tabla User
CREATE TABLE Users (
    UniqueID SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    RoleId INTEGER NOT NULL,
    CONSTRAINT FK_User_Role FOREIGN KEY (RoleId) REFERENCES Roles(RoleId)
);

-- Creación de la tabla Products
CREATE TABLE Products (
    ProductId SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    img VARCHAR(500),
    price DECIMAL(10,2) NOT NULL
);

-- Creación de la tabla DeliveryAddress
CREATE TABLE DeliveryAddress (
    DeliveryID SERIAL PRIMARY KEY,
    UserId INTEGER NOT NULL,
    address TEXT NOT NULL,
    zipcode VARCHAR(20),
    phoneNumber VARCHAR(20),
    description VARCHAR(100),
    CONSTRAINT FK_DeliveryAddress_User FOREIGN KEY (UserId) REFERENCES Users(UniqueID)
);

-- Creación de la tabla Cart
CREATE TABLE Cart (
    CartId SERIAL PRIMARY KEY,
    productId INTEGER NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT FK_Cart_Product FOREIGN KEY (productId) REFERENCES Products(ProductId)
);

-- Creación de la tabla Orders
CREATE TABLE Orders (
    OrderId SERIAL PRIMARY KEY,
    ProductId INTEGER NOT NULL,
    CartId INTEGER NOT NULL,
    DeliveryID INTEGER NOT NULL,
    orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estimatedDeliveryDate DATE,
    CONSTRAINT FK_Orders_Product FOREIGN KEY (ProductId) REFERENCES Products(ProductId),
    CONSTRAINT FK_Orders_Cart FOREIGN KEY (CartId) REFERENCES Cart(CartId),
    CONSTRAINT FK_Orders_Delivery FOREIGN KEY (DeliveryID) REFERENCES DeliveryAddress(DeliveryID)
);
