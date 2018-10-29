package nl.han.dea.joris.track;

import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.services.TrackService;
import nl.han.dea.joris.services.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/tracks")
public class AllTracks {

    private TrackService trackService = new TrackService();
    private UserService userService = new UserService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token) {
        try {
            userService.verifyToken(token);
            TracksResponseDTO tracksResponseDTO = trackService.getListOfTracks(playlistID);
            return Response.ok(tracksResponseDTO).build();
        } catch (TokenException e) {
            return Response.status(403).build();
        }
    }
}
