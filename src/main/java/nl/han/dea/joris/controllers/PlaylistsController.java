package nl.han.dea.joris.controllers;

import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;
import nl.han.dea.joris.services.PlaylistService;
import nl.han.dea.joris.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistsController {

    private UserService userService = new UserService();
    private PlaylistService playlistService = new PlaylistService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        try {
            PlaylistsResponseDTO playlistsResponseDTO = playlistService.getPlaylists(userService.verifyToken(token));
            return Response.ok(playlistsResponseDTO).build();
        } catch (TokenException e) {
            return Response.status(403).build();
        }
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playlistID}")
    public Response editPlaylist(@QueryParam("playlistID") int playlistID, @QueryParam("token") String token) {
//        playlistService.

        return Response.ok().build();
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

}

