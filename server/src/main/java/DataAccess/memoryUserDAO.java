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
        try {
            data.clear();
        }
        catch (Exception e){
            throw new DataAccessException("failed to clear user data");
        }
    }

    @Override
    public void insertUser(UserData user) throws DataAccessException {
        try {
            data.add(user);
        }
        catch (Exception e){
            throw new DataAccessException("Failed to insert user");
        }
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        try {
            for(UserData user: data){
                if (user.username().equals(username)){
                    return user;
                }
            }
            return null;
        }
        catch (Exception e){
            throw new DataAccessException("Failed to find a user");
        }
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