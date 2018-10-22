package nl.han.dea.joris.playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsResponseDTO {

    private List<PlaylistDTO> playlists = new ArrayList<>();
    private int length = 15;

    public void setPlaylists(List<PlaylistDTO> playlists) {this.playlists = playlists;}
    public List<PlaylistDTO> getPlaylists() {return playlists;}
    public int getLength() {return length;}
    //TODO Get length through database
    //public void setLength(int length) {this.length = length;}

}
