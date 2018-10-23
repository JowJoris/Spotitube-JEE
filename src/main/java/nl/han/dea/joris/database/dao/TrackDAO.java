package nl.han.dea.joris.database.dao;

import nl.han.dea.joris.database.objects.Track;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TrackDAO extends DefaultDAO {

    private static final String GET_TRACKS = "SELECT `trackdata`.* FROM `trackdata` LEFT JOIN `trackinplaylist` ON `trackdata`.`trackID` = `trackinplaylist`.`trackID` WHERE playlistID = ?";


    public List<Track> getTracks(int id) {

        List<Track> tracks = new ArrayList<>();

        try {
            connection = connector.getConnection();
            pstmt = connection.prepareStatement(GET_TRACKS);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Track track = new Track();
                track.setId(rs.getInt("trackID"));
                track.setTitle(rs.getString("title"));
                track.setPerformer(rs.getString("performer"));
                track.setDuration(rs.getInt("duration"));
                track.setAlbum(rs.getString("album"));
                track.setPlaycount(rs.getInt("playcount"));
                track.setPublicationdate(rs.getString("publicationDate"));
                track.setDescription(rs.getString("description"));
                track.setOfflineAvailable(rs.getBoolean("offlineAvailable"));
                tracks.add(track);
            }
        } catch (
                SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            closeConnections();
        }
        return tracks;
    }
}
