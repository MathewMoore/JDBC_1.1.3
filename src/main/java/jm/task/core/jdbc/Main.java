package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Андрей", "Петров", (byte) 18);
        userService.saveUser("Тимофей", "Каримов", (byte) 27);
        userService.saveUser("Яна", "Рябова", (byte) 13);
        userService.saveUser("Денис", "Каримов", (byte) 5);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
