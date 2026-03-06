create database sms;
use sms;

create table customer(
	customer_id int auto_increment primary key,
	customer_name varchar(255) not null
);

create table employee(
	employee_id int auto_increment primary key,
	employee_name varchar(255) not null,
	salary decimal(10,2) not null,
	supervisor_id int,
	foreign key (supervisor_id) references employee(employee_id)
);

create table product (
	product_id int auto_increment primary key,
	product_name varchar(255) not null,
	list_price decimal(10,2) not null

);
create table orders(
	order_id int auto_increment primary key,
	order_date datetime not null,
	customer_id int not null,
	employee_id int not null,
	foreign key (customer_id) references customer (customer_id),
	foreign key (employee_id) references employee (employee_id)
);

create table lineitem(
	order_id int not null,
	product_id int not null,
	quantity int not null,
	price decimal(10,2),
	primary key (order_id, product_id),
	foreign key (order_id) references orders (order_id),
	foreign key (product_id) references product (product_id)
);

-- ----------------------------------------------------------------------------
INSERT INTO customer (customer_name) VALUES
('John Smith'),
('Sarah Johnson'),
('Michael Brown'),
('Emily Davis');

-- 2. Employee (employee 1 has no supervisor; 2 and 3 have supervisor_id = 1)
INSERT INTO employee (employee_name, salary, supervisor_id) VALUES
('James Wilson', 15000000.00, NULL),
('Emma Taylor', 12000000.00, 1),
('David Martinez', 11000000.00, 1);

-- 3. Product
INSERT INTO product (product_name, list_price) VALUES
('Dell XPS 15 Laptop', 35900000.00),
('Logitech MX Master Mouse', 2490000.00),
('Keychron K2 Keyboard', 1890000.00),
('LG 27 inch Monitor', 5990000.00);

-- 4. Orders
INSERT INTO orders (order_date, customer_id, employee_id) VALUES
('2025-01-10 09:30:00', 1, 2),
('2025-01-11 14:00:00', 2, 2),
('2025-01-12 10:15:00', 1, 3),
('2025-01-13 16:45:00', 3, 2);

-- 5. LineItem
INSERT INTO lineitem (order_id, product_id, quantity, price) VALUES
(1, 1, 1, 35900000.00),
(1, 2, 2, 2490000.00),
(2, 3, 1, 1890000.00),
(2, 4, 1, 5990000.00),
(3, 2, 1, 2490000.00),
(3, 3, 2, 1890000.00),
(4, 1, 1, 35900000.00);
-- -------------------------------------------------------------------------
alter table orders add column total decimal(10,2) null;
-- 1

select distinct c.customer_id, c.customer_name
from customer c
inner join orders o on o.customer_id = o.customer_id;

-- 2
delimiter //
create procedure GetOrderByCustomer(in p_customer_id int)
begin
	select o.order_id, o.order_date, o.customer_id, o.employee_id, coalesce(sum(l.quantity * l.price), 0) as total
    from orders o
    left join lineitem l on o.order_id = l.order_id
    where customer_id = p_customer_id
    group by o.order_id, o.order_date, o.customer_id, o.employee_id;
end //
delimiter ;
drop procedure GetOrderByCustomer;
call GetOrderByCustomer(1);

-- 3
-- select * from lineitem
delimiter //
create procedure GetLineItemByOrder(in p_order_id int)
begin
	select *
    from lineitem
    where order_id = p_order_id;
end //
delimiter ;
call GetLineItemByOrder(1);

-- 4
delimiter //
create function OrderTotal(p_order_id int)
returns decimal(10,2)
reads sql data
begin
	declare v_total decimal(10,2) default 0;
    select coalesce(sum(quantity * price), 0) into v_total
    from lineitem
    where order_id = p_order_id;
    return v_total;
end //
delimiter ;

select OrderTotal(1);

-- 5 ----------------------------------

delimiter //
create procedure AddCustomer(in p_customer_name varchar(255))
begin
	insert into customer (customer_name) values (p_customer_name);
end //
delimiter ;
call AddCustomer('HEHE');
select * from customer;

-- 6 -----------------------
delimiter //
create procedure DeleteCustomer(in p_customer_id int)
begin
	delete from lineitem where order_id in (select order_id from orders where customer_id = p_customer_id);
    delete from orders where customer_id = p_customer_id;
    delete from customer where customer_id = p_customer_id;
end //
delimiter ;

call DeleteCustomer(2);
select * from customer;
select * from lineitem;

-- 7 Update customer in the database

delimiter //
create procedure UpdateCustomer(in p_customer_id int , in p_customer_name varchar(255))
	begin
		update customer set customer_name = p_customer_name where customer_id = p_customer_id;
    end //
delimiter ;
-- 8 Create an order into the database
select * from orders
delimiter //
create procedure CreateOrder(in p_customer_id int, in p_order_date datetime, in p_employee_id int)
begin
	insert into orders(order_date, customer_id, employee_id)
    values (p_order_date, p_customer_id, p_employee_id);
end //
delimiter ;
call CreateOrder(3, '2025-03-05 10:00:00', 2);

drop procedure CreateOrder

-- 9 Create LineItem into the database

delimiter //
create procedure AddLineItem(in p_order_id int, in p_product_id int, in p_quantity int, in p_price decimal(10,2))
begin
	insert into lineitem(order_id, product_id, quantity, price)
    values(p_order_id, p_product_id, p_quantity, p_price);
end //
delimiter ;
call AddLineItem(4, 2, 3, 2490000.00);
drop procedure AddLineItem;
select * from orders;
select * from lineitem;

-- 10 Update an order total onto the database ??

delimiter //
create procedure UpdateOrderTotal(in p_order_id int)
begin
	update orders o
    set o.total = (select coalesce(sum(l.quantity * l.price), 0)
    from lineitem l
    where l.order_id = o.order_id)
    where o.order_id = p_order_id;
end //
delimiter ;
drop procedure UpdateOrderTotal;
call UpdateOrderTotal(3);
select * from orders;