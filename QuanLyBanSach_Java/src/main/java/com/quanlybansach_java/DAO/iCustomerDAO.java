package com.quanlybansach_java.DAO;

public interface iCustomerDAO {
    String GET_CUSTOMER = "SELECT * FROM Customers";
    String GET_CUSTOMERID = "SELECT customer_id FROM Customers";
    String GET_CUSTOMERID_BYNAME = "SELECT customer_id FROM Customers WHERE first_name = ? AND last_name = ?";
    String COUNT_CUSTOMER = "SELECT COUNT(*) AS customer_count FROM Customers";
    String INSERT_CUSTOMER = "INSERT INTO Customers (customer_id,first_name, last_name, email, phone) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_CUSTOMER = "UPDATE Customers SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE customer_id = ?";
    String DELETE_CUSTOMER = "DELETE FROM Customers WHERE customer_id = ?";
}
