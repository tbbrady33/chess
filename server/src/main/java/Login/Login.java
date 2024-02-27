package Login;

import DataAccess.authDAO;
import DataAccess.userDAO;
import Server.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class Login {
    public String login(Request req, Response res, userDAO user, authDAO auth){
        var serializer = new Gson();
        LoginRequest data = serializer.fromJson(req.body(), LoginRequest.class);
        res.status(200);
        LoginResponce  object = new UserService(user,auth).login(data);
        String json = new Gson().toJson(object);
        if(object.message() != null && object.message().equals("Error: unauthorized")){
            res.status(401);
        }
        return json;
    }
}
