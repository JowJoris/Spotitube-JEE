package nl.han.dea.joris.test.controllersTest;

import nl.han.dea.joris.controllers.LoginController;
import nl.han.dea.joris.exceptions.UnauthorizedException;
import nl.han.dea.joris.login.LoginRequestDTO;
import nl.han.dea.joris.login.LoginResponseDTO;
import nl.han.dea.joris.services.UserService;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.mock;

import javax.ws.rs.core.Response;


public class LoginControllerTest {
    private LoginController loginController;
    private LoginRequestDTO loginRequestDTO;
    private LoginResponseDTO loginResponseDTO;

    private UserService userService = mock(UserService.class);

    private static final String USERNAME ="meron";
    private static final String PASSWORD ="test";
    private static final String TOKEN = "mijnsecrettoken";

    @Before
    public void setup(){

       loginController = new LoginController();

       loginController.setUserService(userService);

       loginRequestDTO = new LoginRequestDTO();
       loginRequestDTO.setUser(USERNAME);
       loginRequestDTO.setPassword(PASSWORD);

       loginResponseDTO = new LoginResponseDTO();
       loginResponseDTO.setUser(USERNAME);
       loginResponseDTO.setToken(TOKEN);

    }


    @Test
    public void loginCredentialsareOkTest() throws UnauthorizedException {

        Mockito.when(userService.authenticate(loginRequestDTO.getUser(),loginRequestDTO.getPassword())).thenReturn(loginResponseDTO);

        Response response = loginController.login(loginRequestDTO);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void loginCredentialsareNotOkTest() throws UnauthorizedException {

        Mockito.when(userService.authenticate(Mockito.anyString(), Mockito.anyString())).thenThrow(UnauthorizedException.class);

        Response response = loginController.login(loginRequestDTO);
        Assert.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }


}

