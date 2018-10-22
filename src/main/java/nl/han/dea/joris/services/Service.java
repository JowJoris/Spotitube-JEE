package nl.han.dea.joris.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service {

    protected static final Logger LOGGER = Logger.getLogger(Service.class.getName() );

    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    protected void closeConnections(){
            try {
                connection.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
    }
}
