package nl.han.dea.joris.controllers;

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
        if (userService.verifyToken(token)) {
                PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();
                playlistsResponseDTO.setPlaylists(playlistService.getPlaylists(token));
                return Response.ok(playlistsResponseDTO).build();

        } else {
            return Response.status(403).build();
        }
    }

    @Inject
    public void setUserService(UserService userService){this.userService=userService;}
    public void setPlaylistService(PlaylistService playlistService){this.playlistService=playlistService;}



//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlistDTO) {
//
//    }

}

