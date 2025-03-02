package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoJDBCImpl(new Util());

        userDao.createUsersTable();

        userDao.saveUser("Андрей","Петров", (byte) 18);
        userDao.saveUser("Тимофей","Каримов", (byte) 27);
        userDao.saveUser("Яна","Рябова", (byte) 13);
        userDao.saveUser("Денис","Каримов", (byte) 5);

        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
