package com.quanlybansach_java.DAO;

import com.quanlybansach_java.DATABASE.ConnectDatabase;
import com.quanlybansach_java.Model.User;
import com.quanlybansach_java.utils.UserId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {

    public boolean authenticateUser(String username, String password) {
        try {
            Connection connection = ConnectDatabase.getConnection();
            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean authenticated = resultSet.next();

            resultSet.close();
            preparedStatement.close();
            return authenticated;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static UserId getUserId(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserId userId = new UserId();

        try {
            connection = ConnectDatabase.getConnection();
            String query = "SELECT user_id FROM Users WHERE Username = ? AND Password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userId.setUser_id(resultSet.getInt("user_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userId;
    }
    public static UserId getUserId(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserId userId = new UserId();

        try {
            connection = ConnectDatabase.getConnection();
            String query = "SELECT user_id FROM Users WHERE Username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userId.setUser_id(resultSet.getInt("user_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userId;
    }

    public static String getPermissionById(int user_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectDatabase.getConnection();
            String query = "SELECT permission_ FROM Users WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("permission_");
            } else {
                // Trả về một giá trị âm hoặc giá trị mặc định thích hợp để xác định rằng tài khoản không tồn tại hoặc thông tin không chính xác.
                return null; // Hoặc giá trị mặc định khác tùy thuộc vào quyết định của bạn.
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Hoặc giá trị mặc định khác tùy thuộc vào quyết định của bạn.
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<User> getUserInformationById(int user_id) {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectDatabase.getConnection();
            String query = "SELECT name_, phone, email FROM Users WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name_");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                users.add(new User(name, phone, email));
                return users;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = ConnectDatabase.getConnection();
            String query = "SELECT user_id, username, password, name_, phone, email, permission_ FROM Users WHERE user_id > 0";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name_ = resultSet.getString("name_");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String permission_ = resultSet.getString("permission_");

                users.add(new User(id, username, password, name_, phone, email, permission_));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public static List<String> getAllUserPermission() {
        List<String> permissions = new ArrayList<>();
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = ConnectDatabase.getConnection();
            String query = "SELECT permission_ FROM Users WHERE permission_!='ADMIN'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String permission_ = resultSet.getString("permission_");

                permissions.add(permission_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissions;
    }

    public static void insertUser(User user) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users (user_id, username, password, name_, phone, email, permission_) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setInt(1,user.getUserId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getName1());
            stmt.setString(5, user.getPhone1());
            stmt.setString(6, user.getEmail1());
            stmt.setString(7, user.getPermission());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE Users SET username = ?, password = ?, name_ = ?, phone = ?, email = ?, permission_ = ? WHERE user_id = ?")) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName1());
            stmt.setString(4, user.getPhone1());
            stmt.setString(5, user.getEmail1());
            stmt.setString(6, user.getPermission());
            stmt.setInt(7,user.getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int user_id) {
        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM Users WHERE user_id = ?")) {

            stmt.setInt(1, user_id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getUsername(int userid) {
        List<String> usernames = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT username FROM Users WHERE user_id != ?")) {
            stmt.setInt(1, userid);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    usernames.add(username);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usernames;
    }

    public static boolean isUserExists(String authorToCheck, int userid) {
        List<String> usernames = getUsername(userid);
        for (String username : usernames) {
            if (username.equalsIgnoreCase(authorToCheck)) {
                return true; // đã tồn tại
            }
        }
        return false; // không tồn tại
    }
    public static List<String> getUsername() {
        List<String> usernames = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT username FROM Users")) {
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    usernames.add(username);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usernames;
    }

    public static boolean isUserExists(String authorToCheck) {
        List<String> usernames = getUsername();
        for (String username : usernames) {
            if (username.equalsIgnoreCase(authorToCheck)) {
                return true; // đã tồn tại
            }
        }
        return false; // không tồn tại
    }
}
