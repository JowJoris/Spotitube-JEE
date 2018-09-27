package nl.han.dea.joris;

import java.util.ArrayList;

public class PlaylistsDTO {

    private ArrayList<PlaylistDTO> playlist = new ArrayList<>();
    private int length = 15;

    public ArrayList<PlaylistDTO> getPlaylist() {
        return playlist;
    }

    public int getLength() {
        return length;
    }
}
