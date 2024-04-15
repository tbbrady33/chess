package dataAccess;

import DataAccess.DataAccessException;
import Model.UserData;

public interface UserDAO {
    void clear() throws DataAccessException;

    void insertUser(UserData user) throws DataAccessException;

    UserData getUser(String username) throws DataAccessException;
}
