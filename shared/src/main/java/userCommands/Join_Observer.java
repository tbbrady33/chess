package userCommands;

public class Join_Observer extends webSocketMessages.userCommands.UserGameCommand {
    private  int gameID;
    public Join_Observer(CommandType commandType,String authToken, int gameID){
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
