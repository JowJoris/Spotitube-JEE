package nl.han.dea.joris.controllers;

import nl.han.dea.joris.exceptions.UnauthorizedException;
import nl.han.dea.joris.login.LoginRequestDTO;
import nl.han.dea.joris.login.LoginResponseDTO;
import nl.han.dea.joris.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private UserService userService = new UserService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO loginRequestDTO) {
        try{
            LoginResponseDTO loginResponseDTO = userService.authenticate(loginRequestDTO.getUser(), loginRequestDTO.getPassword());
            return Response.ok(loginResponseDTO).build();
        }
        catch(UnauthorizedException e){
            return Response.status(401).build();
        }
    }


    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

