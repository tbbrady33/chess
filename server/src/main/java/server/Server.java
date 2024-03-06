package server;

import ClearApp.ClearApp;
import CreateGame.CreateGame;
import dataAccess.*;
import JoinGame.JoinGame;
import ListGames.ListGames;
import Login.Login;
import Logout.Logout;
import Register.Register;
import spark.*;


public class Server {
    public static void main(String args[]){
        new Server().run(8080);
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);
        Spark.staticFiles.location("web");
        //data access stuff
        AuthDAO auth = new SQLAuthDAO();
        UserDAO user = new SQLUserDAO();
        GameDAO game = new SQLGameDAO();

        // all the endpoints, the new classes are the handler classes
        Spark.delete("/db", (req,res) -> new ClearApp().clearApp(req, res,user,auth,game));
        Spark.post("/user",(req,res) -> new Register().register(req,res,user,auth));
        Spark.post("/session" , (req,res) -> new Login().login(req, res,user,auth));
        Spark.delete("/session", (req, res) -> new Logout().logout(req, res,user,auth));
        Spark.get("/game", (req, res) -> new ListGames().listGames(req, res,auth,game));
        Spark.post("/game", (req, res) -> new CreateGame().createGame(req,res,auth,game));
        Spark.put("/game", (req, res) -> new JoinGame().joinGame(req, res,auth,game));


        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
