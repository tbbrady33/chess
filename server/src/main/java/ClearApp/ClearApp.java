package ClearApp;

import DataAccess.authDAO;
import DataAccess.gameDAO;
import DataAccess.userDAO;
import Server.ClearService;
import Server.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ClearApp {
    public String clearApp(Request req, Response res, userDAO user, authDAO auth, gameDAO game){
        var serializer = new Gson();
        ClearAppResponce object = new ClearService(auth,game,user).clear();
        String json = new Gson().toJson(object);
        return json;
    }
}
