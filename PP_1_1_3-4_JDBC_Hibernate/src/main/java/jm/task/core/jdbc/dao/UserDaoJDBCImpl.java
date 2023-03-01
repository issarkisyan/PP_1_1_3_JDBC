package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private Connection connection = getConnection();

    public UserDaoJDBCImpl() throws SQLException {

    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE `users` (`id` BIGINT NOT NULL AUTO_INCREMENT, " +
                "`name` VARCHAR(45) NULL,`lastName` VARCHAR(45) NULL, `age` TINYINT NULL, " +
                "PRIMARY KEY (`id`))" +
                " ENGINE = InnoDB DEFAULT CHARACTER SET = utf8";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }  catch (SQLException e) {

        }

    }

    public void dropUsersTable() throws SQLException {
        String sql = "drop table users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT id, name, lastName, age FROM users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "delete from users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
