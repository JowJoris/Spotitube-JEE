package nl.han.dea.joris.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MySQLDatabaseConnector {

    private static MySQLDatabaseConnector mySQLDatabaseConnector= new MySQLDatabaseConnector();

    public static void main(String[] args) {
        mySQLDatabaseConnector.fileReader();
    }

    public void fileReader(){
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("mysql.properties"));
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            getConnection(user, password);

        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        } catch (IOException ex) {
            System.out.println("io exception");
        }
    }

    public void getConnection(String user, String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection("jdbc:mysql://localhost:3306/Spotitube",user, password);
            System.out.println("CONNECTION CHECK");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
