package nl.han.dea.joris.services;

import java.sql.*;

public class UserService {

    private MySQLDatabaseConnector connector = new MySQLDatabaseConnector();

    private String user;
    private String token;

    public String getUser() {return user;}
    public String getToken() {return token;}

    public boolean authenticate(String username, String password){
        Connection connection;
        connection = connector.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM `userdata` WHERE user = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.first()){
                return false;
            }
            if (rs.first()) {
                this.user = rs.getString("user");
                this.token = rs.getString("token");
            }
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
