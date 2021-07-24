package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Statement statement;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = Util.getConnectionToDataBase().createStatement();
            String sqlCommand = "CREATE TABLE Users " + "(Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastname VARCHAR(20), age INT)";
            statement.executeUpdate(sqlCommand);
        } catch (SQLException ignore) {}
    }

    public void dropUsersTable() {
        try {
            statement = Util.getConnectionToDataBase().createStatement();
            statement.execute("DROP TABLE Users");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sqlInsert = "INSERT Users(name , lastname, age) VALUES (?, ?, ?)";
            PreparedStatement statement = Util.getConnectionToDataBase().prepareStatement(sqlInsert);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных.");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            String sqlDelete = "DELETE FROM Users WHERE ID = ?";
            PreparedStatement statement = Util.getConnectionToDataBase().prepareStatement(sqlDelete);
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User с ID – " + id + " удален из базы данных.");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try {
            statement = Util.getConnectionToDataBase().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                result.add(new User(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getByte(4)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        try {
            statement.execute("TRUNCATE TABLE Users");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
