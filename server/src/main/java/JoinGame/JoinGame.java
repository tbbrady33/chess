package JoinGame;

import CreateGame.CreateGameRequest;
import CreateGame.CreateGameResponce;
import DataAccess.authDAO;
import DataAccess.gameDAO;
import Server.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class JoinGame {
    public String joinGame(Request req, Response res, authDAO auth, gameDAO game){
        var serializer = new Gson();
        String stri = req.headers("authorization");
        JoinGameRequest data = serializer.fromJson(req.body(), JoinGameRequest.class);
        res.status(200);
        JoinGameResponce object = new GameService(auth,game).joinGame(data,stri);
        String json = new Gson().toJson(object);
        return json;
    }
}
