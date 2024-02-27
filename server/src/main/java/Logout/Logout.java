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
        LogoutRequest data = serializer.fromJson(req.headers("authorization"), LogoutRequest.class);
        return new Gson().toJson(new UserService(user,auth).logout(data));
    }
}
