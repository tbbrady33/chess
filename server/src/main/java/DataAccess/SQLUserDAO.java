package DataAccess;

import server.UserData;
import dataAccess.DataAccessException;

public class SQLUserDAO implements UserDAO {
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
