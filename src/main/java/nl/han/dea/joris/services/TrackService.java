package nl.han.dea.joris.services;

import nl.han.dea.joris.database.dao.TrackDAO;
import nl.han.dea.joris.database.objects.Track;
import nl.han.dea.joris.track.PlaylistTracksResponseDTO;

import javax.inject.Inject;
import java.util.List;

public class TrackService {

    private TrackDAO trackDAO = new TrackDAO();

    public PlaylistTracksResponseDTO getTracks(int playlistID){
        PlaylistTracksResponseDTO playlistTracksResponseDTO = new PlaylistTracksResponseDTO();

        List<Track> tracks = trackDAO.getTracks(playlistID);
        playlistTracksResponseDTO.setTracks(tracks);
        return playlistTracksResponseDTO;

    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
