package Server;

import DataAccess.*;
import Login.LoginRequest;
import Login.LoginResponce;
import Logout.LogoutRequest;
import Logout.LogoutResponce;
import Register.RegisterRequest;
import Register.RegisterResponce;

public class UserService {

    userDAO user;
    authDAO auth;

    public UserService(userDAO user, authDAO auth) {
        this.user = user;
        this.auth = auth;
    }

    public RegisterResponce register(RegisterRequest request) {

        if(request.username() == null || request.password() == null){
            return new RegisterResponce(null,null,"Error: bad request");
        }

        boolean exists = false;
        try {
            if (user.getUser(request.username()) == null){

            }
            else {
                exists = true;
                return new RegisterResponce(null,null,"Error: already taken");
            }
            user.insertUser(new UserData(request.username(),request.password(),request.email()));

            String token = auth.createAuth(request.username());
            return new RegisterResponce(request.username(),token,null);

        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data access exeption");
        }
        return null;
    }
    public LoginResponce login(LoginRequest request){
        boolean  exists = false;
        UserData nuser = null;
        if(isinUser(request.username())){
            try {
                nuser = user.getUser(request.username());
            }
            catch (dataAccess.DataAccessException e){
                System.out.println("Data access error");
            }
        }else {
            return new LoginResponce(null,null,"Error: unauthorized");
        }
        if(nuser != null && isrightPass(request.password(),nuser.password())){
            try {
                String token = auth.createAuth(request.username());
                return new LoginResponce(nuser.username(),token,null);
            }
            catch (dataAccess.DataAccessException e){
                System.out.println("Data access error");
            }

        }
        else{
            return new LoginResponce(null, null, "Error: unauthorized");
        }
        return null;
    }


    public LogoutResponce logout(String request){
        if(isinAuth(request)){
            try {
                AuthData data = auth.getAuth(request);
                auth.deleteAuth(data.authToken());
                return new LogoutResponce("Success");
            }
            catch (dataAccess.DataAccessException e){
                System.out.println("Data access");
            }
        }

        return new LogoutResponce("Error: unauthorized");
    }
    private boolean isinUser(String username){
        try{
            if(user.getUser(username) != null){
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
        if(reqpass.equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
}
