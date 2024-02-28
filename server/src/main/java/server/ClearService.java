package server;

import ClearApp.ClearAppResponce;
import DataAccess.authDAO;
import DataAccess.gameDAO;
import DataAccess.userDAO;

public class ClearService {
    authDAO auth;
    gameDAO game;
    userDAO user;

    public ClearService(authDAO auth, gameDAO game, userDAO user){
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
