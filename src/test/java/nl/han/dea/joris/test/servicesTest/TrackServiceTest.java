package nl.han.dea.joris.test.servicesTest;

import nl.han.dea.joris.database.dao.TrackDAO;
import nl.han.dea.joris.database.objects.Track;
import nl.han.dea.joris.services.TrackService;
import nl.han.dea.joris.track.TracksResponseDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TrackServiceTest {

    private TrackService trackService;
    private TracksResponseDTO tracksResponseDTO;

    private TrackDAO trackDAO = mock(TrackDAO.class);

    private static final int PLAYLISTID = 1;
    private static final List<Track> TRACKS = new ArrayList<>();

    @Before
    public void setup(){
        trackService = new TrackService();
        trackService.setTrackDAO(trackDAO);

        tracksResponseDTO = new TracksResponseDTO();
        tracksResponseDTO.setTracks(TRACKS);
    }

    @Test
    public void canGetTracks(){

        Mockito.when(trackDAO.getTracks(PLAYLISTID)).thenReturn(TRACKS);
        TracksResponseDTO tracksResponseDTO = trackService.getTracks(PLAYLISTID);
        Assert.assertNotNull(tracksResponseDTO);
    }
}
