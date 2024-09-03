package com.quanlybansach_java.DAO;
import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements iCustomerDAO {
    public static List<Customer> getAllCustomers() {
        List<Customer> readers = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_CUSTOMER);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                readers.add(new com.quanlybansach_java.Model.Customer(id, firstName, lastName, email, phone));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return readers;
    }
    public static List<Integer> getCustomerIds() {
        List<Integer> customerIds = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_CUSTOMERID);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                customerIds.add(customerId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerIds;
    }
    public static int getCustomerIdByName(String customerFirstName, String customerLastName) {
        int customerId = -1; // Giá trị mặc định khi không tìm thấy khách hàng

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_CUSTOMERID_BYNAME);
        ) {
            stmt.setString(1, customerFirstName);
            stmt.setString(2, customerLastName);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                customerId = resultSet.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerId;
    }
    public List<com.quanlybansach_java.Model.Customer> searchCustomers(List<com.quanlybansach_java.Model.Customer> customers, String keyword) {
        List<com.quanlybansach_java.Model.Customer> matchingCustomers = new ArrayList<>();

        // Chuyển đổi keyword thành chuỗi hoa để tìm kiếm không phân biệt hoa thường
        keyword = keyword.toUpperCase();

        for (com.quanlybansach_java.Model.Customer customer : customers) {
            String id = String.valueOf(customer.getCustomerId()).toUpperCase();
            String firstName = customer.getFirstName().toUpperCase();
            String lastName = customer.getLastName().toUpperCase();
            String email = customer.getEmail().toUpperCase();
            String phone = customer.getPhone().toUpperCase();

            if (id.equals(keyword) || firstName.contains(keyword) || lastName.contains(keyword) ||
                    email.contains(keyword) || keyword.equals(phone)) {
                matchingCustomers.add(customer);
            }
        }
        return matchingCustomers;
    }
    public int getCustomerCount(List<com.quanlybansach_java.Model.Customer> customers) {
        return customers.size();
    }
    public int getCustomerCount() {
        int count = 0;

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(COUNT_CUSTOMER);
             ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                count = resultSet.getInt("customer_count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    public void insertCustomer(com.quanlybansach_java.Model.Customer customer) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_CUSTOMER)) {

            stmt.setInt(1,customer.getCustomerId());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(com.quanlybansach_java.Model.Customer customer) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_CUSTOMER)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setInt(5, customer.getCustomerId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int customerId) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_CUSTOMER)) {

            stmt.setInt(1, customerId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
