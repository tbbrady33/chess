package Logout;

import Server.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class Logout {
    public String logout(Request req, Response res){
        var serializer = new Gson();
        LogoutRequest data = serializer.fromJson(req.body(), LogoutRequest.class);
        return new Gson().toJson(new UserService().logout(data));
    }
}
