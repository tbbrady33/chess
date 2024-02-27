package ListGames;

import Login.LoginRequest;
import Server.GameService;
import Server.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ListGames {
    public String listGames(Request req, Response res){
        var serializer = new Gson();
        ListGamesRequest data = serializer.fromJson(req.body(), ListGamesRequest.class);
        return new Gson().toJson(new GameService().listGames(data));
    }
}
