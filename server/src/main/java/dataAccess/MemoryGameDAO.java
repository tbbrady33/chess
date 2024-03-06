package dataAccess;

import server.GameData;
import chess.ChessGame;

import java.util.Collection;
import java.util.Vector;
import java.util.Random;

public class MemoryGameDAO implements GameDAO {

    public Vector<GameData> data = new Vector<>();
    @Override
    public void clear() throws DataAccessException {
        data.clear();
    }

    @Override
    public Collection<GameData> listGames(String authToken) throws DataAccessException {
        return data;
    }

    @Override
    public GameData createGame(String gameName) throws DataAccessException {
        Boolean notSame = true;
        Random rand = new Random();
        int iD = 0;
        while (notSame){
            iD = rand.nextInt(10000);
            if (data.size() >= 1) {
                for (GameData game : data) {
                    if (game.gameID() != iD) {
                        notSame = false;
                        break;
                    }
                }
            }
            else {
                GameData objec = new GameData(iD,null,null,gameName,new ChessGame());
                data.add(objec);
                return objec;
            }
        }
        GameData objec = new GameData(iD,null,null,gameName,new ChessGame());
        data.add(objec);
        return objec;
    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {
        for(GameData game: data){
            if (game.gameID() == (gameID)){
                return game;
            }
        }
        return null;
    }

    @Override
    public void  changeUsername(int gameID, String username, ChessGame.TeamColor teamColor) throws DataAccessException{
        GameData newGame;
        for(int i = 0; i < data.size(); i++){
            if(data.elementAt(i).gameID() == gameID){
                if(teamColor == ChessGame.TeamColor.BLACK) {
                    newGame = new GameData(gameID,data.elementAt(i).whiteUsername(),username,data.elementAt(i).gameName(),data.elementAt(i).game());
                    data.remove(data.elementAt(i));
                    data.add(newGame);
                    break;
                } else if (teamColor == ChessGame.TeamColor.WHITE){
                    newGame = new GameData(gameID,username,data.elementAt(i).blackUsername(),data.elementAt(i).gameName(),data.elementAt(i).game());
                    data.remove(data.elementAt(i));
                    data.add(newGame);
                    break;
                }
            }
        }
    }
}
