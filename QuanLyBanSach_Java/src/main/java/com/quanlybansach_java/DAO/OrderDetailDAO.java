package com.quanlybansach_java.DAO;

import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.OrderItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO implements iOrderDetailDAO {
    public static List<OrderItems> getAllOrderDetails() {
        List<OrderItems> orderItems = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_ORDERDETAIL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int item_id = resultSet.getInt("Item_id");
                int order_id = resultSet.getInt("Order_id");
                int book_id = resultSet.getInt("Book_id");
                int quantity = resultSet.getInt("Quantity");
                double total_amount = resultSet.getDouble("Total_amount");
                String title = resultSet.getString("Title");
                double price = resultSet.getDouble("Price");

                orderItems.add(new OrderItems(item_id, order_id, book_id, quantity, total_amount, title, price));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }
    public static List<Integer> getAllOrderItemIdByOrderId(int order_id) {
        List<Integer> orderItems = new ArrayList<>();
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = ConnectDatabase.getConnection();
            stmt = connection.prepareStatement("SELECT item_id FROM OrderItems WHERE order_id = ?");
            stmt.setInt(1, order_id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int item_id = rs.getInt("item_id");
                orderItems.add(item_id);
            }

            // Đóng tài nguyên sau khi hoàn thành
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    public static List<OrderItems> searchOrderDetails(List<OrderItems> orderItems, int selectedOrder) {
        List<OrderItems> matchingOrderItems = new ArrayList<>();

        for (OrderItems orderItem : orderItems) {
            int orderId = orderItem.getOrderId();

            if (selectedOrder == orderId) {
                matchingOrderItems.add(orderItem);
            }
        }
        return matchingOrderItems;
    }
    public void deleteOrderDetail(int Item_id) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_ORDERDETAIL)) {

            stmt.setInt(1, Item_id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateOrderDetail(OrderItems orderItems) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_ORDERDETAIL)) {

            stmt.setInt(1, orderItems.getOrderId());
            stmt.setInt(2, orderItems.getBookIdSold());
            stmt.setInt(3, orderItems.getQuantity());
            stmt.setDouble(4, orderItems.getSubtotal());
            stmt.setInt(5, orderItems.getOrderDetailId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getBookIdByName(String bookName) {
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        int BookId = -1; // Giá trị mặc định khi không tìm thấy tác giả

        try {
            connection = ConnectDatabase.getConnection();
            stmt = connection.prepareStatement(GET_BOOKIDBYNAME);
            stmt.setString(1, bookName);

            rs = stmt.executeQuery();

            if (rs.next()) {
                BookId = rs.getInt("Book_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BookId;
    }
    public static void addOrderDetail(OrderItems orderItems) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_ORDERDETAIL)) {

            stmt.setInt(1, orderItems.getOrderDetailId());
            stmt.setInt(2, orderItems.getOrderId());
            stmt.setInt(3, orderItems.getBookIdSold());
            stmt.setInt(4, orderItems.getQuantity());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
