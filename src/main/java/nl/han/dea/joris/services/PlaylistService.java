package nl.han.dea.joris.services;

import nl.han.dea.joris.database.dao.PlaylistDAO;
import nl.han.dea.joris.database.objects.Playlist;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;

import java.util.List;

public class PlaylistService {

    public PlaylistsResponseDTO getPlaylists (int user_id){
        PlaylistDAO playlistDAO = new PlaylistDAO();
        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();
        List<Playlist> playlists = playlistDAO.getPlaylists(user_id);
        checkOwner(user_id, playlists);
        playlistsResponseDTO.setPlaylists(playlists);
        return playlistsResponseDTO;

    }

    private void checkOwner(int user_id, List<Playlist> playlists) {
        for(Playlist p : playlists){
            if(p.getOwnerId() == user_id){
                p.setOwner(true);
            }
        }
    }
}
