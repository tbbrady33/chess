package dataAccess;

import Model.AuthData;

import java.util.UUID;
import java.util.Vector;

public class MemoryAuthDAO implements AuthDAO {
    public Vector<AuthData> data = new Vector<>();
    @Override
    public void clear() throws DataAccessException {
        data.clear();
    }

    @Override
    public String createAuth(String username) throws DataAccessException {
        String token = UUID.randomUUID().toString();
        data.add(new AuthData(token,username));
        return token;
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        for(AuthData auth: data){
            if (auth.authToken().equals(authToken)){
                return auth;
            }
        }
        return null;
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {
        for(AuthData auth: data) {
            if (auth.authToken().equals(authToken)) {
                data.remove(auth);
                return;
            }
        }
    }
}
