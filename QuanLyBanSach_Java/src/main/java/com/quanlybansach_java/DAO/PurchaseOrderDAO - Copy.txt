package com.quanlybansach_java.DAO;

import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.PurchaseOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PurchaseOrderDAO implements iPurchaseOrder {
    public static ObservableList<PurchaseOrder> getAllPurchaseOrders() {
        ObservableList<PurchaseOrder> purchaseOrders = FXCollections.observableArrayList();
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GETPURCHASEORDERS);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                int orderId = resultSet.getInt("purchaseOrder_id");
                int userid = resultSet.getInt("user_id");
                int publisher_id = resultSet.getInt("publisher_id");
                Date orderDate = resultSet.getDate("purchaseOrder_date");
                String publisher_name = resultSet.getString("publisher_name");
                double totalAmount = resultSet.getDouble("total_amount");
                String userUsername = resultSet.getString("Username");

                purchaseOrders.add(new PurchaseOrder(orderId, userid, publisher_id, orderDate, publisher_name, totalAmount, userUsername));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchaseOrders;
    }
    public static void addPuchaseOrder(PurchaseOrder purchaseOrder) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_PURCHASEORDER)) {

            stmt.setInt(1, purchaseOrder.getPurchaseOrder_id());
            stmt.setInt(2, purchaseOrder.getUser_id());
            stmt.setInt(3, purchaseOrder.getPublisher_id());
            stmt.setDate(4, (java.sql.Date) purchaseOrder.getPurchaseOrderDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePuchaseOrder(int purchaseorder_id) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_PURCHASEORDER)) {

            stmt.setInt(1, purchaseorder_id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updatePuchaseOrder(PurchaseOrder purchaseOrder) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(PURCHASEORDER_UPDATE)) {

            stmt.setInt(1, purchaseOrder.getPublisher_id());
            stmt.setDate(2, (java.sql.Date) purchaseOrder.getPurchaseOrderDate());
            stmt.setInt(3, purchaseOrder.getPurchaseOrder_id());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public static List<Order> showCustomerOrder(int customerId) {
//        List<Order> orders = FXCollections.observableArrayList();
//        try (Connection connection = ConnectDatabase.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(SHOWCUSTOMERORDER)) {
//            stmt.setInt(1, customerId); // Set the customer_id parameter
//
//            try (ResultSet resultSet = stmt.executeQuery()) {
//                while (resultSet.next()) {
//                    int orderId = resultSet.getInt("order_id");
//                    String couponId = resultSet.getString("coupon_id");
//                    Date orderDate = resultSet.getDate("Order_date");
//                    double totalAmount = resultSet.getDouble("total_amount"); // Use getDouble for the total_amount
//
//                    orders.add(new Order(orderId, couponId, orderDate, totalAmount));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return orders;
//    }
}
