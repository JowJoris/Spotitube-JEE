package nl.han.dea.joris.test.services;

import nl.han.dea.joris.database.dao.PlaylistDAO;
import nl.han.dea.joris.database.objects.Playlist;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;
import nl.han.dea.joris.services.PlaylistService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class PlaylistServiceTest {

    private PlaylistService playlistService;
    private PlaylistsResponseDTO playlistsResponseDTO;

    private PlaylistDAO playlistDAO = mock(PlaylistDAO.class);

    private static final int USERID = 1;
    private static final List<Playlist> PLAYLISTS = new ArrayList<>();


    @Before
    public void setup() {
        playlistService = new PlaylistService();
        playlistService.setPlaylistDAO(playlistDAO);

        playlistsResponseDTO = new PlaylistsResponseDTO();
        playlistsResponseDTO.setPlaylists(PLAYLISTS);

    }

    @Test
    public void canGetPlaylists() {

        Mockito.when(playlistDAO.getPlaylists(USERID)).thenReturn(PLAYLISTS);
        PlaylistsResponseDTO playlistsResponseDTO = playlistService.getPlaylists(USERID);
        Assert.assertNotNull(playlistsResponseDTO);
    }
}
