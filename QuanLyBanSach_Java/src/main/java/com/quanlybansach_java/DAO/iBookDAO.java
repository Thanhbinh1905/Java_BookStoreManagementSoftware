package com.quanlybansach_java.DAO;

public interface iBookDAO {
    String GET_ALLBOOK =
            "SELECT b.book_id, b.title, b.author_id, b.publisher_id, b.genre,b.purchase_price, b.price, b.quantity_in_stock, a.author_name, p.publisher_name " +
            "FROM Books b " +
            "JOIN Authors a ON b.author_id = a.author_id " +
            "JOIN Publishers p ON b.publisher_id = p.publisher_id";
    String GET_BOOKSGENRE = "SELECT DISTINCT genre FROM Books";
    String DELETE_BOOK = "DELETE FROM Books WHERE book_id = ?";
    String INSERT_BOOK = "INSERT INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATEBOOK = "UPDATE Books SET title=?, author_id=?, publisher_id=?, genre=?, purchase_price=?, price=?, quantity_in_stock=? " +
            "WHERE book_id=?";
    String COUNTBOOK = "SELECT COUNT(*) FROM Books WHERE book_id = ?";
}
