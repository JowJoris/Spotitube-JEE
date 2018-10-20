package nl.han.dea.joris.test.controllers;

import nl.han.dea.joris.controllers.PlaylistsController;
import nl.han.dea.joris.services.PlaylistService;
import nl.han.dea.joris.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class PlaylistsControllerTest {


    @Test
    public void tokenIsTheSame() {
        PlaylistsController playlistsController = new PlaylistsController();
        UserService userService = Mockito.mock(UserService.class);
        PlaylistService playlistService = Mockito.mock(PlaylistService.class);

        playlistsController.setUserService(userService);
        playlistsController.setPlaylistService(playlistService);
        Mockito.when(userService.verifyToken("mijnsecrettoken")).thenReturn(true);

        Response token = playlistsController.getPlaylists("mijnsecrettoken");
        Assert.assertEquals(200, token.getStatus());

    }

    @Test
    public void tokenIsNotTheSame() {
        PlaylistsController playlistsController = new PlaylistsController();
        UserService userService = Mockito.mock(UserService.class);
        PlaylistService playlistService = Mockito.mock(PlaylistService.class);

        playlistsController.setUserService(userService);
        playlistsController.setPlaylistService(playlistService);
        Mockito.when(userService.verifyToken("foutetoken")).thenReturn(false);

        Response token = playlistsController.getPlaylists("foutetoken");
        Assert.assertEquals(403, token.getStatus());

    }
}
