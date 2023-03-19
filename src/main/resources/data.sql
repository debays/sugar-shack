INSERT INTO maple_syrup (id, deleted, type, name, description, price, stock, image)
VALUES (1, false, 'AMBER', 'Amber Syrup', 'Delicious amber syrup', 10.99, 100, 'amber_syrup.jpg');

INSERT INTO maple_syrup (id, deleted, type, name, description, price, stock, image)
VALUES (2, false, 'DARK', 'Dark Syrup', 'Delicious dark syrup', 12.99, 50, 'dark_syrup.jpg');

INSERT INTO maple_syrup (id, deleted, type, name, description, price, stock, image)
VALUES (3, false, 'CLEAR', 'Clear Syrup', 'Delicious clear syrup', 8.99, 200, 'clear_syrup.jpg');

INSERT INTO cart (id, deleted, createdAt, totalPrice, username)
VALUES (1, false, '2022-03-19T10:30:00Z', 0.0, 'user1');

INSERT INTO cart_item (id, deleted, maple_syrup_id, quantity, price, cart_id)
VALUES (1, false, 1, 2, 21.98, 1);

INSERT INTO orders (id, orderDate, deleted, customerName, customerAddress, customerMail, customerPhone, orderStatus, totalPrice)
VALUES (1, '2022-03-19T11:00:00Z', false, 'John Doe', '123 Main St', 'johndoe@example.com', '555-1234', 'COMPLETED', 21.98);

INSERT INTO order_item (id, deleted, order_id, maple_syrup_id, quantity)
VALUES (1, false, 1, 1, 2);