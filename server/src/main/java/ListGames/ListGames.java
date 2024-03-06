package ListGames;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import server.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ListGames {
    public String listGames(Request req, Response res, AuthDAO auth, GameDAO game){
        var serializer = new Gson();
        String data = req.headers("authorization");
        res.status(200);
        ListGamesResponce object = new GameService(auth,game).listGames(data);
        String json = new Gson().toJson(object);
        if(object.message() != null && object.message().equals("Error: unauthorized")){
            res.status(401);
        }
        return json;
    }
}
