package Server;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class Login {
    public String login(Request req, Response res){
        var serializer = new Gson();
        LoginRequest data = serializer.fromJson(req.body(), LoginRequest.class);
        return new Gson().toJson(new UserService().login(data));
    }
}
