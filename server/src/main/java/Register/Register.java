package Register;

import DataAccess.authDAO;
import DataAccess.userDAO;
import server.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class Register {

    public String register(Request req, Response res, userDAO user, authDAO auth){
        var serializer = new Gson();

        RegisterRequest data = serializer.fromJson(req.body(), RegisterRequest.class);
        res.status(200);
        RegisterResponce object = new UserService(user,auth).register(data);
        String json = new Gson().toJson(object);
        if (object.message() != null && object.message().equals("Error: already taken")){
            res.status(403);
        } else if (object.message() != null && object.message().equals("Error: bad request")) {
            res.status(400);
        }
        return json;
    }
}
