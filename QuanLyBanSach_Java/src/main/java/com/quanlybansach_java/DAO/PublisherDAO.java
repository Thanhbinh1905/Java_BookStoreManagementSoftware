package com.quanlybansach_java.DAO;

import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.Publisher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDAO {
    public static List<String> getPublisherName() {
        List<String> publishers = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT DISTINCT publisher_name FROM Publishers");
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                String publisher = resultSet.getString("publisher_name");
                publishers.add(publisher);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publishers;
    }
    public static int getPublisherIdByName(String authorName) {
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        int authorId = -1; // Giá trị mặc định khi không tìm thấy tác giả

        try {
            connection = ConnectDatabase.getConnection();
            stmt = connection.prepareStatement("SELECT publisher_id FROM publishers WHERE publisher_name = ?");
            stmt.setString(1, authorName);

            rs = stmt.executeQuery();

            if (rs.next()) {
                authorId = rs.getInt("publisher_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorId;
    }
    private static int getPublisherCount() {
        int count = 0;

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS publisher_count FROM Publishers");
             ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                count = resultSet.getInt("publisher_count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    public static boolean isPublisherExists(String publisherToCheck) {
        List<String> publishers = getPublisherName();
        for (String publisher : publishers) {
            if (publisher.equalsIgnoreCase(publisherToCheck)) {
                return true; // Nhà xuất bản đã tồn tại
            }
        }
        return false; // Nhà xuất bản không tồn tại
    }
    public static void addPublisher(String newPublisher) {
        int publisherCount = getPublisherCount();
        int newPublisherId = publisherCount + 1;

        String sql = "INSERT INTO Publishers (publisher_id, publisher_name) " +
                "VALUES (?, ?)";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, newPublisherId);
            stmt.setString(2, newPublisher);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
