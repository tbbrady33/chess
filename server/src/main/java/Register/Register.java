package Register;

import Server.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class Register {

    public String register(Request req, Response res){
        var serializer = new Gson();

        RegisterRequest data = serializer.fromJson(req.body(), RegisterRequest.class);
        res.status(200);
        return new Gson().toJson(new UserService().register(data));
    }
}
