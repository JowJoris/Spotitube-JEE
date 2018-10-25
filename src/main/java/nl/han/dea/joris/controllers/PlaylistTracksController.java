package nl.han.dea.joris.controllers;

import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.services.TrackService;
import nl.han.dea.joris.services.UserService;
import nl.han.dea.joris.track.PlaylistTracksResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("playlists/{playlistID}/tracks")
public class PlaylistTracksController {

    private TrackService trackService = new TrackService();
    private UserService userService = new UserService();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@PathParam("playlistID") int playlistID, @QueryParam("token") String token) {
        try{
            userService.verifyToken(token);
            PlaylistTracksResponseDTO playlistTracksResponseDTO = trackService.getTracks(playlistID);
            return Response.ok(playlistTracksResponseDTO).build();
        }
        catch (TokenException e){
            return Response.status(403).build();
        }
    }
    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

}
