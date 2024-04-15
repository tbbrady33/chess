package dataAccess;

import DataAccess.DataAccessException;
import Model.AuthData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLAuthDAO implements AuthDAO {

    public SQLAuthDAO(){
        try {
            configureDatabase();
        }
        catch (DataAccessException e){

        }
    }
    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE auth";
        executeUpdate(statement);
    }

    @Override
    public String createAuth(String username) throws DataAccessException {
        var token = UUID.randomUUID().toString();
        var statement = "INSERT into auth (authtoken,username) VALUES (?,?)";
        executeUpdate(statement,token,username);
        return token;
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        try(var conn = DatabaseManager.getConnection()){
            var statment = "SELECT authtoken, username FROM auth WHERE authtoken=?";
            try(var ps = conn.prepareStatement(statment)) {
                ps.setString(1,authToken);
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return readAuth(rs);
                    }
                }
            }
        }
        catch (Exception e){
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
        return null;
    }

    private AuthData readAuth(ResultSet rs) throws SQLException{
        var username = rs.getString("username");
        var authtoken = rs.getString("authtoken");
        return new AuthData(authtoken,username);
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {
        var statement = "DELETE FROM auth WHERE authtoken=?";
        executeUpdate(statement,authToken);
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
            CREATE TABLE IF NOT EXISTS auth (
                `authtoken` varchar(64) NOT NULL,
                `username` varchar(64) NOT NULL
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
