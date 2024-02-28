package CreateGame;

import DataAccess.authDAO;
import DataAccess.gameDAO;
import ListGames.ListGamesResponce;
import Register.RegisterRequest;
import Server.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class CreateGame {
    public String createGame(Request req, Response res, authDAO auth, gameDAO game){
        var serializer = new Gson();
        String stri = req.headers("authorization");
        CreateGameRequest data = serializer.fromJson(req.body(), CreateGameRequest.class);
        res.status(200);
        CreateGameResponce object = new GameService(auth,game).createGame(data,stri);
        String json = new Gson().toJson(object);
        return json;
    }
}
