package DataAccess;

import Server.GameData;

import java.util.Collection;

public interface gameDAO {
    void clear() throws dataAccess.DataAccessException;

    GameData createGame(String gameName) throws dataAccess.DataAccessException;

    GameData getGame(int gameID) throws dataAccess.DataAccessException;

    Collection<GameData> listGames(String authToken) throws dataAccess.DataAccessException;

}
