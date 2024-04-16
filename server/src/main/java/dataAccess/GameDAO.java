package dataAccess;

import DataAccess.DataAccessException;
import Model.GameData;
import chess.ChessGame;

import java.util.Collection;

public interface GameDAO {
    void clear() throws DataAccessException;

    GameData createGame(String gameName) throws DataAccessException;

    GameData getGame(int gameID) throws DataAccessException;

    Collection<GameData> listGames(String authToken) throws DataAccessException;

    public void  changeUsername(int gameID, String username, ChessGame.TeamColor teamColor) throws DataAccessException;
    public void updateGame(ChessGame game, int iD) throws DataAccessException;
}
