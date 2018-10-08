package nl.han.dea.joris.playlist;

import java.util.List;

public class PlaylistDTO {

    private int id;
    private String name;
    private boolean owner;
    private List<PlaylistDTO>tracks;

    public PlaylistDTO(int id, String name, boolean owner, List tracks){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks=tracks;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public boolean isOwner() {return owner;}
    public List<PlaylistDTO> getTracks() {return tracks;}
}
