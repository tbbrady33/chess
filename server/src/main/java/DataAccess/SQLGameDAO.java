package DataAccess;

import server.GameData;
import chess.ChessGame;
import dataAccess.DataAccessException;

import java.util.Collection;

public class SQLGameDAO implements GameDAO {

    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {
        return null;
    }

    @Override
    public Collection<GameData> listGames(String authToken) throws DataAccessException {
        return null;
    }

    @Override
    public GameData createGame(String gameName) throws DataAccessException {
        return null;
    }

    @Override
    public void changeUsername(int gameID, String username, ChessGame.TeamColor teamColor) throws DataAccessException {

    }
}
