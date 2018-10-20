package nl.han.dea.joris.services;

import nl.han.dea.joris.playlist.PlaylistDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistService extends Service{

    private MySQLDatabaseConnector connector = new MySQLDatabaseConnector();

    private int playlistID;
    private String name;
    private boolean owner;
    private List<PlaylistDTO> tracks = new ArrayList<>();

    private static final String GET_PLAYLISTS = "SELECT * FROM `playlistdata` WHERE token = ?";

    public List<PlaylistDTO> getPlaylists(String token) {

        List<PlaylistDTO> playlists = new ArrayList<>();

        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(GET_PLAYLISTS);
            pstmt.setString(1, token);


            rs = pstmt.executeQuery();

            while (rs.next()) {
                this.playlistID = rs.getInt("playlistID");
                this.name = rs.getString("name");
                this.owner = rs.getBoolean("owner");
                PlaylistDTO playlistDTO = new PlaylistDTO(playlistID, name, owner, tracks);
                playlists.add(playlistDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return playlists;
    }
}
