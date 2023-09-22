-- Script to insert dummy dev data into the database.

-- You first need to register two users into the system before running this script.

-- Replace the id here with the first user id you want to have ownership of the orders.
DO $$
    DECLARE
        userId1 integer := 8;
        userId2 integer := 9;
    BEGIN
        DELETE FROM product_order_quantities;
        DELETE FROM product_order;
        DELETE FROM inventory;
        DELETE FROM product;
        DELETE FROM address;

        INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #1', 'Product one short description.', 'This is a very long description of product #1.', 5.50);
        INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #2', 'Product two short description.', 'This is a very long description of product #2.', 10.56);
        INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #3', 'Product three short description.', 'This is a very long description of product #3.', 2.74);
        INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #4', 'Product four short description.', 'This is a very long description of product #4.', 15.69);
        INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #5', 'Product five short description.', 'This is a very long description of product #5.', 42.59);

        -- Get the product IDs
        DECLARE product1 INT;
        DECLARE product2 INT;
        DECLARE product3 INT;
        DECLARE product4 INT;
        DECLARE product5 INT;

        SELECT id INTO product1 FROM product WHERE name = 'Product #1';
        SELECT id INTO product2 FROM product WHERE name = 'Product #2';
        SELECT id INTO product3 FROM product WHERE name = 'Product #3';
        SELECT id INTO product4 FROM product WHERE name = 'Product #4';
        SELECT id INTO product5 FROM product WHERE name = 'Product #5';

        INSERT INTO inventory (product_id, quantity) VALUES (product1, 5);
        INSERT INTO inventory (product_id, quantity) VALUES (product2, 8);
        INSERT INTO inventory (product_id, quantity) VALUES (product3, 12);
        INSERT INTO inventory (product_id, quantity) VALUES (product4, 73);
        INSERT INTO inventory (product_id, quantity) VALUES (product5, 2);

        INSERT INTO address (address_line_1, city, country, user_id) VALUES ('123 Tester Hill', 'Testerton', 'England', userId1);
        INSERT INTO address (address_line_1, city, country, user_id) VALUES ('312 Spring Boot', 'Hibernate', 'England', userId2);

        -- Get the address IDs
        DECLARE address1 INT;
        DECLARE address2 INT;

        SELECT id INTO address1 FROM address WHERE user_id = userId1 ORDER BY id DESC LIMIT 1;
        SELECT id INTO address2 FROM address WHERE user_id = userId2 ORDER BY id DESC LIMIT 1;

        INSERT INTO web_order (address_id, user_id) VALUES (address1, userId1);
        INSERT INTO web_order (address_id, user_id) VALUES (address1, userId1);
        INSERT INTO web_order (address_id, user_id) VALUES (address1, userId1);
        INSERT INTO web_order (address_id, user_id) VALUES (address2, userId2);
        INSERT INTO web_order (address_id, user_id) VALUES (address2, userId2);

        -- Get the order IDs
        DECLARE order1 INT;
        DECLARE order2 INT;
        DECLARE order3 INT;
        DECLARE order4 INT;
        DECLARE order5 INT;

        SELECT id INTO order1 FROM web_order WHERE address_id = address1 AND user_id = userId1 ORDER BY id DESC LIMIT 1;
        SELECT id INTO order2 FROM web_order WHERE address_id = address1 AND user_id = userId1 ORDER BY id DESC OFFSET 1 LIMIT 1;
        SELECT id INTO order3 FROM web_order WHERE address_id = address1 AND user_id = userId1 ORDER BY id DESC OFFSET 2 LIMIT 1;
        SELECT id INTO order4 FROM web_order WHERE address_id = address2 AND user_id = userId2 ORDER BY id DESC LIMIT 1;
        SELECT id INTO order5 FROM web_order WHERE address_id = address2 AND user_id = userId2 ORDER BY id DESC OFFSET 1 LIMIT 1;

        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order1, product1, 5);
        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order1, product2, 5);
        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order2, product3, 5);
        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order2, product2, 5);
        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order2, product5, 5);
        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order3, product3, 5);
        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order4, product4, 5);
        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order4, product2, 5);
        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order5, product3, 5);
        INSERT INTO product_order_quantities (order_id, product_id, quantity) VALUES (order5, product1, 5);
        END $$;
