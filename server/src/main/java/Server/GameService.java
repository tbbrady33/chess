package Server;

import ListGames.ListGamesRequest;
import ListGames.ListGamesResponce;

public class GameService {
    public GameService(){

    }

    public ListGamesResponce listGames(ListGamesRequest request){

        return new ListGamesResponce(1,null,null,null,null);
    }

    private Boolean getAuth(String authToken){
        return false;
    }
}
