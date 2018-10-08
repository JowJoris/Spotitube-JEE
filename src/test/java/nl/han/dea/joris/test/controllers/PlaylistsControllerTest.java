package nl.han.dea.joris.test.controllers;

import nl.han.dea.joris.controllers.PlaylistsController;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class PlaylistsControllerTest {

    @Test
    public void tokenIsTheSame(){
        PlaylistsController playlistsController = new PlaylistsController();

        Response token =  playlistsController.playlists("mijnspecialetoken");
        Assert.assertEquals(200, token.getStatus());

    }

    @Test
    public void tokenIsNotTheSame(){
        PlaylistsController playlistsController = new PlaylistsController();

        Response token =  playlistsController.playlists("foutetoken");
        Assert.assertEquals(403, token.getStatus());

    }
}
