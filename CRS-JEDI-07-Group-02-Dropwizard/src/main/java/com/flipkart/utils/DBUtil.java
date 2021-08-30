package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            } else {

                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/test?characterEncoding=latin1&useConfigs=maxPerformance";
                String user = "root";
                String password = "root";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}