package server;

import ClearApp.ClearAppResponce;
import DataAccess.AuthDAO;
import DataAccess.GameDAO;
import DataAccess.UserDAO;

public class ClearService {
    AuthDAO auth;
    GameDAO game;
    UserDAO user;

    public ClearService(AuthDAO auth, GameDAO game, UserDAO user){
        this.auth = auth;
        this.game = game;
        this.user = user;
    }

    public ClearAppResponce clear(){
        try{
            auth.clear();
            game.clear();
            user.clear();
            return new ClearAppResponce("Success");
        }
        catch (dataAccess.DataAccessException e){
            System.out.println("Data access");
        }
        return null;
    }
}
