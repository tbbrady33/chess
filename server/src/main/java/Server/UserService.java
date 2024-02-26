package Server;

import DataAccess.*;
import Login.LoginRequest;
import Login.LoginResponce;
import Logout.LogoutRequest;
import Logout.LogoutResponce;
import Register.RegisterRequest;
import Register.RegisterResponce;

public class UserService {


    public UserService() {
    }

    public RegisterResponce register(RegisterRequest request) {
        userDAO thing = new memoryUserDAO();
        authDAO auth = new MemoryAuthDAO();

        boolean exists = false;
        try {
            if (thing.getUser(request.username()) == null){

            }
            else {
                exists = true;
                return new RegisterResponce(null,null,"Error: already taken");
            }
            thing.insertUser(new UserData(request.username(),request.password(),request.email()));

            String token = auth.createAuth(request.username());
            return new RegisterResponce(request.username(),token,null);

        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data access exeption");
        }
        return null;
    }
    public LoginResponce login(LoginRequest request){
        userDAO thing = new memoryUserDAO();
        authDAO auth = new MemoryAuthDAO();
        boolean  exists = false;
        UserData user = null;
        if(isinUser(request.username())){
            try {
                user = thing.getUser(request.username());
            }
            catch (dataAccess.DataAccessException e){
                // this is a data access error
            }
        }else {
            //we have an error exit code
        }
        if(isrightPass(request.password(),user.password())){
            try {
                String token = auth.createAuth(request.username());
                return new LoginResponce(user.username(),token,null);
            }
            catch (dataAccess.DataAccessException e){
                // didnt work
            }

        }
        else{
            // problem exit now
        }
        return null;
    }

    public LogoutResponce logout(LogoutRequest request){
        authDAO auth = new MemoryAuthDAO();
        if(isinAuth(request.authtoken())){
            try {
                AuthData data = auth.getAuth(request.authtoken());
                auth.deleteAuth(data.authToken());
                return new LogoutResponce("Success",null);
            }
            catch (dataAccess.DataAccessException e){
                System.out.println("Data access");
            }
        }

        return null;
    }
    private boolean isinUser(String username){
        userDAO thing = new memoryUserDAO();
        try{
            if(thing.getUser(username) != null){
                return true;
            }
            else{
                return false;

            }
        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Dataaccess exeption");
        }
        return false;
    }


    private boolean isinAuth(String authtoken){
        authDAO auth = new MemoryAuthDAO();
        try{
            if(auth.getAuth(authtoken) != null){
                return true;
            }
            else {
                return false;
            }

        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data access stuff");

        }
        return false;
    }

    private boolean isrightPass(String reqpass, String password){
        userDAO pass = new memoryUserDAO();
        if(reqpass.equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
}
