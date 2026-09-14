# Write your MySQL query statement below
SELECT DISTINCT p.product_name, s.sumOfUnit AS unit
FROM Products p JOIN Orders o ON p.product_id = o.product_id 
JOIN (
    SELECT product_id, SUM(unit) AS sumOfUnit, order_date
    FROM Orders 
    WHERE order_date BETWEEN '2020-02-01' AND '2020-02-29'
    GROUP BY product_id
) s ON p.product_id = s.product_id AND s.sumOfUnit >= 100;