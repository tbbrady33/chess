package DataAccess;

import server.AuthData;

public interface AuthDAO {
    void clear() throws dataAccess.DataAccessException;

    String createAuth(String username) throws dataAccess.DataAccessException;

    AuthData getAuth(String authToken) throws dataAccess.DataAccessException;

    void deleteAuth(String authToken) throws dataAccess.DataAccessException;

}
