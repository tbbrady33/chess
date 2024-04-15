package server;

import ClearApp.ClearAppResponce;
import DataAccess.DataAccessException;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;

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
        catch (DataAccessException e){
            System.out.println("Data access");
        }
        return null;
    }
}
