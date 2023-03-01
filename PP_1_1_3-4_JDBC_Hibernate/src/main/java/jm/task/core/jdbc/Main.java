package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        try {
            util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UserDaoJDBCImpl test1 = new UserDaoJDBCImpl();
        test1.createUsersTable();
        test1.saveUser("Igor","Mai", (byte) 22);
        System.out.println("User с именем - Igor добавлен в Базу Данных");
        test1.saveUser("Mitya","Ruzanov", (byte) 24);
        System.out.println("User с именем - Mitya добавлен в Базу Данных");
        test1.saveUser("Kolya","Starchikov", (byte) 24);
        System.out.println("User с именем - Kolya добавлен в Базу Данных");
        test1.saveUser("Misha","Berezin", (byte) 26);
        System.out.println("User с именем - Misha добавлен в Базу Данных");
        System.out.println(test1.getAllUsers());
        test1.dropUsersTable();

    }

}
