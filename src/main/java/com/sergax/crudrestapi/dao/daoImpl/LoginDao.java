package com.sergax.crudrestapi.dao.daoImpl;

import com.sergax.crudrestapi.model.User;
import com.sergax.crudrestapi.utils.JDBCUtil;

import java.sql.*;

public class LoginDao {
    private final static String SELECT_USERNAME_PASSWORD = "SELECT * FROM user WHERE user_name = ? AND password = ? ";

    public boolean validate(User user) throws ClassNotFoundException {
        boolean status = false;

        try (PreparedStatement preparedStatement = JDBCUtil.
                getConnection().
                prepareStatement(SELECT_USERNAME_PASSWORD)) {

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
