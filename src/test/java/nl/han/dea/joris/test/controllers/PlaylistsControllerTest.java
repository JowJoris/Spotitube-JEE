package nl.han.dea.joris.test.controllers;

import nl.han.dea.joris.controllers.PlaylistsController;
import nl.han.dea.joris.database.objects.Playlist;
import nl.han.dea.joris.database.objects.User;
import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;
import nl.han.dea.joris.services.PlaylistService;
import nl.han.dea.joris.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsControllerTest {


//    @Test
//    public void tokenIsTheSame() throws TokenException {
//        PlaylistsController playlistsController = new PlaylistsController();
//        UserService userService = Mockito.mock(UserService.class);
//
//        playlistsController.setUserService(userService);
//        Mockito.when(userService.verifyToken(Mockito.anyString())).thenReturn(Mockito.anyInt());
//    }
//
//    @Test
//    public void tokenIsNotTheSame() throws TokenException {
//        PlaylistsController playlistsController = new PlaylistsController();
//        UserService userService = Mockito.mock(UserService.class);
//
//        playlistsController.setUserService(userService);
//        Mockito.when(userService.verifyToken(Mockito.anyString())).thenThrow(TokenException.class);
//    }

//    @Test
//    public void playlistCouldNotBeRetrieved () {
//        PlaylistsController playlistsController = new PlaylistsController();
//        PlaylistService playlistService = Mockito.mock(PlaylistService.class);
//
//        playlistsController.setPlaylistService(playlistService);
//        Mockito.when(playlistService.getPlaylists(Mockito.anyInt())).thenThrow(TokenException.class);
//
//        Response getPlaylists = playlistsController.getPlaylists(Mockito.anyString());
//        Assert.assertEquals(403,getPlaylists.getStatus());
//    }

//    @Test
//    public void playlistCouldBeRetrieved () {
//        PlaylistsController playlistsController = new PlaylistsController();
//        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();
//        PlaylistService playlistService = Mockito.mock(PlaylistService.class);
//
//        playlistsController.setPlaylistService(playlistService);
//        Mockito.when(playlistService.getPlaylists(Mockito.anyInt())).thenReturn(playlistsResponseDTO);
//
//        Response getPlaylists = playlistsController.getPlaylists(Mockito.anyString());
//        Assert.assertEquals(200, getPlaylists.getStatus());
//    }
}
