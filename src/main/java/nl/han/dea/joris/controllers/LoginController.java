package nl.han.dea.joris.controllers;

import nl.han.dea.joris.login.LoginRequestDTO;
import nl.han.dea.joris.login.LoginResponseDTO;
import nl.han.dea.joris.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO loginRequestDTO) {
        if (userService.authenticate(loginRequestDTO.getUser(),loginRequestDTO.getPassword())) {
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setToken(userService.generateToken());
            loginResponseDTO.setUser(loginRequestDTO.getUser());


            return Response.ok(loginResponseDTO).build();
        } else {
            return Response.status(401).build();
        }
    }
}

