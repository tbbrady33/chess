package DataAccess;

import Server.AuthData;
import Server.UserData;

public interface userDAO {
    void clear() throws dataAccess.DataAccessException;

    void insertUser(UserData user) throws dataAccess.DataAccessException;

    UserData getUser(String username) throws dataAccess.DataAccessException;
}
