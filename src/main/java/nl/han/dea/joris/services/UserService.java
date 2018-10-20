package nl.han.dea.joris.services;

import java.sql.*;

public class UserService extends Service{

    private MySQLDatabaseConnector connector = new MySQLDatabaseConnector();

    private static final String GET_USER = "SELECT * FROM `userdata` WHERE user = ? AND password = ?";

    private String user;
    private String token;

    public String getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public boolean authenticate(String username, String password) {
        try {
            connection = connector.getConnection();

            pstmt = connection.prepareStatement(GET_USER);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (!rs.first()) {
                return false;
            }
            if (rs.first()) {
                this.user = rs.getString("user");
                this.token = rs.getString("token");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return true;
    }
}
