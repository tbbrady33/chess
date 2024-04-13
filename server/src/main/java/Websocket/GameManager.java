package Websocket;

import java.util.HashSet;

public class GameManager {
    private HashSet<SingleGame> games = new HashSet<>();

    public HashSet<SingleGame> getGames() {
        return games;
    }

    public void setGames(HashSet<SingleGame> games) {
        this.games = games;
    }

    public void addGame(SingleGame game){
        games.add(game);
    }
}
