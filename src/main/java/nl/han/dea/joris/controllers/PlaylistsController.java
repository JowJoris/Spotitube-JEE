package nl.han.dea.joris.controllers;

import nl.han.dea.joris.playlist.PlaylistDTO;
import nl.han.dea.joris.playlist.PlaylistsResponseDTO;
import nl.han.dea.joris.services.PlaylistService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistsController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        if (token.equals("mijnsecrettoken")) {
                PlaylistService playlistService = new PlaylistService();
                PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();
                playlistsResponseDTO.setPlaylists(playlistService.getPlaylists(token));
                return Response.ok(playlistsResponseDTO).build();

        } else {
            return Response.status(403).build();
        }
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlistDTO) {
//
//    }

}

