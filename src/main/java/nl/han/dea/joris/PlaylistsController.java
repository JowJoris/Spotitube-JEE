package nl.han.dea.joris;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistsController {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlists(@QueryParam("token") String token) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();



        return Response.ok(playlistsDTO).build();
    }


}
