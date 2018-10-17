package nl.han.dea.joris.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {

    private String user;
    private String token;
    private Connection connection;

    public String getUser() {return user;}
    public String getToken() {return token;}

    public boolean authenticate(String username, String password){
        connection = MySQLDatabaseConnector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM Userdata WHERE user =" + username + "AND password =" + password;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                user = rs.getString("user");
                token = rs.getString("token");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
