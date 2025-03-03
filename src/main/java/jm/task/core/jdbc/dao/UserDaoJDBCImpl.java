package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final static String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "lastname VARCHAR(20) NOT NULL, " +
                    "age INT NOT NULL)";

    private final static String DROP_TABLE =
            "DROP TABLE IF EXISTS users";

    private final static String ADD_USER =
            "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";

    private final static String DELETE_USER =
            "DELETE FROM users WHERE id = ?";

    private final static String GET_ALL_USERS =
            "SELECT * FROM users";

    private final static String CLEAN_TABLE =
            "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() throws SQLException {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(DROP_TABLE);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = Util.getConnection().prepareStatement(ADD_USER)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = Util.getConnection().prepareStatement(DELETE_USER)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User с ID — " + id + " удалён");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = Util.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_USERS)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            users.forEach(u -> System.out.println(u.toString()));
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(CLEAN_TABLE);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
