package userCommands;

public class Resign extends UserGameCommand {
    private int gameID;
    public Resign(CommandType commandType, String authToken, int gameID) {
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
