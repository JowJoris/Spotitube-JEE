package nl.han.dea.joris.services;

import nl.han.dea.joris.database.dao.PlaylistDAO;
import nl.han.dea.joris.database.objects.Playlist;
import nl.han.dea.joris.database.objects.Track;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;

import java.util.List;

public class PlaylistService {

    public PlaylistsResponseDTO getPlaylists (int userID){
        PlaylistDAO playlistDAO = new PlaylistDAO();
        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();

        List<Playlist> playlists = playlistDAO.getPlaylists(userID);
        checkOwner(userID, playlists);
        playlistsResponseDTO.setLength(getLength(playlists));
        playlistsResponseDTO.setPlaylists(playlists);
        return playlistsResponseDTO;

    }

    public PlaylistsResponseDTO addPlaylist (int userID, String name){
        PlaylistDAO playlistDAO = new PlaylistDAO();
        playlistDAO.addPlaylist(userID,name);
        int playlistID = playlistDAO.getPlaylistID(name);
        playlistDAO.updateUserPlaylist(playlistID, userID);
        return getPlaylists(userID);
    }

    private void checkOwner(int userID, List<Playlist> playlists) {
        for(Playlist p : playlists){
            if(p.getOwnerId() == userID){
                p.setOwner(true);
            }
        }
    }

    private int getLength(List<Playlist> playlists){
        int length = 0;
        for(Playlist p: playlists){
            for(Track t : p.getTracks()){
                length += t.getDuration();
            }
        }
        return length;
    }
}
