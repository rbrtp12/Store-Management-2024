package com.example.storemanagement.DAO;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
        @Value("spring.datasource.url")
        private static String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
        @Value("spring.datasource.username")
        private static String USER = "robert";
        @Value("spring.datasource.password")
        private static String PASSWORD = "test";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}


