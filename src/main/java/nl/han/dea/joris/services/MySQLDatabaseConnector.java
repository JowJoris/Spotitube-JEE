package nl.han.dea.joris.services;

import java.sql.*;

public class MySQLDatabaseConnector {

    public static void main(String[] args) {
        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3306/Spotitube?user=root&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[MYSQL] Connection established");
    }
}
