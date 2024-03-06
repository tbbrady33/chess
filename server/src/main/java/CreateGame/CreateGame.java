package CreateGame;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import server.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class CreateGame {
    public String createGame(Request req, Response res, AuthDAO auth, GameDAO game){
        var serializer = new Gson();
        String stri = req.headers("authorization");
        CreateGameRequest data = serializer.fromJson(req.body(), CreateGameRequest.class);
        res.status(200);
        CreateGameResponce object = new GameService(auth,game).createGame(data,stri);
        String json = new Gson().toJson(object);
        if(object.message() != null && object.message().equals("Error: unauthorized")){
            res.status(401);
        }
        return json;
    }
}
