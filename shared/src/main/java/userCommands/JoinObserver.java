package userCommands;

public class JoinObserver extends UserGameCommand {
    private  int gameID;
    public JoinObserver(CommandType commandType, String authToken, int gameID){
        super(commandType,authToken);
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
