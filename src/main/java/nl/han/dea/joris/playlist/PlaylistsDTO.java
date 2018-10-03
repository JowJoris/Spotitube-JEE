package nl.han.dea.joris.playlist;

import java.util.ArrayList;

public class PlaylistsDTO {

    private ArrayList<PlaylistDTO> playlists = new ArrayList<>();
    private int length = 15;
    private int playlistCounter = 0;

    public void addPlaylist(){
        playlistCounter++;
        PlaylistDTO playlistDTO = new PlaylistDTO(playlistCounter,"Heavy Metal", true, new ArrayList());
        playlists.add(playlistDTO);

        playlistCounter++;
        PlaylistDTO playlist2DTO = new PlaylistDTO(playlistCounter,"Rock", true, new ArrayList());
        playlists.add(playlist2DTO);
    }

    public ArrayList<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}
