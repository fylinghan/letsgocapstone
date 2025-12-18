DROP TABLE IF EXISTS orderitems;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS userstable;
 
CREATE TABLE IF NOT EXISTS userstable (
    email VARCHAR(100) PRIMARY KEY,      
    pw VARCHAR(50) NOT NULL
);
 
CREATE TABLE IF NOT EXISTS products (
    productid BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,      
    stock INT NOT NULL,
    price DECIMAL(7,2) NOT NULL,
    productname VARCHAR(1000) NOT NULL,
    seriesname VARCHAR(1000) NOT NULL,
    dateadded DATE NOT NULL,
    producttype ENUM('CARD', 'PACK', 'DECK') NOT NULL,
    imgpath VARCHAR(1000),
    useremail VARCHAR(100),
    FOREIGN KEY (useremail) REFERENCES userstable(email)
);
 
CREATE TABLE IF NOT EXISTS orders (
    orderid BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    shippingstatus ENUM('ORDERED', 'SHIPPED', 'DELIVERED') NOT NULL,      
    orderdate DATE NOT NULL,
    shippingaddress VARCHAR(1000) NOT NULL,
    useremail VARCHAR(100) NOT NULL,
    FOREIGN KEY (useremail) REFERENCES userstable(email)
);
 
CREATE TABLE IF NOT EXISTS orderitems (
    orderitemid BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    productid BIGINT UNSIGNED NOT NULL,
    quantity INT NOT NULL,
    orderid BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (productid) REFERENCES products(productid),
    FOREIGN KEY (orderid) REFERENCES orders(orderid)
);