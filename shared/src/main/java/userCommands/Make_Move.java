package userCommands;

import chess.ChessGame;
import chess.ChessMove;

public class Make_Move extends webSocketMessages.userCommands.UserGameCommand {
    private int gameID;
    private ChessMove move;
    public Make_Move(CommandType commandType, String authToken, int gameID, ChessMove move){
        super(commandType,authToken);
        this.gameID = gameID;
        this.move = move;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public ChessMove getMove() {
        return move;
    }

    public void setMove(ChessMove move) {
        this.move = move;
    }
}
