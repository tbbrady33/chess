package userCommands;

import chess.ChessGame;
import webSocketMessages.userCommands.UserGameCommand;

public class Join_Player extends UserGameCommand {

    private int gameID;
    private ChessGame.TeamColor color;
    public Join_Player(CommandType commandType, String authToken, int gameID, ChessGame.TeamColor color){
        super(commandType,authToken);
        this.color = color;
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public ChessGame.TeamColor getColor() {
        return color;
    }

    public void setColor(ChessGame.TeamColor color) {
        this.color = color;
    }
}
