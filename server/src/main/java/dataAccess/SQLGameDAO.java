package dataAccess;

import DataAccess.DataAccessException;
import com.google.gson.Gson;
import Model.GameData;
import chess.ChessGame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class SQLGameDAO implements GameDAO {

    public SQLGameDAO(){
        try {
            DatabaseManager.configureDatabase(createStatements);
        }
        catch (DataAccessException e){

        }
    }
    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE game";
        DatabaseManager.executeUpdate(statement);
    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {
        try(var conn = DatabaseManager.getConnection()){
            var statment = "SELECT gameID,whiteUsername,blackUsername,gameName,game  FROM game WHERE gameID=?";
            try(var ps = conn.prepareStatement(statment)) {
                ps.setInt(1,gameID);
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return readGame(rs);
                    }
                }
            }
        }
        catch (Exception e){
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
        return null;
    }


    @Override
    public void updateGame(ChessGame game, int iD) throws DataAccessException{
        var newGame = new Gson().toJson(game);
        var statement = "UPDATE game SET game = ? WHERE gameID = ?";
        DatabaseManager.executeUpdate(statement,newGame,iD);
    }

    private GameData readGame(ResultSet rs) throws SQLException{
        var gameID = rs.getInt("gameID");
        var whiteUsername = rs.getString("whiteUsername");
        var blackUsername = rs.getString("blackUsername");
        var gameName = rs.getString("gameName");
        var game = rs.getString("game");
        ChessGame game1 = new Gson().fromJson(game,ChessGame.class);

        return new GameData(gameID,whiteUsername,blackUsername,gameName,game1);
    }

    @Override
    public Collection<GameData> listGames(String authToken) throws DataAccessException {
        HashSet<GameData> games= new HashSet<>();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT gameID,whiteUsername,blackUsername,gameName,game  FROM game";
            try (var ps = conn.prepareStatement(statement)) {
                try(var rs = ps.executeQuery()){
                    while(rs.next()){
                         games.add(readGame(rs));
                    }
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            throw new DataAccessException(String.format("unable to update database:"));
        }

        return games;
    }
    @Override
    public void changeUsername(int gameID, String username, ChessGame.TeamColor teamColor) throws DataAccessException {

        if (teamColor == ChessGame.TeamColor.BLACK){
            var statement = "UPDATE game SET blackUsername = ? WHERE gameID = ?";
            DatabaseManager.executeUpdate(statement,username,gameID);
        } else if (teamColor == ChessGame.TeamColor.WHITE) {
            var statement = "UPDATE game SET whiteUsername = ? WHERE gameID = ?";
            DatabaseManager.executeUpdate(statement,username,gameID);

        }

    }

    @Override
    public GameData createGame(String gameName) throws DataAccessException {

        String json = new Gson().toJson(new ChessGame());

        var statment = "INSERT into game (gameName,game) VALUES (?,?)";
        int iD = DatabaseManager.executeUpdate(statment,gameName,json);
        ChessGame game = new ChessGame();

        GameData objec = new GameData(iD, null, null, gameName, game);

        return objec;
    }




    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS game (
                `gameID` int NOT NULL AUTO_INCREMENT,
                `whiteUsername` varchar(64),
                `blackUsername` varchar(64),
                `gameName` varchar(64) NOT NULL,
                `game` TEXT NOT NULL,
                primary key(gameID)
                )
            """
    };




}
