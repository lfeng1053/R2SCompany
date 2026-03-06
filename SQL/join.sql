use Sales;
update orders set customer_id = 2 where order_id = 1;

-- Q1 
select o.order_id, c.customer_id, c.customer_name
from customers c
inner join orders o on c.customer_id = o.customer_id;
-- select* from orders;

-- Q2
select c.customer_id, customer_name, order_id
from customers c 
left join orders o on o.customer_id = c.customer_id;

-- Q3
select a.employee_id, concat(a.last_name,'', a.first_name) as 'Employee Name', b.employee_id as 'Supervisor ID',
concat(b.last_name,'', b.first_name) as 'Supervisor Name'
from employee a 
inner join employee b on a.supervisor_id = b.employee_id;

-- Q4
select a.customer_id, a.customer_name, a.country
from customers a 
inner join customers b on a.customer_id <> b.customer_id
where a.country = b.country;

-- Q5
select order_id, customer_name, concat(first_name,'', last_name) employeeName, order_date
from employee e 
inner join orders o on e.employee_id = o.employee_id
inner join customers c on c.customer_id = o.customer_id;

-- -------------------------------------------------
use bikestores;
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