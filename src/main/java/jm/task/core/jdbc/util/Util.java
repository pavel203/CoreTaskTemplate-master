package jm.task.core.jdbc.util;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util extends UserServiceImpl {
    private static final String URL = "jdbc:mysql://localhost:3306/store?autoReconnect=true&useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Whydoit1";

    public static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnectionToDataBase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }
}
