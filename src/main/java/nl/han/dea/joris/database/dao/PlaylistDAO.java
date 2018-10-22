package nl.han.dea.joris.database.dao;

import nl.han.dea.joris.database.objects.Playlist;
import nl.han.dea.joris.playlist.PlaylistDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PlaylistDAO extends DefaultDAO{


    private static final String GET_PLAYLISTS = "SELECT `playlistdata`.`playlist_id`, `playlistdata`.`owner_id`, `playlistdata`.`name`FROM `userplaylist` LEFT JOIN `playlistdata` ON `userplaylist`.`playlist_id` = `playlistdata`.`playlist_id` WHERE `userplaylist`.`user_id` = ?";
    private static final String EDIT_PLAYLIST ="";
    private static final String ADD_PLAYLIST ="";
    private static final String DELETE_PLAYLIST="";



    public List<Playlist> getPlaylists(int id) {

        List<Playlist> playlists = new ArrayList<>();

        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(GET_PLAYLISTS);
            pstmt.setInt(1, id);


            rs = pstmt.executeQuery();

            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("playlist_id"));
                playlist.setName(rs.getString("name"));
                playlist.setOwnerId(rs.getInt("owner_id"));
                playlists.add(playlist);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
        return playlists;
    }
}
