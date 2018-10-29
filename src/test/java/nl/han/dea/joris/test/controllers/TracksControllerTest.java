package nl.han.dea.joris.test.controllers;

import nl.han.dea.joris.controllers.TracksController;
import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.services.TrackService;
import nl.han.dea.joris.services.UserService;
import nl.han.dea.joris.track.TracksResponseDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TracksControllerTest {
    private TracksController tracksController;
    private TracksResponseDTO tracksResponseDTO;

    private UserService userService = mock(UserService.class);
    private TrackService trackService = mock(TrackService.class);

    private static final int PLAYLISTID = 1;
    private static final String TOKEN = "mijnsecrettoken";
    private static final List TRACKS = new ArrayList();

    @Before
    public void setup() {

        tracksController = new TracksController();
        tracksController.setUserService(userService);
        tracksController.setTrackService(trackService);

        tracksResponseDTO = new TracksResponseDTO();
        tracksResponseDTO.setTracks(TRACKS);

    }


    @Test
    public void tracksCouldBeRetrieved() {

        Mockito.when(trackService.getTracks(PLAYLISTID)).thenReturn(tracksResponseDTO);

        Response response = tracksController.getTracks(PLAYLISTID, TOKEN);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void trackCouldNotBeRetrieved(){

        Mockito.when(trackService.getTracks(Mockito.anyInt())).thenThrow(TokenException.class);

        Response response = tracksController.getTracks(PLAYLISTID, TOKEN);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }
}
