package com.quanlybansach_java.DAO;

import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements iBookDAO {
    public static ObservableList<Book> getAllBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_ALLBOOK);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                int author_id = resultSet.getInt("author_id");
                int publisher_id = resultSet.getInt("publisher_id");
                String genre = resultSet.getString("genre");
                double purchase_price = resultSet.getDouble("purchase_price");
                double price = resultSet.getDouble("price");
                int quantity_in_stock = resultSet.getInt("quantity_in_stock");
                String authorName = resultSet.getString("author_name");
                String publisherName = resultSet.getString("publisher_name");

                books.add(new Book(id, title, author_id, publisher_id, genre,purchase_price, price, quantity_in_stock, authorName, publisherName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public static List<String> getBookName() {
        List<String> books = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT Title FROM Books");
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                String bookName = resultSet.getString("Title");
                books.add(bookName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
    public static List<String> getAllGenres() {
        List<String> genres = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_BOOKSGENRE);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                String genre = resultSet.getString("genre");
                genres.add(genre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }
    public static int getBookIdByName(String bookName) {
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        int bookId = -1; // Giá trị mặc định khi không tìm thấy tác giả

        try {
            connection = ConnectDatabase.getConnection();
            stmt = connection.prepareStatement("SELECT book_id FROM Books WHERE Title = ?");
            stmt.setString(1, bookName);

            rs = stmt.executeQuery();

            if (rs.next()) {
                bookId = rs.getInt("book_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookId;
    }
    public List<Book> searchBooks(int id, String author, String title, String publisher, String genre) {
        List<Book> books = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection()) {
            StringBuilder query = new StringBuilder("SELECT b.*, a.author_name, p.publisher_name FROM Books b");
            query.append(" LEFT JOIN Authors a ON b.author_id = a.author_id");
            query.append(" LEFT JOIN Publishers p ON b.publisher_id = p.publisher_id");
            query.append(" WHERE 1=1");

            if (id != -1) {
                query.append(" AND b.book_id = ").append(id);
            }
            if (!author.isEmpty()) {
                query.append(" AND a.author_name LIKE '%").append(author).append("%'");
            }
            if (!title.isEmpty()) {
                query.append(" AND b.title LIKE '%").append(title).append("%'");
            }
            if (!publisher.isEmpty()) {
                query.append(" AND p.publisher_name LIKE '%").append(publisher).append("%'");
            }
            if (genre != null && !genre.isEmpty()) {
                query.append(" AND b.genre LIKE '%").append(genre).append("%'");
            }

            try (PreparedStatement stmt = connection.prepareStatement(query.toString());
                 ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("book_id");
                    String bookTitle = resultSet.getString("title");
                    int authorId = resultSet.getInt("author_id");
                    int publisherId = resultSet.getInt("publisher_id");
                    String bookGenre = resultSet.getString("genre");
                    double purchase_price = resultSet.getDouble("purchase_price");
                    double price = resultSet.getDouble("price");
                    int quantityInStock = resultSet.getInt("quantity_in_stock");
                    String authorName = resultSet.getString("author_name");
                    String publisherName = resultSet.getString("publisher_name");

                    books.add(new Book(bookId, bookTitle, authorId, publisherId, bookGenre, purchase_price, price, quantityInStock, authorName, publisherName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
    public void deleteBook(int book_id) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_BOOK)) {

            stmt.setInt(1, book_id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addBook(Book book) {

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_BOOK)) {

            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3,book.getAuthorId());
            stmt.setInt(4, book.getPublisherId());
            stmt.setString(5, book.getGenre());
            stmt.setDouble(6, book.getPurchasePrice());
            stmt.setDouble(7, book.getPrice());
            stmt.setInt(8, book.getQuantityInStock());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateBook(Book book) {

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATEBOOK)) {

            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthorId());
            stmt.setInt(3, book.getPublisherId());
            stmt.setString(4, book.getGenre());
            stmt.setDouble(5, book.getPurchasePrice());
            stmt.setDouble(6, book.getPrice());
            stmt.setInt(7, book.getQuantityInStock());
            stmt.setInt(8, book.getBookId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean isBookIdExists(int bookId) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        boolean exists = false;

        try {
            connection = ConnectDatabase.getConnection();
            preparedStatement = connection.prepareStatement(COUNTBOOK);
            preparedStatement.setInt(1, bookId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
    public static boolean isBookTitleExists(String bookTitle) {
        // Iterate through the list of books and check if any book has the same title
        List<Book> books = getAllBooks();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                return true; // Book title already exists
            }
        }
        return false; // Book title does not exist
    }
}
