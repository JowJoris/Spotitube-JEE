package nl.han.dea.joris.playlist;

import java.util.ArrayList;

public class PlaylistDTO {

    private int id;
    private String name;
    private boolean owner;
    private ArrayList<PlaylistDTO>tracks;

    public PlaylistDTO(int id, String name, boolean owner, ArrayList tracks){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks=tracks;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public boolean isOwner() {return owner;}
    public ArrayList<PlaylistDTO> getTracks() {return tracks;}
}
