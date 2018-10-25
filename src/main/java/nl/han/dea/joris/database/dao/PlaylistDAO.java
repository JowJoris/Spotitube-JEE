package nl.han.dea.joris.database.dao;

import nl.han.dea.joris.database.objects.Playlist;
import nl.han.dea.joris.database.objects.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PlaylistDAO extends DefaultDAO {


    private static final String GET_PLAYLISTS = "SELECT `playlistdata`.`playlist_id`, `playlistdata`.`owner_id`, `playlistdata`.`name`FROM `userplaylist` LEFT JOIN `playlistdata` ON `userplaylist`.`playlist_id` = `playlistdata`.`playlist_id` WHERE `userplaylist`.`user_id` = ?";
    private static final String ADD_PLAYLIST = "INSERT INTO `playlistdata` (`owner_id`, `name`) VALUES (?, ?)";
    private static final String GET_PLAYLISTID = "SELECT `playlistdata`.`playlist_id` FROM `playlistdata` WHERE `playlistdata`.`name` = ?";
    private static final String ADD_TO_USERPLAYLIST = "INSERT INTO `userplaylist` (`playlist_id`, `user_id`) VALUES (?, ?)";
    private static final String EDIT_PLAYLIST = "UPDATE `playlistdata` SET `name` = ? WHERE `playlistdata`.`playlist_id` = ?";
    private static final String DELETE_PLAYLIST = "DELETE FROM `playlistdata` WHERE `playlistdata`.`playlist_id` = ?";
    private static final String DELETE_FROM_USERPLAYLIST = "DELETE FROM `userplaylist` WHERE `playlist_id` = ? AND `user_id` = ?";

    public List<Playlist> getPlaylists(int id) {

        List<Playlist> playlists = new ArrayList<>();
        TrackDAO trackDAO = new TrackDAO();

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
                playlist.setTracks(trackDAO.getTracks(playlist.getId()));
                playlists.add(playlist);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
        return playlists;
    }

    public void addPlaylist(int ownerID, String name) {
        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(ADD_PLAYLIST);

            pstmt.setInt(1, ownerID);
            pstmt.setString(2, name);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
    }

    public int getPlaylistID(String name){
        int playlistID = 0;
        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(GET_PLAYLISTID);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if (rs.first()) {
                playlistID = rs.getInt("playlist_id");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
        return playlistID;
    }

    public void editUserPlaylist(UserPlaylistType type, int playlistID, int userID){
        try {
            connection = connector.getConnection();
            switch(type){
                case ADD:
                    pstmt = connection.prepareStatement(ADD_TO_USERPLAYLIST);
                    break;
                case DELETE:
                    pstmt = connection.prepareStatement(DELETE_FROM_USERPLAYLIST);
                    break;
            }

            pstmt.setInt(1, playlistID);
            pstmt.setInt(2, userID);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
    }

    public void editPlaylist(String name, int playlistID) {
        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(EDIT_PLAYLIST);

            pstmt.setString(1, name);
            pstmt.setInt(2, playlistID);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
    }

    public void deletePlaylist(int playlistID){
        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(DELETE_PLAYLIST);

            pstmt.setInt(1, playlistID);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
    }
}
