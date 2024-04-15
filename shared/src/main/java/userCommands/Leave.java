package userCommands;

public class Leave extends UserGameCommand {

    private int gameID;
    public Leave(CommandType commandType, String authToken, int gameID) {
        super(commandType, authToken);
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
