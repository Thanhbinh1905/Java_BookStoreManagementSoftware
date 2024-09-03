package com.quanlybansach_java.DAO;

import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderDAO implements iOrderDAO {
    public static ObservableList<Order> getAllOrders() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GETORDERS);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int userid = resultSet.getInt("user_id");
                int customerId = resultSet.getInt("customer_id");
                String couponId = resultSet.getString("coupon_id");
                Date orderDate = resultSet.getDate("order_date");
                String customerFirstName = resultSet.getString("first_name");
                String customerLastName = resultSet.getString("last_name");
                double totalAmount = resultSet.getDouble("total_amount");
                String userUsername = resultSet.getString("Username");

                orders.add(new Order(orderId, userid, customerId, couponId, orderDate, customerFirstName, customerLastName, totalAmount, userUsername));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public static void addOrder(Order order) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_ORDER)) {

            stmt.setInt(1, order.getOrderId());
            stmt.setInt(2, order.getUserid());
            stmt.setInt(3, order.getCustomerId());
            stmt.setString(4,order.getCouponId());
            stmt.setDate(5, (java.sql.Date) order.getOrderDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int order_id) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_ORDER)) {

            stmt.setInt(1, order_id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateOrder(Order order) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(ORDER_UPDATE)) {

            stmt.setInt(1, order.getCustomerId());
            stmt.setString(2, order.getCouponId());
            stmt.setDate(3, (java.sql.Date) order.getOrderDate());
            stmt.setInt(4, order.getOrderId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Order> showCustomerOrder(int customerId) {
        List<Order> orders = FXCollections.observableArrayList();
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SHOWCUSTOMERORDER)) {
            stmt.setInt(1, customerId); // Set the customer_id parameter

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("order_id");
                    String couponId = resultSet.getString("coupon_id");
                    Date orderDate = resultSet.getDate("Order_date");
                    double totalAmount = resultSet.getDouble("total_amount"); // Use getDouble for the total_amount

                    orders.add(new Order(orderId, couponId, orderDate, totalAmount));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}


