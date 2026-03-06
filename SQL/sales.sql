create database Sales;

create table employee(
employee_id int auto_increment primary key,
last_name varchar(20),
first_name varchar(20),
birth_date date,
supervisor_id int not null
);

create table customers(
customer_id int auto_increment primary key,
customer_name varchar(255),
contact_name varchar(255),
address varchar(255),
city varchar(255),
postal_code varchar(10),
country varchar(20)
);


create table orders(
order_id int auto_increment primary key,
customer_id int not null,
employee_id int not null,
order_date datetime,
foreign key (customer_id) references customers (customer_id),
foreign key (employee_id) references employee (employee_id)
);

insert into customers(customer_name, contact_name, address, city, postal_code, country)
values	
('Trần Bình', 'Trọng', 'Quận 8', 'HCM', 70000, 'VN'),
('Tran Bao', 'An', 'Bình Thạnh', 'HCM', 70000, 'VN'),
('Tasty', 'Finn', 'Streetroad 19B', 'Liverpool', 'L1 0AA', 'UK');

-- Question 1
select * from customers;

-- Question 2
select distinct country from customers;

-- Q3
select contry from customers
where country = 'VN';

-- Q4
select country, count(customer_id) Number_Of_Customers
from customers
group by country;

-- Q5
select country, count(customer_id) Number_Of_Customers
from customers
group by country
having count(customer_id) >= 2;

-- Q6
select customer_id, customer_name, country
from customers
order by customer_name;



