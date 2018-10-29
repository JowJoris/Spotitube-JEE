package nl.han.dea.joris.test.controllersTest;

import nl.han.dea.joris.controllers.PlaylistsController;
import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.playlist.PlaylistRequestDTO;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;
import nl.han.dea.joris.services.PlaylistService;
import nl.han.dea.joris.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class PlaylistsControllerTest {

    private PlaylistsController playlistsController;
    private PlaylistRequestDTO playlistRequestDTO;
    private PlaylistsResponseDTO playlistsResponseDTO;

    private UserService userService = mock(UserService.class);
    private PlaylistService playlistService = mock(PlaylistService.class);

    private static final String TOKEN = "mijnsecrettoken";
    private static final int USERID = 1;
    private static final int PLAYLISTID = 1;
    private static final String PLAYLISTNAME = "Hardstyle";
    private static final boolean OWNER = false;
    private static final List PLAYLISTS = new ArrayList();
    private static final int LENGTH = 1;


    @Before
    public void setup() {

        playlistsController = new PlaylistsController();

        playlistsController.setUserService(userService);
        playlistsController.setPlaylistService(playlistService);

        playlistRequestDTO = new PlaylistRequestDTO();
        playlistRequestDTO.setPlaylistID(PLAYLISTID);
        playlistRequestDTO.setName(PLAYLISTNAME);
        playlistRequestDTO.setOwner(OWNER);

        playlistsResponseDTO = new PlaylistsResponseDTO();
        playlistsResponseDTO.setPlaylists(PLAYLISTS);
        playlistsResponseDTO.setLength(LENGTH);

    }

    @Test
    public void tokenIsTheSame() throws TokenException {

        Mockito.when(userService.verifyToken(TOKEN)).thenReturn(USERID);

        Response response = playlistsController.getPlaylists(TOKEN);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void tokenIsNotTheSame() throws TokenException {

        Mockito.when(userService.verifyToken(Mockito.anyString())).thenThrow(TokenException.class);
        Response response = playlistsController.getPlaylists(Mockito.anyString());
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void playlistCouldBeRetrieved() {

        Mockito.when(playlistService.getPlaylists(USERID)).thenReturn(playlistsResponseDTO);

        Response response = playlistsController.getPlaylists(TOKEN);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void playlistCouldNotBeRetrieved() {

        Mockito.when(playlistService.getPlaylists(Mockito.anyInt())).thenThrow(TokenException.class);

        Response response = playlistsController.getPlaylists(TOKEN);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void playlistCouldBeAdded() {

        Mockito.when(playlistService.addPlaylist(USERID, playlistRequestDTO.getName()                   )).thenReturn(playlistsResponseDTO);

        Response response = playlistsController.addPlaylist(TOKEN, playlistRequestDTO);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void playlistCouldNotBeAdded() {
        Mockito.when(playlistService.addPlaylist(Mockito.anyInt(), Mockito.anyString())).thenThrow(TokenException.class);

        Response response = playlistsController.addPlaylist(TOKEN, playlistRequestDTO);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void playlistCouldBeEdited() {
        Mockito.when(playlistService.editPlaylist(playlistRequestDTO.getName(), playlistRequestDTO.getPlaylistID(), USERID)).thenReturn(playlistsResponseDTO);

        Response response = playlistsController.editPlaylist(playlistRequestDTO, playlistRequestDTO.getPlaylistID(), TOKEN);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void playlistCouldNotBeEditied() {
        Mockito.when(playlistService.editPlaylist(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenThrow(TokenException.class);

        Response response = playlistsController.editPlaylist(playlistRequestDTO, playlistRequestDTO.getPlaylistID(), TOKEN);
        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void playlistCouldBeDeleted() {
        Mockito.when(playlistService.deletePlaylist(playlistRequestDTO.getPlaylistID(), USERID)).thenReturn(playlistsResponseDTO);

        Response response = playlistsController.deletePlaylist(playlistRequestDTO.getPlaylistID(), TOKEN);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void playlistCouldNotBeDeleted() {
        Mockito.when(playlistService.deletePlaylist(Mockito.anyInt(), Mockito.anyInt())).thenThrow(TokenException.class);

        Response response = playlistsController.deletePlaylist(playlistRequestDTO.getPlaylistID(), TOKEN);

        Assert.assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }
}
