package dataAccess;

import Model.UserData;

public interface UserDAO {
    void clear() throws dataAccess.DataAccessException;

    void insertUser(UserData user) throws dataAccess.DataAccessException;

    UserData getUser(String username) throws dataAccess.DataAccessException;
}
