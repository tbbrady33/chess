package ClearApp;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import server.ClearService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ClearApp {
    public String clearApp(Request req, Response res, UserDAO user, AuthDAO auth, GameDAO game){
        var serializer = new Gson();
        ClearAppResponce object = new ClearService(auth,game,user).clear();
        String json = new Gson().toJson(object);
        return json;
    }
}
