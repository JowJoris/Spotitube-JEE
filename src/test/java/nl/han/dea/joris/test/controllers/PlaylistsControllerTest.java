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

    private PlaylistsController playlistsController = new PlaylistsController();
    private UserService userService = Mockito.mock(UserService.class);

    @Test
    public void tokenIsTheSame() {
        playlistsController.setUserService(userService);
        Mockito.when(userService.verifyToken("mijnsecrettoken")).thenReturn(true);

        Response token = playlistsController.getPlaylists("mijnsecrettoken");
        Assert.assertEquals(200, token.getStatus());

    }

    @Test
    public void tokenIsNotTheSame() {
        playlistsController.setUserService(userService);
        Mockito.when(userService.verifyToken("foutetoken")).thenReturn(false);

        Response token = playlistsController.getPlaylists("foutetoken");
        Assert.assertEquals(403, token.getStatus());

    }
}
