package com.quanlybansach_java.DAO;

public interface iAuthorDAO {
    String GET_AUTHOR = "SELECT DISTINCT author_name FROM Authors";
    String GETAUTHORIDBYNAME = "SELECT author_id FROM authors WHERE author_name = ?";
    String COUNT_AUTHOR = "SELECT COUNT(*) AS author_count FROM Authors";
    String INSERT_AUTHOR = "INSERT INTO Authors (author_id, author_name) " +
            "VALUES (?, ?)";
}
