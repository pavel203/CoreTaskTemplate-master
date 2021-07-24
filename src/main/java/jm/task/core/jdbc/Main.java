package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Павел", "Тарасов", (byte) 29);
        userService.saveUser("Игорь", "Олегов", (byte) 29);
        userService.saveUser("Антон", "Хан", (byte) 29);
        userService.saveUser("Мария", "Дмитренко", (byte) 29);

        userService.getAllUsers().forEach(System.out :: println);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
