package nl.han.dea.joris;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO loginRequestDTO) {
        if (loginRequestDTO.getUser().equals("meron") && loginRequestDTO.getPassword().equals("test")) {
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setToken("mijnspecialetoken");
            loginResponseDTO.setUser(loginRequestDTO.getUser());


            return Response.ok(loginResponseDTO).build();
        } else {
            return Response.status(401).build();
        }
    }
}

