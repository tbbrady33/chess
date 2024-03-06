package dataAccess;

import com.google.gson.Gson;
import server.GameData;
import chess.ChessGame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;

public class SQLGameDAO implements GameDAO {

    public SQLGameDAO(){
        try {
            configureDatabase();
        }
        catch (DataAccessException e){

        }
    }
    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE game";
        executeUpdate(statement);
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
        Boolean notSame = true;
        Random rand = new Random();
        int iD = 0;
        ResultSet readAuth = null;
        try(var conn = DatabaseManager.getConnection()){
            var statment = "SELECT gameName FROM game";
            try(var ps = conn.prepareStatement(statment)) {
                ps.setString(1,gameName);
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        readAuth = rs;
                    }
                }
            }
        }
        catch (Exception e){
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
        while (notSame) {
            iD = rand.nextInt(10000);
            if (readAuth == null) {
                for (GameData game : data) {
                    if (game.gameID() != iD) {
                        notSame = false;
                        break;
                    }
                }

            } else {
                GameData objec = new GameData(iD, null, null, gameName, new ChessGame());
                String json = new Gson().toJson(objec);

                return objec;
            }
        }
        return null;
    }

    @Override
    public void changeUsername(int gameID, String username, ChessGame.TeamColor teamColor) throws DataAccessException {

    }

    private void executeUpdate(String statement, Object... params) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var ps = conn.prepareStatement(statement)) {
                for (var i = 0; i < params.length; i++) {
                    var param = params[i];
                    if (param instanceof String p) ps.setString(i + 1, p);
                    else if (param instanceof Integer p) ps.setInt(i + 1, p);
                    else if (param == null) ps.setNull(i + 1, 0);
                }
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(String.format("unable to update database: %s, %s", statement, e.getMessage()));
        }
    }
    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS game (
                `gameID` int NOT NULL,
                `whiteUsername` varchar(64),
                `blackUsername` varchar(64),
                `gameName` varchar(64) NOT NULL,
                `game`TEXT NOT NULL
                )
            """
    };


    private void configureDatabase() throws DataAccessException {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            for (var statement : createStatements) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataAccessException("Couldn't configure a database");
        }
    }
}
