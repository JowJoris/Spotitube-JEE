package nl.han.dea.joris.controllers;

import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.services.TrackService;
import nl.han.dea.joris.services.UserService;
import nl.han.dea.joris.track.TrackRequestDTO;
import nl.han.dea.joris.track.TracksResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("playlists/{playlistID}/tracks")
public class TracksController {

    private TrackService trackService = new TrackService();
    private UserService userService = new UserService();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@PathParam("playlistID") int playlistID, @QueryParam("token") String token) {
        try{
            userService.verifyToken(token);
            TracksResponseDTO tracksResponseDTO = trackService.getTracks(playlistID);
            return Response.ok(tracksResponseDTO).build();
        }
        catch (TokenException e){
            return Response.status(403).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrack (@PathParam("playlistID") int playlistID, @QueryParam("token") String token, TrackRequestDTO trackRequestDTO) {
        try{
            userService.verifyToken(token);
            TracksResponseDTO tracksResponseDTO= trackService.addTrack(playlistID, trackRequestDTO.getId(), trackRequestDTO.isOfflineAvailable());
            return Response.ok(tracksResponseDTO).build();
        }
        catch (TokenException e){
            return Response.status(403).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteTrack(@PathParam("playlistID") int playlistID, @PathParam("id") int id, @QueryParam("token") String token){
        try {
            userService.verifyToken(token);
            TracksResponseDTO tracksResponseDTO = trackService.deleteTrack(playlistID, id);
            return Response.ok(tracksResponseDTO).build();
        } catch (TokenException e) {
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
