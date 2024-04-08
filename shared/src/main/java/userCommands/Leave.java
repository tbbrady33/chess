package userCommands;

public class Leave extends webSocketMessages.userCommands.UserGameCommand {

    private int gameID;
    public Leave(CommandType commandType, String authToken, int gameID) {
        super(commandType, authToken);
        this.gameID = gameID;
    }
}
