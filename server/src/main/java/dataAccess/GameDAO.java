package dataAccess;

import Model.GameData;
import chess.ChessGame;

import java.util.Collection;

public interface GameDAO {
    void clear() throws dataAccess.DataAccessException;

    GameData createGame(String gameName) throws dataAccess.DataAccessException;

    GameData getGame(int gameID) throws dataAccess.DataAccessException;

    Collection<GameData> listGames(String authToken) throws dataAccess.DataAccessException;

    public void  changeUsername(int gameID, String username, ChessGame.TeamColor teamColor) throws dataAccess.DataAccessException;
    public void updateGame(GameData game) throws DataAccessException;
}
