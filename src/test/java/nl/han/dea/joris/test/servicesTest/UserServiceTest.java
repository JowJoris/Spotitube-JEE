package nl.han.dea.joris.test.servicesTest;

import nl.han.dea.joris.database.dao.UserDAO;
import nl.han.dea.joris.database.objects.User;
import nl.han.dea.joris.exceptions.TokenException;
import nl.han.dea.joris.exceptions.UnauthorizedException;
import nl.han.dea.joris.login.LoginResponseDTO;
import nl.han.dea.joris.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class UserServiceTest {

    private UserService userService;
    private LoginResponseDTO loginResponseDTO;

    private UserDAO userDAO = mock(UserDAO.class);

    private static final String USERNAME = "meron";
    private static final String PASSWORD = "test";
    private static final String TOKEN = "mijnsecrettoken";
    private static final int USERID = 1;
    private static final User USER = new User();

    @Before
    public void setup(){
        userService = new UserService();
        userService.setUserDAO(userDAO);

        loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser(USERNAME);
        loginResponseDTO.setToken(TOKEN);
    }

    @Test
    public void canAuthenticateUser() throws UnauthorizedException {

        Mockito.when(userDAO.getUser(USERNAME, PASSWORD)).thenReturn(USER);
        LoginResponseDTO loginResponseDTO = userService.authenticate(USERNAME, PASSWORD);
        Assert.assertNotNull(loginResponseDTO);
    }

    @Test (expected=UnauthorizedException.class)
    public void canNotAuthenticateUser() throws UnauthorizedException {

        Mockito.when(userDAO.getUser(Mockito.anyString(), Mockito.anyString())).thenThrow(UnauthorizedException.class);
        userService.authenticate(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void canVerifyToken() throws TokenException {

        Mockito.when(userDAO.getID(TOKEN)).thenReturn(USERID);
        int userID = userService.verifyToken(TOKEN);
        Assert.assertNotNull(userID);
    }

    @Test (expected = TokenException.class)
    public void canNotVerifyToken() throws TokenException {

        Mockito.when(userDAO.getID(Mockito.anyString())).thenThrow(TokenException.class);
        userService.verifyToken(Mockito.anyString());
    }
}
