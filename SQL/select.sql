use Sales;
-- Question 1
select * from customers;

-- Question 2
select distinct country from customers;

-- Q3
select country from customers
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

-- ----------------------------------------------------------------------------------

use bikestores;

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