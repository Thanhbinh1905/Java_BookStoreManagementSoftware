package com.quanlybansach_java.DAO;

public interface iOrderDetailDAO {
    String GET_ORDERDETAIL =
            "SELECT OrderItems.Item_id, OrderItems.Order_id, OrderItems.Book_id, OrderItems.Quantity, OrderItems.Total_amount, Books.Title, Books.Price " +
                    "FROM OrderItems " +
                    "JOIN Books ON OrderItems.Book_id = Books.Book_id";
    String DELETE_ORDERDETAIL = "DELETE FROM OrderItems WHERE Item_id = ?";
    String UPDATE_ORDERDETAIL = "UPDATE OrderItems SET Order_id=?, Book_id=?, Quantity=?, Total_amount=? " +
            "WHERE Item_id=?";
    String INSERT_ORDERDETAIL = "INSERT INTO OrderItems (item_id, order_id, book_id, quantity) " +
            "VALUES (?, ?, ?, ?)";
    String GET_BOOKIDBYNAME = "SELECT Book_id FROM Books WHERE Title = ?";
}
