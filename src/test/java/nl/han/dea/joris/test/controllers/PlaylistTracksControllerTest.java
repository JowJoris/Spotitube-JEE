package nl.han.dea.joris.test.controllers;

import nl.han.dea.joris.controllers.PlaylistTracksController;
import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.services.TrackService;
import nl.han.dea.joris.services.UserService;
import nl.han.dea.joris.track.PlaylistTracksResponseDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class PlaylistTracksControllerTest {
    private PlaylistTracksController playlistTracksController;
    private PlaylistTracksResponseDTO playlistTracksResponseDTO;

    private UserService userService = mock(UserService.class);
    private TrackService trackService = mock(TrackService.class);

    private static final int PLAYLISTID = 1;
    private static final String TOKEN = "mijnsecrettoken";
    private static final List TRACKS = new ArrayList();

    @Before
    public void setup() {

        playlistTracksController = new PlaylistTracksController();
        playlistTracksController.setUserService(userService);
        playlistTracksController.setTrackService(trackService);

        playlistTracksResponseDTO = new PlaylistTracksResponseDTO();
        playlistTracksResponseDTO.setTracks(TRACKS);

    }


    @Test
    public void tracksCoudlBeRetrieved() {

        Mockito.when(trackService.getTracks(PLAYLISTID)).thenReturn(playlistTracksResponseDTO);

        Response response = playlistTracksController.getTracks(PLAYLISTID, TOKEN);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
