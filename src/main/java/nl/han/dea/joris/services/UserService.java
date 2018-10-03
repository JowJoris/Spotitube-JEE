package nl.han.dea.joris.services;

public class UserService {

    public boolean authenticate(String user, String password){
        return "meron".equals(user) && "test".equals(password);
    }

    public String generateToken(){
        return "mijnspecialetoken";
    }
}
