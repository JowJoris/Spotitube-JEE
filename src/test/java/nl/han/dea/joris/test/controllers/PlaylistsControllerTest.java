package nl.han.dea.joris.test.controllers;

import nl.han.dea.joris.controllers.PlaylistsController;
import nl.han.dea.joris.services.PlaylistService;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class PlaylistsControllerTest {

    @Test
    public void tokenIsTheSame(){
        PlaylistsController playlistsController = new PlaylistsController();
        PlaylistService playlistService = new PlaylistService();


        Response token =  playlistsController.getPlaylists("mijnsecrettoken");
        Assert.assertEquals(200, token.getStatus());

    }

    @Test
    public void tokenIsNotTheSame(){
        PlaylistsController playlistsController = new PlaylistsController();

        Response token =  playlistsController.getPlaylists("foutetoken");
        Assert.assertEquals(403, token.getStatus());

    }
}
