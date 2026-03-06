create database BikeStores;
create table brands(
brand_id int auto_increment primary key,
brand_name varchar(255) not null
);

create table categories(
category_id int auto_increment primary key,
category_name varchar(255) not null
);

create table customers(
customer_id int auto_increment primary key,
first_name varchar(50) not null,
last_name varchar(50) not null,
phone varchar(25),
email varchar(255),
street varchar(255),
city varchar(255),
state varchar(25),
zip_code varchar(5)
);

create table stores(
store_id int auto_increment primary key,
store_name varchar(255) not null,
phone varchar(25),
email varchar(255),
street varchar(255),
city varchar(255),
state varchar(10),
zip_code varchar(5)
);

create table products(
product_id int auto_increment primary key,
product_name varchar(255) not null,
brand_id int not null,
category_id int not null,
model_year smallint,
list_price decimal(10,2) not null,
foreign key (brand_id) references brands (brand_id),
foreign key (category_id) references categories (category_id)
);

create table stocks(
store_id int not null,
product_id int not null,
quantity int not null default 0,
primary key (store_id, product_id),
foreign key (store_id) references stores (store_id),
foreign key (product_id) references products (product_id)
);

create table staffs(
staff_id int auto_increment primary key,
first_name varchar(50) not null,
last_name varchar(50) not null,
email varchar(255) not null unique,
phone varchar(50) not null,
active tinyint not null,
store_id int not null,
manager_id int,
foreign key (store_id) references stores (store_id),
foreign key (manager_id) references staffs (staff_id)
);

create table orders(
order_id int auto_increment primary key,
customer_id int not null,
order_status tinyint not null,
order_date date,
required_date date,
shipped_date date,
store_id int not null,
staff_id int not null,
foreign key (store_id) references stores (store_id),
foreign key (staff_id) references staffs (staff_id),
foreign key (customer_id) references customers(customer_id)  
);


create table order_items(
order_id int not null,
item_id int not null,
product_id int not null,
quantity int not null,
list_price decimal(10,2) not null,
discount decimal(4,2) not null default 0,
primary key(order_id, item_id),
foreign key (order_id) references orders (order_id),      
foreign key (product_id) references products (product_id) 
);



insert into brands (brand_name)
values ('Electra'),
('Haro'),	('Heller'),
('Pure Cycles'),
('Ritchey'),
('Strider'),
('Sun Bicycles'),
('Surly'),
('Trek');

insert into categories (category_name)
values ('Children Bicycles'),
('Comfort Bicycles'),	('Cruisers Bicycles'),
('Cyclocross Bicycle'),
('Electric Bikes'),
('Mountain Bikes'),
('Road Bikes');

insert into products (product_name, brand_id, category_id, model_year, list_price)
values 
('Trek 820 - 2016', 9, 6, '2016', 379.99),
('Ritchey Timberwolf Frameset - 2016', 5, 6, '2016', 749.99),
('Surly Wednesday frameset - 2016', 8, 6, '2016', 999.99),
('Trek Fuel EX 8 29 - 2016', 9, 6, '2016', 2899.99),
('Heller Shagamaw Frame - 2016', 3, 6, '2016', 1320.99),
('Surly Ice Cream Truck Frameset - 2016', 8, 6, '2016', 379.99),
('Trek Slash 8 27.5 - 2016', 9, 6, '2016', 3999.99),
('Trek Remedy 29 Carbon Frameset - 2016', 9, 6, '2016', 1799.99),
('Trek Conduit+ - 2016', 9, 5, '2016', 2999.99),
('Surly Straggler - 2016', 9, 6, '2016', 379.99);

insert into customers(first_name, last_name, phone, email, street, city, state, zip_code)
values
('Debra', 'Burks',null,'debra.burks@yahoo.com', '9273 Thrne Ave.', 'Orchard Park', 'NY', 14127),
('Kasha', 'Todd',null,'kasha.todd@yahoo.com', '910 Vine Street', 'Campbell', 'CA', 95008),
('Tameka', 'Fisher',null,'tameka.fisher@aol.com', '769C Honey Creek St.', 'Redondo Beach', 'CA', 14127),
('Daryl', 'Soebce',null,'daryl.spence@aol.com', '988 Pearl Lane', 'Ybuibdake', 'NY', 11553),
('Charolette', 'Rice','(916) 381-6003','charolette.rice@msn.com', '107 River Dr.', 'Sacramento', 'CA', 95820);

insert into stores(store_name, phone, email, street,city,state,zip_code)
values ('Santa Cruz Bikes', '(831) 476-4321', 'santacruz@bikes.shop', '3700 Portola Drive', 'Santa Cruz', 'CA', '95060'),
('Baldwin Bikes', '(516) 379-8888', 'baldwin@bikes.shop', '4200 Chestnut Lane', 'Baldwin', 'NY', '11432'),
('Rowlett Bikes', '(972) 530-5555', 'rowlett@bikes.shop', '8000 Fairway Avenue', 'Rowlett', 'TX', '75088');

insert into stocks(store_id, product_id, quantity)
values 
(1, 1, 27),
(1, 2, 5),
(1, 3, 6),
(1, 4, 23),
(1, 5, 22),
(1, 6, 0),
(1, 7, 8),
(1, 8, 0),
(1, 9, 11),
(1, 10, 15);

insert into staffs(first_name, last_name, email, phone, active,store_id, manager_id)
values 
( 'Fabiola', 'Jackson', 'fabiola.jackson@bikes.shop', '(831) 555-5554', 1, 1, null),
('Mireya', 'Copeland', 'mireya.copeland@bikes.shop', '(831) 555-5555', 1, 1, 1),
('Genna', 'Serrano', 'genna.serrano@bikes.shop', '(831) 555-5556', 1, 1, 2),
('Virgie', 'Wiggins', 'virgie.wiggins@bikes.shop', '(831) 555-5557', 1, 1, 2),
('Jannette', 'David', 'jannette.david@bikes.shop', '(516) 379-4444', 1, 2, 1);

insert into orders (order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id) 
values
(1, 1, 4, '2016-01-01', '2016-01-03', '2016-01-03', 1, 2),
(2, 2, 4, '2016-01-01', '2016-01-04', '2016-01-03', 2, 5),
(3, 3, 4, '2016-01-02', '2016-01-05', '2016-01-03', 2, 5),
(4, 4, 4, '2016-01-03', '2016-01-04', '2016-01-05', 1, 3),
(5, 5, 4, '2016-01-03', '2016-01-06', '2016-01-06', 2, 4);

insert into order_items (order_id, item_id, product_id, quantity, list_price, discount) 
values
(1, 1, 10, 1, 599.99, 0.20),
(1, 2, 8, 2, 1799.99, 0.07),
(1, 3, 10, 2, 1549.00, 0.05),
(1, 4, 10, 2, 599.99, 0.05),
(1, 5, 4, 1, 2899.99, 0.20),
(2, 1, 10, 1, 599.99, 0.07),
(2, 2, 10, 2, 599.99, 0.05),
(3, 1, 3, 1, 999.99, 0.05),
(3, 2, 10, 1, 599.99, 0.05),
(4, 1, 2, 2, 749.99, 0.10);


-- Q1
select first_name, last_name, email 
from customers;

-- Q2
select * from customers
where state = 'CA';

-- Q3
select * from customers
order by first_name asc;

-- Q4
select  city, count(customer_id)  Customer_Count
from customers
where state = 'CA'
group by city;



-- Q5
select city
from customers 
where state = 'CA'
group by city
having count(customer_id) > 10;



-- Q6
select product_name, model_year
from products
where list_price between 1000 and 2000;
-- select * from products;

-- Q7
select first_name, email
from staffs
where active = 1;

-- Q8
select product_name, brand_id
from products
where model_year = 2016 and list_price > 1000;

-- Q9
select order_id, customer_id 
from orders
where shipped_date is not null;

-- Q10
select product_id, list_price, quantity
from order_items
where discount > 0  and quantity = 2;

-- Q11
select store_id, count(product_id) product_count
from stocks
group by store_id
having count(product_id) >  5
order by product_count;

-- Q12
select concat(first_name,' ', last_name) as customer_name, email
from customers
where email like '%@yahoo.com'
order by customer_name;
-- select * from customers

-- Q13
select c.category_name, avg(p.list_price) as avg_list_price
from categories c
join products p on c.category_id = p.category_id
group by c.category_id, c.category_name
having avg(p.list_price) > 500
order by avg_list_price desc;

-- Q14
select b.brand_name, COUNT(p.product_id) as total_products
from brands b
join products p on b.brand_id = p.brand_id
group by b.brand_id, b.brand_name
having COUNT(p.product_id) > 2
order by total_products desc;

-- Join
-- Q1
-- select * from products;
-- select * from brands;
select product_name, list_price, brand_name
from products p
join brands o on p.brand_id = o.brand_id
where list_price > 1000;

-- Q2 
-- select * from orders
select c.customer_id, first_name, last_name, order_id, order_status
from customers c 
join orders o on c.customer_id = o.customer_id
where order_status = 4;

-- Q3
-- select * from staffs
select a.first_name, a.last_name, a.email, b.first_name, b.last_name, b.email
from staffs a 
left join staffs b on a.manager_id = b.staff_id;

-- Q4
-- select * from products;
select product_name, b.brand_name
from products p
left join brands b on p.brand_id = b.brand_id;

-- Q5
select product_name, model_year, brand_name
from products p 
join brands b on p.brand_id = b.brand_id
where model_year >= 2016;

-- Q6
-- select * from order_items
select order_id, product_name, quantity
from order_items o 
join products p on o.product_id = p.product_id;

-- Q7
-- select * from products;
select product_name, category_name
from products p 
join categories c on p.category_id = c.category_id
where category_name = 'Moutain Bikes';

-- Q8 
-- select * from products p
-- join categories c on p.category_id = c.category_id;
-- select * from categories;
select distinct p.product_name, p.list_price, c.category_name,  brand_name
from products p 
join  categories c on p.category_id = c.category_id
join brands b on p.brand_id = b.brand_id
where c.category_name = 'Electric Bikes' and p.list_price > 500; 

-- Q9 ?
select * from orders;
select * from customers ;
select c.customer_id, first_name, last_name, order_id, shipped_date
from customers c 
left join orders o on c.customer_id = o.customer_id
where shipped_date is null;

-- Q10
-- select * from stores;
-- select * from orders;

select store_name, count(o.store_id) as order_count
from stores s
left join orders o on s.store_id = o.store_id
group by  s.store_name;

-- Q11 
select * from orders;
select * from stores;
select * from staffs;

select order_id, first_name, last_name, order_date
from orders o 
join stores st on st.store_id = o.store_id
join staffs s on o.staff_id = s.staff_id
where st.store_id = 1;

-- Q12
select concat(first_name, ' ', last_name) as customer_name, order_id, order_date
from customers c
join orders o on c.customer_id = o.customer_id
where year(o.order_date) = 2016;

-- Q13
select * from orders;
insert into orders (order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id) 
values
(6, 1, 4, '2016-12-01', '2016-12-03', '2016-12-03', 1, 2);
-- --------------------------------------------------------
select first_name, last_name, order_id, order_date
from staffs s
join orders o on o.staff_id = s.staff_id
where month(order_date) = 12; 





