
USE mydatabase;
SHOW tables;

CREATE
TABLE employee (id INT PRIMARY KEY,name VARCHAR(255),payment_id INT,FOREIGN KEY (payment_id) REFERENCES payment(id));

desc customer;
desc orders;
desc customer_orders;

DROP TABLE customer;
DROP TABLE orders;
DROP TABLE customer_orders;




INSERT INTO `mydatabase`.`employee` (`id`, `name`) VALUES ('1', 'Akash chaudhary');

select * from app_user;
select * from orders;
SELECT * FROM `mydatabase`.`employee` WHERE id = 1;

UPDATE `mydatabase`.`employee` SET name = 'Akash' WHERE id = 1;

DELETE FROM `mydatabase`.`employee` WHERE id = 1;




