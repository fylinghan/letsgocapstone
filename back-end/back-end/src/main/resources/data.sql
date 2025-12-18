-- Insert users
INSERT INTO userstable (email, pw) VALUES
('alice@example.com', 'password123'),
('bob@example.com', 'securepass'),
('charlie@example.com', 'charliepw');
 
-- Insert products
INSERT INTO products (stock, price, productname, seriesname, dateadded, producttype, imgpath, useremail) VALUES
-- Packs
(100, 19.99, 'Destined Pack', 'Destined Series', '2025-11-02', 'PACK', '/images/pack/destined.png', 'alice@example.com'),
(80, 24.99, 'Stellar Pack', 'Stellar Series', '2025-11-15', 'PACK', '/images/pack/stellar.png', 'alice@example.com'),
(50, 29.99, 'Surging Pack', 'Surging Series', '2025-11-28', 'PACK', '/images/pack/surging.png', 'bob@example.com'),
 
-- Deck
(20, 49.99, 'Corvi Deck', 'Corvi Series', '2025-12-07', 'DECK', '/images/deck/corvi.jpg', 'bob@example.com'),
 
-- Cards
(200, 5.99, 'Bulbasaur Card', 'Pokemon Series', '2025-09-22', 'CARD', '/cards/bulbasaur.jpg', 'charlie@example.com'),
(150, 6.99, 'Pikachu Card', 'Pokemon Series', '2025-10-05', 'CARD', '/cards/pikachu.jpg', 'charlie@example.com'),
(120, 7.99, 'Umbreon Card', 'Pokemon Series', '2025-10-18', 'CARD', '/cards/umbreon.jpg', 'alice@example.com');
 
-- Insert an additional user
INSERT INTO userstable (email, pw) VALUES
('owner@gmail.com', 'password');
 
-- Update products to set useremail for PACK and DECK types
UPDATE products
SET useremail = 'owner@gmail.com'
WHERE producttype IN ('PACK', 'DECK');