package ListGames;

import DataAccess.authDAO;
import DataAccess.gameDAO;
import Login.LoginRequest;
import Server.GameService;
import Server.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ListGames {
    public String listGames(Request req, Response res, authDAO auth, gameDAO game){
        var serializer = new Gson();
        String data = req.headers("authorization");
        res.status(200);
        ListGamesResponce object = new GameService(auth,game).listGames(data);
        String json = new Gson().toJson(object);
        return json;
    }
}
