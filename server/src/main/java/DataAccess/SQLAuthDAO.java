package DataAccess;

import Server.AuthData;
import dataAccess.DataAccessException;

public class SQLAuthDAO implements authDAO{
    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public String createAuth(String username) throws DataAccessException {
        return null;
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {

    }
}
