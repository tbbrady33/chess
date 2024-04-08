package userCommands;

public class Resign extends webSocketMessages.userCommands.UserGameCommand {
    private int gameID;
    public Resign(CommandType commandType, String authToken, int gameID) {
        super(commandType, authToken);
        this.gameID = gameID;
    }
}
