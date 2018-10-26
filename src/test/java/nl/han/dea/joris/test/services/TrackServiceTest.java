package nl.han.dea.joris.test.services;

import nl.han.dea.joris.database.dao.TrackDAO;
import nl.han.dea.joris.database.objects.Track;
import nl.han.dea.joris.services.TrackService;
import nl.han.dea.joris.track.PlaylistTracksResponseDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TrackServiceTest {

    private TrackService trackService;
    private PlaylistTracksResponseDTO playlistTracksResponseDTO;

    private TrackDAO trackDAO = mock(TrackDAO.class);

    private static final int PLAYLISTID = 1;
    private static final List<Track> TRACKS = new ArrayList<>();

    @Before
    public void setup(){
        trackService = new TrackService();
        trackService.setTrackDAO(trackDAO);


        playlistTracksResponseDTO = new PlaylistTracksResponseDTO();
        playlistTracksResponseDTO.setTracks(TRACKS);
    }

    @Test
    public void canGetTracks(){

        Mockito.when(trackDAO.getTracks(PLAYLISTID)).thenReturn(TRACKS);
        PlaylistTracksResponseDTO playlistTracksResponseDTO = trackService.getTracks(PLAYLISTID);
        Assert.assertNotNull(playlistTracksResponseDTO);
    }
}
