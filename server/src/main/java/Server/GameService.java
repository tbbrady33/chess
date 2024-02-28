package Server;

import CreateGame.CreateGame;
import CreateGame.CreateGameRequest;
import CreateGame.CreateGameResponce;
import DataAccess.authDAO;
import DataAccess.gameDAO;
import JoinGame.JoinGameRequest;
import JoinGame.JoinGameResponce;
import ListGames.ListGamesRequest;
import ListGames.ListGamesResponce;
import Register.RegisterResponce;
import chess.ChessGame;

public class GameService {
    authDAO auth;
    gameDAO game;
    public GameService(authDAO auth, gameDAO game){
        this.auth = auth;
        this.game = game;
    }

    public ListGamesResponce listGames(String request){
        try{
            if(hasAccess(request)){
                return new ListGamesResponce(game.listGames(request),null);
            }
        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data access");
        }
        return null;

    }
    public CreateGameResponce createGame(CreateGameRequest body, String header){
        try {
            if(hasAccess(header)){
                GameData ngame = game.createGame(body.gameName());
                return new CreateGameResponce(ngame.gameID(),null);
            }
        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data access");
        }
        return null;
    }
    public JoinGameResponce joinGame(JoinGameRequest body, String header){
        try {
            if(gameExists(body.gameID(), header)){
                GameData oldGame = game.getGame(body.gameID());
                AuthData player = auth.getAuth(header);
                if(body.playerColor().equals(ChessGame.TeamColor.WHITE)){
                    GameData newGame = new GameData(body.gameID(), player.username(), oldGame.blackUsername(), oldGame.gameName(),oldGame.game());
                }
                if(body.playerColor().equals(ChessGame.TeamColor.BLACK)){
                    GameData newGame = new GameData(body.gameID(), oldGame.whiteUsername(), player.username(), oldGame.gameName(),oldGame.game());
                }
                return new JoinGameResponce("Success");
            }
        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data Access");
        }

        return null;
    }

    private Boolean gameExists(int gameID,String header){
        try {
            for (GameData game : game.listGames(header)) {
                if (game.gameID() == (gameID)) {
                    return true;
                }
            }
            return false;
        }
        catch (dataAccess.DataAccessException e){
            System.out.println("data access");
        }
        return null;
    }

    private Boolean hasAccess(String authToken){
        try{
            if(auth.getAuth(authToken)!= null){
                return true;
            }
            else{
                return false;
            }
        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data access");
        }
        return false;
    }
}
