package nl.han.dea.joris.controllers;

import nl.han.dea.joris.playlist.PlaylistsDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistsController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlists(@QueryParam("token") String token) {

        if (token.equals("mijnsecrettoken")) {
            PlaylistsDTO playlistsDTO = new PlaylistsDTO();
            playlistsDTO.addPlaylist();
            return Response.ok(playlistsDTO).build();
        } else {
            return Response.status(403).build();
        }
    }
}
