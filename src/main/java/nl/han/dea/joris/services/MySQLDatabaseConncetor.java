package nl.han.dea.joris.services;

import java.sql.*;

public class MySQLDatabaseConncetor {

    public void MySQLDatabaseConncetor() {
        String url = "jdbc:mysql://localhost:3306/Spotitube";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[MYSQL] Connection established");
    }

}
