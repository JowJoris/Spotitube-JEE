package nl.han.dea.joris.test.services;

import nl.han.dea.joris.database.dao.PlaylistDAO;
import nl.han.dea.joris.database.objects.Playlist;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class PlaylistServiceTest {

    private PlaylistServiceTest playlistService;
    private PlaylistsResponseDTO playlistsResponseDTO;

    private PlaylistDAO playlistDAO = mock(PlaylistDAO.class);

    private static final int USERID = 1;
    private static final int OWNERID = 1;
    private static final String PLAYLISTNAME = "Hardstyle";
    private static final int PLAYLISTID = 1;
    private static final List<Playlist> PLAYLISTS = new ArrayList<>();


    @Before
    public void setup() {
        playlistService = new PlaylistServiceTest();

        playlistsResponseDTO = new PlaylistsResponseDTO();
        playlistsResponseDTO.setPlaylists(PLAYLISTS);

    }

    @Test
    public void canGetPlaylists() {

        Mockito.when(playlistDAO.getPlaylists(USERID)).thenReturn(PLAYLISTS);

        List<Playlist> playlists = playlistDAO.getPlaylists(USERID);

        Assert.assertEquals(PLAYLISTS, playlists);
    }
}
