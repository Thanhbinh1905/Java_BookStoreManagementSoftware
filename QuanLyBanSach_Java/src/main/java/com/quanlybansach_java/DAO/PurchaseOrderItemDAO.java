package com.quanlybansach_java.DAO;

import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.OrderItems;
import com.quanlybansach_java.Model.PurchaseOrderItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderItemDAO {
    public static List<PurchaseOrderItems> getAllPurchaseOrderItems() {
        List<PurchaseOrderItems> purchaseOrderItems = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "SELECT PurchaseOrderItems.Item_id, PurchaseOrderItems.purchaseOrder_id, PurchaseOrderItems.Book_id, PurchaseOrderItems.Quantity, PurchaseOrderItems.Total_amount, Books.Title, Books.Price " +
                             "FROM PurchaseOrderItems " +
                             "JOIN Books ON PurchaseOrderItems.Book_id = Books.Book_id"
             );
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int item_id = resultSet.getInt("Item_id");
                int order_id = resultSet.getInt("purchaseOrder_id");
                int book_id = resultSet.getInt("Book_id");
                int quantity = resultSet.getInt("Quantity");
                double total_amount = resultSet.getDouble("Total_amount");
                String title = resultSet.getString("Title");
                double price = resultSet.getDouble("Price");

                purchaseOrderItems.add(new PurchaseOrderItems(item_id, order_id, book_id, quantity, total_amount, title, price));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchaseOrderItems;
    }
    public static List<Integer> getAllPurchaseOrderItemIdByOrderId(int purchaseorder_id) {
        List<Integer> orderItems = new ArrayList<>();
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            connection = ConnectDatabase.getConnection();
            stmt = connection.prepareStatement("SELECT item_id FROM PurchaseOrderItems WHERE purchaseorder_id = ?");
            stmt.setInt(1, purchaseorder_id);
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
    public static List<PurchaseOrderItems> searchPurchaseOrders(List<PurchaseOrderItems> purchaseOrderItems, int selectedOrder) {
        List<PurchaseOrderItems> matchingOrderItems = new ArrayList<>();

        for (PurchaseOrderItems purchaseOrderItem : purchaseOrderItems) {
            int orderId = purchaseOrderItem.getPurchaseOrder_id();

            if (selectedOrder == orderId) {
                matchingOrderItems.add(purchaseOrderItem);
            }
        }
        return matchingOrderItems;
    }
    public void deletePurchaseOrderItem(int Item_id) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM PurchaseOrderItems WHERE Item_id = ?")) {

            stmt.setInt(1, Item_id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updatePurchaseOrderItem(PurchaseOrderItems purchaseOrderItems) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "UPDATE PurchaseOrderItems SET Book_id=?, Quantity=?, Total_amount=? " +
                             "WHERE Item_id=? AND purchaseOrder_id=?"
             )) {

            stmt.setInt(1, purchaseOrderItems.getBookId());
            stmt.setInt(2, purchaseOrderItems.getQuantity());
            stmt.setDouble(3, purchaseOrderItems.getTotal_amount());
            stmt.setInt(4, purchaseOrderItems.getItem_id());
            stmt.setInt(5, purchaseOrderItems.getPurchaseOrder_id());

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
            stmt = connection.prepareStatement("SELECT Book_id FROM Books WHERE Title = ?");
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
    public static void addOrderItems(PurchaseOrderItems purchaseOrderItems) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO PurchaseOrderItems (item_id, purchaseorder_id, book_id, quantity) " +
                             "VALUES (?, ?, ?, ?)"
             )) {

            stmt.setInt(1, purchaseOrderItems.getItem_id());
            stmt.setInt(2, purchaseOrderItems.getPurchaseOrder_id());
            stmt.setInt(3, purchaseOrderItems.getBookId());
            stmt.setInt(4, purchaseOrderItems.getQuantity());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
