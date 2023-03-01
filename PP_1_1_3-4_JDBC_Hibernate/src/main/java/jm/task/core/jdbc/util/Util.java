package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mysqltest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(!connection.isClosed());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
        return connection;
    }
}
