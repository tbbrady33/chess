package DataAccess;

import Server.UserData;
import dataAccess.DataAccessException;

public class SQLUserDAO implements userDAO{
    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public void insertUser(UserData user) throws DataAccessException {

    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        return null;
    }
}
