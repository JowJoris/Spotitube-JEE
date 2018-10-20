package nl.han.dea.joris.playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsResponseDTO {

    private List<PlaylistDTO> playlists = new ArrayList<>();
    private int length = 15;

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }
    public int getLength() {
        return length;
    }
    public void setPlaylists(List<PlaylistDTO> playlists) {this.playlists = playlists;}
    public void setLength(int length) {this.length = length;}

}
