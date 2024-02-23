package Server;

import spark.*;


public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");
        // all the endpoints, the new classes are the handler classes
        Spark.delete("/db", (req,res) -> new clearApp(req, res));
        Spark.post("/user",(req,res) -> new Register().register(req,res));
        Spark.post("/session" , (req,res) -> new login(req, res));
        Spark.delete("/session", (req, res) -> new logout(req, res));
        Spark.get("/game", (req, res) -> new listGames(req, res));
        Spark.post("/game", (req, res) -> new createGame(req,res));
        Spark.put("/game", (req, res) -> new joinGame(req, res));


        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
