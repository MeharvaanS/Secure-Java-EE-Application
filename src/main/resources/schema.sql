DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
employeeNumber INT AUTO_INCREMENT PRIMARY KEY,
employeeName VARCHAR(20) NOT NULL,
employeeEmail VARCHAR(50) NOT NULL,
employeePhone Long NOT NULL );