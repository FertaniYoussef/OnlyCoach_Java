package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionController {

    public connectionController() {


    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return null;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/onlycoach", "root", "root");

        return connection;
    }



}
