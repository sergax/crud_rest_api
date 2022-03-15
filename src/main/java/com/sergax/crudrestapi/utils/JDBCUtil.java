package com.sergax.crudrestapi.utils;

import java.sql.*;

public class JDBCUtil {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/crud_rest_api";
    private static final String USER = "user";
    private static final String PASSWORD = "Aks-662828";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(
                    (DATABASE_URL),
                    (USER),
                    (PASSWORD));
        } catch (SQLException e) {
            System.out.println("SQL Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
