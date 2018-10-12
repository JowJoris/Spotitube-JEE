package nl.han.dea.joris.services;

import java.sql.*;

public class MySQLDatabaseConnector {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Spotitube";
        try {
            DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[MYSQL] Connection established");
    }
}
