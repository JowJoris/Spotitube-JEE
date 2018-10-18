package nl.han.dea.joris.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MySQLDatabaseConnector {
    private Properties prop = new Properties();

    private Map<String, String> getProperties(){
        InputStream input;
        Map<String, String> properties = new HashMap<>();
        try{
            input = new FileInputStream("C:\\Users\\Joris\\Documents\\GitHub\\Spotitube-JEE\\src\\main\\resources\\mysql.properties");
            prop.load(input);
            properties.put("driver", prop.getProperty("driverPropertie"));
            properties.put("connectionstring", prop.getProperty("connectionstringPropertie"));
            properties.put("username", prop.getProperty("usernamePropertie"));
            properties.put("password", prop.getProperty("passwordPropertie"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;

    }

    public Connection getConnection(){
        Map<String, String> properties = getProperties();
        Connection connection = null;
        try {
            Class.forName(properties.get("driver"));
            connection = DriverManager.getConnection(properties.get("connectionstring"), properties.get("username"), properties.get("password"));
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
