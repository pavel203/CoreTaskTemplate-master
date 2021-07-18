package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.util.List;

public class Main {
    private static final UserService userService = new UserServiceImpl();


    public static void main(String[] args) {
        Util.registerDriver();

        userService.createUsersTable();

        userService.saveUser("Анатолий", "Петров", (byte) 25);
        userService.saveUser("Анна", "Петрова", (byte) 26);
        userService.saveUser("Николай", "Онегин", (byte) 35);
        userService.saveUser("Анастасия", "Петров", (byte) 39);

        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
