DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id BIGINT PRIMARY KEY auto_increment, title VARCHAR (255), description VARCHAR (255),cost BIGINT);
INSERT INTO products (title, description, cost) VALUES
('Product1','Solid',100),
('Product2','Liquid',400),
('Product3','Solid',700),
('Product4','Loose',800),
('Product5','Liquid',300);

DROP TABLE IF EXISTS users CASCADE ;
CREATE TABLE users (id BIGINT PRIMARY KEY auto_increment, name VARCHAR (255));
INSERT INTO users (name) VALUES
('Alex'),
('Bob'),
('Ustas');


DROP TABLE IF EXISTS users_products CASCADE;
CREATE TABLE users_products (product_id BIGINT, user_id BIGINT, FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (user_id) REFERENCES users(id));
INSERT INTO users_products (product_id, user_id) VALUES
(1,1),
(1,2),
(2,3),
(3,1),
(4,3),
(5,2),
(4,1);

###

use data_lesson_28_2;
INSERT INTO `rols` (`name`)
    VALUES('ROLE_GUEST'),('ROLE_ADMIN'),('ROLE_SUPERADMIN');

select * from rols;

select * from users_roles;