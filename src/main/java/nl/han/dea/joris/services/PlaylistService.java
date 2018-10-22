package nl.han.dea.joris.services;

import nl.han.dea.joris.database.dao.PlaylistDAO;
import nl.han.dea.joris.database.objects.Playlist;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;

import java.util.List;

public class PlaylistService {

    public PlaylistsResponseDTO getPlaylists (int userID){
        PlaylistDAO playlistDAO = new PlaylistDAO();
        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();
        List<Playlist> playlists = playlistDAO.getPlaylists(userID);
        checkOwner(userID, playlists);
        playlistsResponseDTO.setPlaylists(playlists);
        return playlistsResponseDTO;

    }

    private void checkOwner(int userID, List<Playlist> playlists) {
        for(Playlist p : playlists){
            if(p.getOwnerId() == userID){
                p.setOwner(true);
            }
        }
    }
}
