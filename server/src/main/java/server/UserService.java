package server;

import dataAccess.*;
import Login.LoginRequest;
import Login.LoginResponce;
import Logout.LogoutResponce;
import Register.RegisterRequest;
import Register.RegisterResponce;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {

    UserDAO user;
    AuthDAO auth;

    public UserService(UserDAO user, AuthDAO auth) {
        this.user = user;
        this.auth = auth;
    }

    public RegisterResponce register(RegisterRequest request) {

        if(request.username() == null || request.password() == null || request.username().isEmpty()){
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


            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(request.password());

            // write the hashed password in database along with the user's other information
            user.insertUser(new UserData(request.username(),hashedPassword,request.email()));

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

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean pass = encoder.matches(request.password(), nuser.password());

        if(pass){
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
