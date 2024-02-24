package DataAccess;

import Server.UserData;
import dataAccess.DataAccessException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Vector;

public class memoryUserDAO implements userDAO{
    public Vector<UserData> data = new Vector<>();
    @Override
    public void clear() throws DataAccessException {
        data.clear();
    }

    @Override
    public void insertUser(UserData user) throws DataAccessException {

        data.add(user);
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {

        for(UserData user: data){
            if (user.username().equals(username)){
                return user;
            }
        }
        return null;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof memoryUserDAO that)) return false;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}