package nl.han.dea.joris.test.controllers;

import nl.han.dea.joris.controllers.LoginController;
import nl.han.dea.joris.exceptions.UnauthorizedException;
import nl.han.dea.joris.login.LoginRequestDTO;
import nl.han.dea.joris.login.LoginResponseDTO;
import nl.han.dea.joris.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginControllerTest {

    @Test
    public void loginCredentialsareOkTest() throws UnauthorizedException {
        LoginController loginController = new LoginController();
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        UserService userService = Mockito.mock(UserService.class);

        loginRequestDTO.setUser("meron");
        loginRequestDTO.setPassword("test");

        loginResponseDTO.setUser("meron");
        loginResponseDTO.setToken("mijnsecrettoken");

        loginController.setUserService(userService);
        Mockito.when(userService.authenticate(Mockito.anyString(),Mockito.anyString())).thenReturn(loginResponseDTO);

        Response login = loginController.login(loginRequestDTO);
        Assert.assertEquals(200, login.getStatus());
    }

    @Test
    public void loginCredentialsareNotOkTest() throws UnauthorizedException {
        LoginController loginController = new LoginController();
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        UserService userService = Mockito.mock(UserService.class);

        loginRequestDTO.setUser("meron");
        loginRequestDTO.setPassword("test");

        loginController.setUserService(userService);
        Mockito.when(userService.authenticate(Mockito.anyString(),Mockito.anyString())).thenThrow(UnauthorizedException.class);

        Response login = loginController.login(loginRequestDTO);
        Assert.assertEquals(401, login.getStatus());
    }


}

