package nl.han.dea.joris.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {
    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    protected void closeConnections(){
        if (connection != null && pstmt != null && rs != null) {
            try {
                connection.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
