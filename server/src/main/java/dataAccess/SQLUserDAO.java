package dataAccess;

import DataAccess.DataAccessException;
import Model.UserData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {

    public SQLUserDAO(){
        try {
            DatabaseManager.configureDatabase(createStatements);
        }
        catch (DataAccessException e){

        }

    }
    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE user";
        DatabaseManager.executeUpdate(statement);
    }

    @Override
    public void insertUser(UserData user) throws DataAccessException {
        var statement = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
        DatabaseManager.executeUpdate(statement, user.username(), user.password(),user.email());
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        try(var conn = DatabaseManager.getConnection()){
            var statment = "SELECT username, password, email FROM user WHERE username=?";
            try(var ps = conn.prepareStatement(statment)) {
                ps.setString(1,username);
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return readUser(rs);
                    }
                }
            }
        }
        catch (Exception e){
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
        return null;
    }
    private UserData readUser(ResultSet rs) throws SQLException{
        var username = rs.getString("username");
        var password = rs.getString("password");
        var email = rs.getString("email");
        return new UserData(username,password,email);
    }

    public final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS user (
              `username` varchar(64) NOT NULL,
              `password` varchar(64) NOT NULL,
              `email` varchar(64) NOT NULL
            )
            """
    };


}
