package JoinGame;

import DataAccess.AuthDAO;
import DataAccess.GameDAO;
import server.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class JoinGame {
    public String joinGame(Request req, Response res, AuthDAO auth, GameDAO game){
        var serializer = new Gson();
        String stri = req.headers("authorization");
        JoinGameRequest data = serializer.fromJson(req.body(), JoinGameRequest.class);
        res.status(200);
        JoinGameResponce object = new GameService(auth,game).joinGame(data,stri);
        String json = new Gson().toJson(object);
        if(object.message() != null && object.message().equals("Error: bad request")){
            res.status(400);
        }
        if(object.message() != null && object.message().equals("Error: unauthorized")){
            res.status(401);
        }
        if(object.message() != null && object.message().equals("Error: already taken")){
            res.status(403);
        }
        return json;
    }
}
