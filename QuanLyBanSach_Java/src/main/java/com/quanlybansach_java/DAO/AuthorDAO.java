package com.quanlybansach_java.DAO;

import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.Author;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO implements iAuthorDAO {
    public static List<String> getAuthorName() {
        List<String> authorsName = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_AUTHOR);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                String authorName = resultSet.getString("author_name");
                authorsName.add(authorName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorsName;
    }

    // Phương thức lấy author_id dựa trên tên tác giả
    public static int getAuthorIdByName(String authorName) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int authorId = -1; // Giá trị mặc định khi không tìm thấy tác giả

        try {
            connection = ConnectDatabase.getConnection();
            stmt = connection.prepareStatement(GETAUTHORIDBYNAME);
            stmt.setString(1, authorName);

            rs = stmt.executeQuery();

            if (rs.next()) {
                authorId = rs.getInt("author_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorId;
    }
    private static int getAuthorCount() {
        int count = 0;

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(COUNT_AUTHOR);
             ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                count = resultSet.getInt("author_count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    public static boolean isAuthorExists(String authorToCheck) {
        List<String> authors = getAuthorName();
        for (String author : authors) {
            if (author.equalsIgnoreCase(authorToCheck)) {
                return true; // Tác giả đã tồn tại
            }
        }
        return false; // Tác giả không tồn tại
    }
    public static void addAuthor(String newAuthor) {
        int authorCount = getAuthorCount();
        int newAuthorid = authorCount + 1;

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_AUTHOR)) {

            stmt.setInt(1, newAuthorid);
            stmt.setString(2, newAuthor);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
