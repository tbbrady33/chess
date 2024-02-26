package DataAccess;

import Server.GameData;
import Server.UserData;
import chess.ChessGame;
import dataAccess.DataAccessException;

import java.util.Collection;
import java.util.Vector;
import java.util.Random;

public class MemoryGameDAO implements gameDAO{

    Vector<GameData> data = new Vector<>();
    @Override
    public void clear() throws DataAccessException {
        data.clear();
    }

    @Override
    public Collection<GameData> listGames() throws DataAccessException {
        return data;
    }

    @Override
    public GameData createGame(String gameName) throws DataAccessException {
        Boolean notSame = true;
        Random rand = new Random();
        int ID = 0;
        while (notSame){
            ID = rand.nextInt(10000);
            for(GameData game: data){
                if(game.gameID() != ID){
                    notSame = false;
                    break;
                }
            }
        }
        return new GameData(ID,null,null,gameName,new ChessGame());
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
}
