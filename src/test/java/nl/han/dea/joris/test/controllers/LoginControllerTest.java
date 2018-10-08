package nl.han.dea.joris.test.controllers;

import nl.han.dea.joris.controllers.LoginController;
import nl.han.dea.joris.login.LoginRequestDTO;
import nl.han.dea.joris.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginControllerTest {

    @Test
    public void loginCredentialsareOkTest(){
        LoginController loginController = new LoginController();
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        UserService userService = Mockito.mock(UserService.class);

        loginRequestDTO.setUser("meron");
        loginRequestDTO.setPassword("test");

        loginController.setUserService(userService);
        Mockito.when(userService.authenticate("meron","test")).thenReturn(true);

        Response login = loginController.login(loginRequestDTO);
        Assert.assertEquals(200, login.getStatus());
    }

    @Test
    public void loginCredentialsareNotOkTest(){
        LoginController loginController = new LoginController();
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        UserService userService = Mockito.mock(UserService.class);

        loginRequestDTO.setUser("meron");
        loginRequestDTO.setPassword("test");

        loginController.setUserService(userService);
        Mockito.when(userService.authenticate("meron","wrongpassword")).thenReturn(false);

        Response login = loginController.login(loginRequestDTO);
        Assert.assertEquals(401, login.getStatus());
    }


}

