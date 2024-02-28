package Logout;

import DataAccess.authDAO;
import DataAccess.userDAO;
import Server.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class Logout {
    public String logout(Request req, Response res, userDAO user, authDAO auth){
        var serializer = new Gson();
        String data = req.headers("authorization");
        LogoutResponce object = new UserService(user,auth).logout(data);

        return new Gson().toJson(object);
    }
}
