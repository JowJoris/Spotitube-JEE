package nl.han.dea.joris.services;

import nl.han.dea.joris.database.dao.TrackDAO;
import nl.han.dea.joris.database.objects.Track;
import nl.han.dea.joris.track.PlaylistTracksResponseDTO;

import java.util.List;

public class TrackService {

    public PlaylistTracksResponseDTO getTracks(int playlistID){
        TrackDAO trackDAO = new TrackDAO();
        PlaylistTracksResponseDTO playlistTracksResponseDTO = new PlaylistTracksResponseDTO();

        List<Track> tracks = trackDAO.getTracks(playlistID);
        playlistTracksResponseDTO.setTracks(tracks);
        return playlistTracksResponseDTO;

    }
}
